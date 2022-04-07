package com.calculator.handyman.infraestructure.gateways;

import com.calculator.handyman.core.domain.serviceNumberOfWeek.ServiceNumberOfWeek;
import com.calculator.handyman.core.gateways.ServiceNumberOfWeekRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SqlServiceNumberOfWeekRepository implements ServiceNumberOfWeekRepository {
    private final DataSource dataSource;

    public SqlServiceNumberOfWeekRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(ServiceNumberOfWeek serviceNumberOfWeek) {
        String sql = "INSERT INTO service_number_of_week (technician_id, service_id, number_of_week_id) VALUES (?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,serviceNumberOfWeek.getTechnicianId().toString());
            preparedStatement.setString(2, serviceNumberOfWeek.getServiceId().toString());
            preparedStatement.setString(3, serviceNumberOfWeek.getNumberOfWeekId().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error querying database", e);
        }
    }
}
