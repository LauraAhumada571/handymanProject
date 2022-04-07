package com.calculator.handyman.infraestructure.controller.services;

import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeek;
import com.calculator.handyman.core.domain.numberOfWeek.NumberOfWeekId;
import com.calculator.handyman.core.domain.reportService.ReportService;
import com.calculator.handyman.core.domain.reportService.ReportServiceEndDateTime;
import com.calculator.handyman.core.domain.reportService.ReportServiceStartDateTime;
import com.calculator.handyman.core.domain.service.ServiceId;
import com.calculator.handyman.core.domain.serviceNumberOfWeek.ServiceNumberOfWeek;
import com.calculator.handyman.core.domain.technician.TechnicianId;
import com.calculator.handyman.core.gateways.ReportServiceRepository;
import com.calculator.handyman.core.services.ReportServiceDomainService;
import com.calculator.handyman.infraestructure.controller.models.ReportServiceDTO;
import com.calculator.handyman.infraestructure.controller.models.ReportServiceInput;
import com.calculator.handyman.infraestructure.gateways.repositories.models.QueryInputDBO;
import com.calculator.handyman.shared.domain.ReportServiceWeek;
import com.calculator.handyman.shared.domain.reportServicesHours.ReportServiceHours;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ReportServices {
    private final ReportServiceRepository reportServiceRepository;
    private final ServiceNumberOfWeekServices serviceNumberOfWeekServices;
    private final NumberOfWeekServices numberOfWeekServices;
    private final ReportServiceDomainService reportServiceDomainService;

    public ReportServices(ReportServiceRepository reportServiceRepository, ServiceNumberOfWeekServices serviceNumberOfWeekServices, NumberOfWeekServices numberOfWeekServices, ReportServiceDomainService reportServiceDomainService) {
        this.reportServiceRepository = reportServiceRepository;
        this.serviceNumberOfWeekServices = serviceNumberOfWeekServices;
        this.numberOfWeekServices = numberOfWeekServices;
        this.reportServiceDomainService = reportServiceDomainService;
    }

    public ReportServiceDTO storeReportService (ReportServiceInput reportServiceInput){
        List<ServiceNumberOfWeek> serviceNumberOfWeek = new ArrayList<>();
        ReportService reportService = new ReportService(
                new TechnicianId(reportServiceInput.getTechnicianId()),
                new ServiceId(reportServiceInput.getServiceId()),
                new ReportServiceStartDateTime(reportServiceInput.getStartDateTime()),
                new ReportServiceEndDateTime(reportServiceInput.getEndDateTime()));
        reportServiceRepository.store(reportService);
        List<NumberOfWeek> listNumberWeek = numberOfWeekServices.createNumberOfWeek(reportService.getStartDateTime().getValue(), reportService.getEndDateTime().getValue());

        for(int i = 0; i < listNumberWeek.size(); i++){
            serviceNumberOfWeek.add(new ServiceNumberOfWeek (new TechnicianId(reportService.getTechnicianId().getValue()),
                    new ServiceId(reportService.getServiceId().getValue()), new NumberOfWeekId(listNumberWeek.get(i).getNumberOfWeekId().getValue())));
        }
        serviceNumberOfWeekServices.storeServiceNumberOfWeek(serviceNumberOfWeek);

        return ReportServiceDTO.fromDomain(reportService);
    }

    //Method that returns the list with the number of hours per week, in unordered
    public Object getListReportServiceWeek(String technicianId, int numberOfWeek) {
        QueryInputDBO queryInputDBO = calculatedDateRange(technicianId, numberOfWeek);
        List<ReportServiceWeek> reportServiceWeekList = reportServiceRepository.getListReport(queryInputDBO);
        ReportServiceHours reportServiceHoursDomain = reportServiceDomainService.getAllHours(reportServiceWeekList, numberOfWeek);
        return reportServiceHoursDomain;
    }

    //Method that allows me to calculate the date range to make the query in SQL
    private static final QueryInputDBO calculatedDateRange (String technicianId, int numberOfWeek){
        LocalDate startDateWeek = getLocalDate(numberOfWeek, DayOfWeek.SUNDAY, 2022, Locale.US);
        LocalDate endDateWeek = getLocalDate(numberOfWeek, DayOfWeek.SATURDAY, 2022, Locale.US);
        LocalTime startTime = LocalTime.of(00,00,00);
        LocalTime endTime = LocalTime.of(23,59,59);
        LocalDateTime startDateTime = LocalDateTime.of(startDateWeek, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDateWeek, endTime);

        QueryInputDBO queryInputDBO = new QueryInputDBO(
                technicianId,
                startDateTime,
                endDateTime
        );

        return queryInputDBO;
    }

    //Method that returns the date according to the requested week and day of the week
    private static final LocalDate getLocalDate(int weekNumber, DayOfWeek dayOfWeek, int year, Locale locale) {
        return LocalDate.now()
                .with(dayOfWeek)
                .with(WeekFields.of(locale).weekOfWeekBasedYear(), weekNumber);
    }

}
