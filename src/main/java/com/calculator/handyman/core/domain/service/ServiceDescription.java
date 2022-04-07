package com.calculator.handyman.core.domain.service;

import org.apache.commons.lang3.Validate;

public class ServiceDescription {
    private final String value;

    public ServiceDescription(String value) {
        Validate.notNull(value, "Service description can not be null");
        Validate.notBlank(value, "Service description can not be blank");
        Validate.isTrue(value.trim().length() <= 500, "Service description can not be longer than 500");
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
