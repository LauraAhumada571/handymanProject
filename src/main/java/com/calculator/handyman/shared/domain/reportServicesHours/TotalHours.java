package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class TotalHours {
    private final Double value;

    public TotalHours(Double value) {
        Validate.notNull(value, "Total hours can not be null");
        Validate.isTrue(value >= 0, "Total hours must be a possitive number");
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value + "";
    }
}
