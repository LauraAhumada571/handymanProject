package com.calculator.handyman.infraestructure.gateways;

import com.calculator.handyman.core.domain.reportService.ReportService;
import com.calculator.handyman.core.gateways.ReportServiceRepository;
import com.calculator.handyman.infraestructure.gateways.repositories.models.QueryInputDBO;
import com.calculator.handyman.infraestructure.gateways.repositories.models.ReportServiceWeekDBO;
import com.calculator.handyman.shared.domain.ReportServiceWeek;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlReportServiceRepository implements ReportServiceRepository {

    private final DataSource dataSource;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SqlReportServiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(ReportService reportService) {
        String sql = "INSERT INTO report_service (technician_id, service_id, start_date_time, end_date_time) VALUES (?,?,?,?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            String parsedStartDateTime = reportService.getStartDateTime().getValue().format(dateTimeFormatter);
            String parsedEndDateTime = reportService.getEndDateTime().getValue().format(dateTimeFormatter);

            preparedStatement.setString(1, reportService.getTechnicianId().toString());
            preparedStatement.setString(2, reportService.getServiceId().toString());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(parsedStartDateTime));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(parsedEndDateTime));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error querying database", e);
        }
    }


    @Override
    public List<ReportServiceWeek> getListReport(QueryInputDBO queryInputDBO) {
        String sql = "SELECT rs.technician_id, rs.service_id, rs.start_date_time, rs.end_date_time, nw.number FROM report_service rs " +
                "JOIN service_number_of_week snw " +
                "ON snw.technician_id = rs.technician_id " +
                "AND snw.service_id = rs.service_id " +
                "JOIN number_of_week nw ON (nw.number_of_week_id = snw.number_of_week_id) " +
                "WHERE rs.technician_id = ? AND " +
                "((rs.start_date_time >= ? AND rs.start_date_time <= ?) " +
                "OR (rs.end_date_time >= ? AND rs.end_date_time <= ?))";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, queryInputDBO.getTechnicianId().toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(queryInputDBO.getStartDateTime().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(queryInputDBO.getEndDateTime().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(queryInputDBO.getStartDateTime().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(queryInputDBO.getEndDateTime().format(dateTimeFormatter)));

            ResultSet resultSet = preparedStatement.executeQuery();
            List<ReportServiceWeek> result = new ArrayList<>();

            while(resultSet.next()){
                ReportServiceWeekDBO dbo = ReportServiceWeekDBO.fromResultSet(resultSet);
                ReportServiceWeek domainReportServiceWeek = dbo.toDomain();
                result.add(domainReportServiceWeek);
            }

            resultSet.close();
            return  result;
        } catch (SQLException e) {
            throw new RuntimeException("Error querying database Report Service", e);
        }
    }
}
