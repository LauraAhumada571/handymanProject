package com.calculator.handyman.core.domain.service;

import org.apache.commons.lang3.Validate;

public class ServiceId {
    private final String value;

    public ServiceId(String value) {
        Validate.notNull(value, "Service id can not be null");
        Validate.notBlank(value, "Service id can not be blank");
        Validate.isTrue(value.trim().length() == 36, "Service id must be 36 characteres");
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
