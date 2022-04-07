package com.calculator.handyman.infraestructure.controller.services;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeek;
import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekId;
import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekNumber;
import com.calculator.handyman.core.gateways.NumberOfWeekRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class NumberOfWeekServices {
    private final NumberOfWeekRepository numberOfWeekRepository;

    public NumberOfWeekServices(NumberOfWeekRepository numberOfWeekRepository) {
        this.numberOfWeekRepository = numberOfWeekRepository;
    }

    // Calculate the number of weeks according to the recorded start and end dates
    public List<NumberOfWeek> createNumberOfWeek(LocalDateTime startDateTime, LocalDateTime endDateTime){
        List<NumberOfWeek> arrayList = new ArrayList<>();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        String idStart = UUID.randomUUID().toString();
        int weekNumberStart = startDateTime.get(woy);
        int weekNumberEnd = endDateTime.get(woy);

        NumberOfWeek numberOfWeekStart = new NumberOfWeek(new NumberOfWeekId(idStart), new NumberOfWeekNumber(weekNumberStart));

        arrayList.add(numberOfWeekStart);
        numberOfWeekRepository.store(numberOfWeekStart);

        if (weekNumberStart != weekNumberEnd){
            String idEnd = UUID.randomUUID().toString();
            NumberOfWeek numberOfWeekEnd = new NumberOfWeek(new NumberOfWeekId(idEnd), new NumberOfWeekNumber(weekNumberEnd));
            arrayList.add(numberOfWeekEnd);
            numberOfWeekRepository.store(numberOfWeekEnd);
        }

        return arrayList;
    }

}
