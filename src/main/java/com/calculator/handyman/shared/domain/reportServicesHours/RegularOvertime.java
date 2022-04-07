package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class RegularOvertime {
    private final Double value;

    public RegularOvertime(Double value) {
        Validate.notNull(value, "Regular overtime can not be null");
        Validate.isTrue(value >= 0, "Regular overtime must be a possitive number");
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
