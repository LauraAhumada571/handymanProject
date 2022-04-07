package com.calculator.handyman.infraestructure.gateways.repositories.models;

import com.calculator.handyman.core.domain.reportService.ReportService;
import com.calculator.handyman.core.domain.reportService.ReportServiceEndDateTime;
import com.calculator.handyman.core.domain.reportService.ReportServiceStartDateTime;
import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.technician.TechnicianId;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportServiceDBO {
    private String technicianId;
    private String serviceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public ReportServiceDBO(String technicianId, String serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public ReportServiceDBO() {
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
}
