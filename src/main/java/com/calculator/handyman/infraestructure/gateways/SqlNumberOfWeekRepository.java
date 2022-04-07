package com.calculator.handyman.infraestructure.gateways;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeek;
import com.calculator.handyman.core.gateways.NumberOfWeekRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SqlNumberOfWeekRepository implements NumberOfWeekRepository {
    private final DataSource dataSource;

    public SqlNumberOfWeekRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(NumberOfWeek numberOfWeek) {
        String sql = "INSERT INTO number_of_week (number_of_week_id, number)  VALUES(?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, numberOfWeek.getNumberOfWeekId().toString());
            preparedStatement.setInt(2, numberOfWeek.getNumberOfWeekNumber().getValue());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error querying database", e);
        }
    }
}
