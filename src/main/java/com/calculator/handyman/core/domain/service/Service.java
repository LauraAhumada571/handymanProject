package com.calculator.handyman.core.domain.service;

public class Service {
    private final ServiceId serviceId;
    private final ServiceName serviceName;
    private final ServiceDescription serviceDescription;

    public Service(ServiceId serviceId, ServiceName serviceName, ServiceDescription serviceDescription) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public ServiceDescription getServiceDescription() {
        return serviceDescription;
    }
}
