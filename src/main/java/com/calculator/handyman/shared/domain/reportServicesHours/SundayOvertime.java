package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class SundayOvertime {
    private final Double value;

    public SundayOvertime(Double value) {
        Validate.notNull(value, "Sunday overtime can not be null");
        Validate.isTrue(value >= 0, "Sunday overtime must be a possitive number");
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
