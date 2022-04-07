package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class SundayHours {
    private final Double value;

    public SundayHours(Double value) {
        Validate.notNull(value, "Sunday hours can not be null");
        Validate.isTrue(value >= 0, "Sunday hours must be a possitive number");
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
