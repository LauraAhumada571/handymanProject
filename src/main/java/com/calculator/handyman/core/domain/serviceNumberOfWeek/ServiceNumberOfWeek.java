package com.calculator.handyman.core.domain.serviceNumberOfWeek;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekId;
import com.calculator.handyman.core.domain.reportService.ReportService;
import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.technician.TechnicianId;

public class ServiceNumberOfWeek {
    private final TechnicianId technicianId;
    private final ServiceId serviceId;
    private final NumberOfWeekId numberOfWeekId;

    public ServiceNumberOfWeek(TechnicianId technicianId, ServiceId serviceId, NumberOfWeekId numberOfWeekId) {
        this.technicianId = technicianId;
        this.serviceId = serviceId;
        this.numberOfWeekId = numberOfWeekId;
    }

    public TechnicianId getTechnicianId() {
        return technicianId;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public NumberOfWeekId getNumberOfWeekId() {
        return numberOfWeekId;
    }
}
