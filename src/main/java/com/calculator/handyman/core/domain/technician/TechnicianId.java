package com.calculator.handyman.core.domain.technician;

import org.apache.commons.lang3.Validate;

public class TechnicianId {
    private final String value;

    public TechnicianId(String value) {
        Validate.notNull(value, "Technician id can not be null");
        Validate.notBlank(value, "Technician id can not be blank");
        Validate.isTrue(value.trim().length() >= 6 && value.trim().length() <= 10, "Technician id  must be between 6 to 10 characteres");
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
