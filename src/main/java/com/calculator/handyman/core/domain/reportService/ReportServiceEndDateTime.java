package com.calculator.handyman.core.domain.reportService;

import java.time.LocalDateTime;
import org.apache.commons.lang3.Validate;

public class ReportServiceEndDateTime {
    private final LocalDateTime value;

    public ReportServiceEndDateTime(LocalDateTime value) {
        Validate.notNull(value, "End date time of service can not be null");
        Validate.isTrue(value.isBefore(LocalDateTime.now()), "The end date cannot be after the current date");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReportServiceEndDateTime{" +
                "value=" + value +
                '}';
    }
}
