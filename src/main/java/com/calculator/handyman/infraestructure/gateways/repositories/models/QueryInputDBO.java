package com.calculator.handyman.infraestructure.gateways.repositories.models;

import java.time.LocalDateTime;

public class QueryInputDBO {
    private String technicianId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public QueryInputDBO(String technicianId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.technicianId = technicianId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
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
