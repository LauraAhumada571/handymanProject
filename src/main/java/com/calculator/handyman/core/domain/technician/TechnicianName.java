package com.calculator.handyman.core.domain.technician;

import org.apache.commons.lang3.Validate;

public class TechnicianName {
    private final String value;

    public TechnicianName(String value) {
        Validate.notNull(value, "Technician name can not be null");
        Validate.notBlank(value, "Technician name can not be blank");
        Validate.isTrue(value.trim().length() == 20, "Technician name must be 20 characteres");
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
