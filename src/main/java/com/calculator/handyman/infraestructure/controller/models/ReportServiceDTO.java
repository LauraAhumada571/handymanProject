package com.calculator.handyman.infraestructure.controller.models;

import com.calculator.handyman.core.domain.reportService.ReportService;

public class ReportServiceDTO {
    private String technicianId;
    private String serviceId;
    private String startDateTime;
    private String endDateTime;

    public ReportServiceDTO() {
    }

    public ReportServiceDTO(String technicianId, String serviceId, String startDateTime, String endDateTime) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static ReportServiceDTO fromDomain(ReportService reportService){
        return new ReportServiceDTO(
                reportService.getTechnicianId().toString(),
                reportService.getServiceId().toString(),
                reportService.getStartDateTime().toString(),
                reportService.getEndDateTime().toString()
        );
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

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
