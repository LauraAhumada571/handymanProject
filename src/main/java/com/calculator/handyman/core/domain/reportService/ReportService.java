package com.calculator.handyman.core.domain.reportService;

import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.technician.TechnicianId;
import org.apache.commons.lang3.Validate;

import java.time.temporal.ChronoUnit;

public class ReportService {
    private final TechnicianId technicianId;
    private final ServiceId serviceId;
    private final ReportServiceStartDateTime startDateTime;
    private final ReportServiceEndDateTime endDateTime;

    public ReportService(TechnicianId technicianId, ServiceId serviceId, ReportServiceStartDateTime startDateTime, ReportServiceEndDateTime endDateTime) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.startDateTime = startDateTime;
        Validate.isTrue(endDateTime.getValue().isAfter(startDateTime.getValue()), "The end date cannot be earlier than the start date");
        Validate.isTrue(endDateTime.getValue().isBefore(startDateTime.getValue().plusDays(1)), "The end date cannot exceed 24 hours from the start date");
        this.endDateTime = endDateTime;
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
}
