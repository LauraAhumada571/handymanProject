package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class RegularHours {
    private final Double value;

    public RegularHours(Double value) {
        Validate.notNull(value, "Regular hours can not be null");
        Validate.isTrue(value >= 0, "Regular hours must be a possitive number");
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
