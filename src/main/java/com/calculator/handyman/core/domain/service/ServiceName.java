package com.calculator.handyman.core.domain.service;

import org.apache.commons.lang3.Validate;

import javax.xml.validation.Validator;

public class ServiceName {
    private final String value;

    public ServiceName(String value) {
        Validate.notNull(value, "Service name can not be null");
        Validate.notBlank(value, "Service name can not be blank");
        Validate.isTrue(value.trim().length() == 20, "Service name must be 20 characteres");
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
