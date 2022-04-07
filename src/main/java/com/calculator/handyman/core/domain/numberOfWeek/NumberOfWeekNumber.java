package com.calculator.handyman.core.domain.numberOfWeek;

import org.apache.commons.lang3.Validate;

public class NumberOfWeekNumber {
    private final Integer value;

    public NumberOfWeekNumber(Integer value) {
        Validate.notNull(value, "Number of week can not be null");
        Validate.isTrue((Math.log10(value)+1) > 0 && (Math.log10(value)+1) < 3, "Number of week must be between 1 to 2");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
