package com.calculator.handyman.core.domain.technician;

import org.apache.commons.lang3.Validate;

public class TechnicianLastName {
    private final String value;

    public TechnicianLastName(String value) {
        Validate.notNull(value, "Technician lastname can not be null");
        Validate.notBlank(value, "Technician lastname can not be blank");
        Validate.isTrue(value.trim().length() == 30, "Technician lastname must be 30 characteres");
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
