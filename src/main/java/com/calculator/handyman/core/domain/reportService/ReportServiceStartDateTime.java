package com.calculator.handyman.core.domain.reportService;

import org.apache.commons.lang3.Validate;
import java.time.LocalDateTime;

public class ReportServiceStartDateTime {
    private final LocalDateTime value;

    public ReportServiceStartDateTime(LocalDateTime value) {
        Validate.notNull(value, "Start date time of service can not be null");
        Validate.isTrue(value.isBefore(LocalDateTime.now()), "The start date cannot be after the current date");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReportServiceStartDateTime{" +
                "value=" + value +
                '}';
    }
}
