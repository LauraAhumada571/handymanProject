package com.calculator.handyman.shared.domain.startAndEndTimes;

import com.calculator.handyman.shared.domain.reportServicesHours.ReportServiceHours;

import java.time.LocalDateTime;

public class StartAndEndTimes {
    private ReportServiceHours reportServiceHours;
    private LocalDateTime startDateTimeHoursObject;
    private LocalDateTime endDateTimeHoursObject;

    public StartAndEndTimes() {
    }

    public StartAndEndTimes(ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject, LocalDateTime endDateTimeHoursObject) {
        this.reportServiceHours = reportServiceHours;
        this.startDateTimeHoursObject = startDateTimeHoursObject;
        this.endDateTimeHoursObject = endDateTimeHoursObject;
    }

    public ReportServiceHours getReportServiceHours() {
        return reportServiceHours;
    }

    public void setReportServiceHours(ReportServiceHours reportServiceHours) {
        this.reportServiceHours = reportServiceHours;
    }

    public LocalDateTime getStartDateTimeHoursObject() {
        return startDateTimeHoursObject;
    }

    public void setStartDateTimeHoursObject(LocalDateTime startDateTimeHoursObject) {
        this.startDateTimeHoursObject = startDateTimeHoursObject;
    }

    public LocalDateTime getEndDateTimeHoursObject() {
        return endDateTimeHoursObject;
    }

    public void setEndDateTimeHoursObject(LocalDateTime endDateTimeHoursObject) {
        this.endDateTimeHoursObject = endDateTimeHoursObject;
    }
}
