package com.calculator.handyman.infraestructure.gateways.repositories.models;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekNumber;
import com.calculator.handyman.core.domain.reportService.ReportServiceEndDateTime;
import com.calculator.handyman.core.domain.reportService.ReportServiceStartDateTime;
import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.technician.TechnicianId;
import com.calculator.handyman.shared.domain.ReportServiceWeek;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportServiceWeekDBO {
    private String technicianId;
    private String serviceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer number;

    public ReportServiceWeekDBO() {
    }

    public ReportServiceWeekDBO(String technicianId, String serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer number) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.number = number;
    }

    public ReportServiceWeek toDomain(){
        return new ReportServiceWeek(
                new TechnicianId(technicianId),
                new ServiceId(serviceId),
                new ReportServiceStartDateTime(startDateTime),
                new ReportServiceEndDateTime(endDateTime),
                new NumberOfWeekNumber(number)
        );
    }

    public static ReportServiceWeekDBO fromDomain(ReportServiceWeek reportServiceWeek){
        return new ReportServiceWeekDBO(
                reportServiceWeek.getTechnicianId().toString(),
                reportServiceWeek.getServiceId().toString(),
                reportServiceWeek.getStartDateTime().getValue(),
                reportServiceWeek.getEndDateTime().getValue(),
                reportServiceWeek.getNumber().getValue()
        );
    }

    public static ReportServiceWeekDBO fromResultSet(ResultSet resultSet) throws SQLException {
        ReportServiceWeekDBO dbo = new ReportServiceWeekDBO();
        dbo.setTechnicianId(resultSet.getString("technician_id"));
        dbo.setServiceId(resultSet.getString("service_id"));
        dbo.setStartDateTime(resultSet.getTimestamp("start_date_time").toLocalDateTime());
        dbo.setEndDateTime(resultSet.getTimestamp("end_date_time").toLocalDateTime());
        dbo.setNumber(resultSet.getInt("number"));
        return dbo;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
