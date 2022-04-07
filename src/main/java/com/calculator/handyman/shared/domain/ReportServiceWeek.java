package com.calculator.handyman.shared.domain;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekNumber;
import com.calculator.handyman.core.domain.reportService.ReportServiceEndDateTime;
import com.calculator.handyman.core.domain.reportService.ReportServiceStartDateTime;
import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.technician.TechnicianId;

public class ReportServiceWeek {
    private TechnicianId technicianId;
    private ServiceId serviceId;
    private ReportServiceStartDateTime startDateTime;
    private ReportServiceEndDateTime endDateTime;
    private NumberOfWeekNumber number;

    public ReportServiceWeek() {
    }

    public ReportServiceWeek(TechnicianId technicianId, ServiceId serviceId, ReportServiceStartDateTime startDateTime, ReportServiceEndDateTime endDateTime, NumberOfWeekNumber number) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.number = number;
    }

    public TechnicianId getTechnicianId() {
        return technicianId;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public ReportServiceStartDateTime getStartDateTime() {
        return startDateTime;
    }

    public ReportServiceEndDateTime getEndDateTime() {
        return endDateTime;
    }

    public NumberOfWeekNumber getNumber() {
        return number;
    }

    public void setStartDateTime(ReportServiceStartDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(ReportServiceEndDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
