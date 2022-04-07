package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class NightHours {
    private final Double value;

    public NightHours(Double value) {
        Validate.notNull(value, "Nigth hours can not be null");
        Validate.isTrue(value >= 0, "Nigth hours must be a possitive number");
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
