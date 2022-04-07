package com.calculator.handyman.infraestructure.controller.services;

import com.calculator.handyman.core.domain.serviceNumberOfWeek.ServiceNumberOfWeek;
import com.calculator.handyman.core.gateways.ServiceNumberOfWeekRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNumberOfWeekServices {
    private final ServiceNumberOfWeekRepository serviceNumberOfWeekRepository;

    public ServiceNumberOfWeekServices(ServiceNumberOfWeekRepository serviceNumberOfWeekRepository) {
        this.serviceNumberOfWeekRepository = serviceNumberOfWeekRepository;
    }

    public void storeServiceNumberOfWeek (List<ServiceNumberOfWeek> serviceNumberOfWeek){
        for (int i = 0; i < serviceNumberOfWeek.size(); i++){
            serviceNumberOfWeekRepository.store(serviceNumberOfWeek.get(i));
        }
    }
}
