package com.calculator.handyman.shared.domain.reportServicesHours;

import org.apache.commons.lang3.Validate;

public class NightOvertime {
    private final Double value;

    public NightOvertime(Double value) {
        Validate.notNull(value, "Nigth overtime can not be null");
        Validate.isTrue(value >= 0, "Nigth overtime must be a possitive number");
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
