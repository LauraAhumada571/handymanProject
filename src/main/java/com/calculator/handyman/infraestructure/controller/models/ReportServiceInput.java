package com.calculator.handyman.infraestructure.controller.models;

import java.time.LocalDateTime;

public class ReportServiceInput {
    private String technicianId;
    private String serviceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public ReportServiceInput() {
    }

    public ReportServiceInput(String technicianId, String serviceId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
