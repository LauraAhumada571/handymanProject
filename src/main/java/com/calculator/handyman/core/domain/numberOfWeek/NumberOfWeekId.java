package com.calculator.handyman.core.domain.numberOfWeek;

import org.apache.commons.lang3.Validate;

public class NumberOfWeekId {
    private final String value;

    public NumberOfWeekId(String value) {
        Validate.notNull(value, "Number of week id can not be null");
        Validate.notBlank(value, "Number of week id can not be blank");
        Validate.isTrue(value.trim().length() == 36, "Number of week id must be 36 characteres");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
