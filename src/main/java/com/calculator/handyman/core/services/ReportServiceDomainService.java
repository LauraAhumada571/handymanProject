package com.calculator.handyman.core.services;

import com.calculator.handyman.core.domain.reportService.ReportServiceEndDateTime;
import com.calculator.handyman.core.domain.reportService.ReportServiceStartDateTime;
import com.calculator.handyman.shared.domain.ReportServiceWeek;
import com.calculator.handyman.shared.domain.reportServicesHours.*;
import com.calculator.handyman.shared.domain.startAndEndTimes.StartAndEndTimes;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportServiceDomainService {

    //Method to be invoked from the application service
    public ReportServiceHours getAllHours(List<ReportServiceWeek> reportServiceWeekList, int numberOfWeek){
        List<ReportServiceWeek> reportServiceWeekSort = sortArray(reportServiceWeekList);
        Boolean flagDifferentWeeks = differentWeeks(reportServiceWeekSort, numberOfWeek);
        ReportServiceHours reportServiceHours = new ReportServiceHours();
        List<ReportServiceWeek> reportServiceWeekModifyDates = new ArrayList<>();
        if (flagDifferentWeeks) {
            reportServiceWeekModifyDates = modifyDates(reportServiceWeekSort, numberOfWeek);
            reportServiceHours = arrayPath(reportServiceWeekModifyDates);
        } else {
            reportServiceHours = arrayPath(reportServiceWeekSort);
        }
        return reportServiceHours;
    }

    //Method to sort the array from the lowest date to the highest date according to the records of a week
    private final static List<ReportServiceWeek> sortArray(List<ReportServiceWeek> reportServiceWeekList){
        Collections.sort(reportServiceWeekList, new Comparator<ReportServiceWeek>() {
            @Override
            public int compare(ReportServiceWeek o1, ReportServiceWeek o2) {
                return o1.getStartDateTime().getValue().compareTo(o2.getStartDateTime().getValue());
            }
        });
        return reportServiceWeekList;
    }

    //Identifying whether the date lag spans two different weeks
    private final static boolean differentWeeks(List<ReportServiceWeek> reportServiceWeekSort, int numberOfWeek){
        boolean flag = false;
        for (int i = 0; i < reportServiceWeekSort.size(); i++){
            if(numberOfWeek != reportServiceWeekSort.get(i).getNumber().getValue()){
                flag = true;
            }
        }
        return flag;
    }

    //Modify the array dates leaving only the times comprising the requested week
    private final List<ReportServiceWeek> modifyDates(List<ReportServiceWeek> reportServiceWeek, int numberOfWeek){
        int arraySize = reportServiceWeek.size();
        LocalDateTime startDateTimeFirstPosition = reportServiceWeek.get(0).getStartDateTime().getValue();
        LocalDateTime startDateTimeLastPosition = reportServiceWeek.get(arraySize - 1).getStartDateTime().getValue();
        DayOfWeek j = startDateTimeLastPosition.getDayOfWeek();
        if(startDateTimeFirstPosition.getDayOfWeek() == DayOfWeek.SATURDAY){
            reportServiceWeek = calculateWhenChangeWeek(reportServiceWeek, numberOfWeek, arraySize, startDateTimeFirstPosition, startDateTimeLastPosition);
        }
        if (startDateTimeLastPosition.getDayOfWeek() == DayOfWeek.SATURDAY && reportServiceWeek.size() > 1){
            reportServiceWeek.remove(arraySize - 1);
            startDateTimeLastPosition = midnightDate(startDateTimeLastPosition.plusDays(1));
            reportServiceWeek.get(reportServiceWeek.size() - 1).setEndDateTime(new ReportServiceEndDateTime(startDateTimeLastPosition));
        }
        return reportServiceWeek;
    }

    //Set the midnight of a date
    private final static LocalDateTime midnightDate(LocalDateTime localDateTime){
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime localDateTimeFinal = LocalDateTime.of(localDate, localTime);
        return  localDateTimeFinal;
    }

    //Method to be executed when there is a change of week
    private final static List<ReportServiceWeek> calculateWhenChangeWeek(List<ReportServiceWeek> reportServiceWeek, int numberOfWeek, int arraySize,
                                                                                           LocalDateTime startDateTimeFirstPosition, LocalDateTime startDateTimeLastPosition){
        if(reportServiceWeek.get(0).getNumber().getValue() == numberOfWeek){
            reportServiceWeek.remove(arraySize - 1);
            startDateTimeLastPosition = midnightDate(startDateTimeFirstPosition.plusDays(1));
            reportServiceWeek.get(0).setEndDateTime(new ReportServiceEndDateTime(startDateTimeLastPosition));
        } else {
            reportServiceWeek.remove(0);
            startDateTimeFirstPosition = midnightDate(startDateTimeFirstPosition.plusDays(1));
            reportServiceWeek.get(0).setStartDateTime(new ReportServiceStartDateTime(startDateTimeFirstPosition));
        }
        return reportServiceWeek;
    }

    //start the array path
    private final ReportServiceHours arrayPath(List<ReportServiceWeek> reportServiceWeek){
        ReportServiceHours reportServiceHours = new ReportServiceHours();
        for(int i = 0; i < reportServiceWeek.size(); i++){
            LocalDateTime startDateTime = reportServiceWeek.get(i).getStartDateTime().getValue();
            LocalDateTime endDateTime = reportServiceWeek.get(i).getEndDateTime().getValue();
            //calculate how many hours there are between two dates
            double hoursAmount = (double) ChronoUnit.SECONDS.between(startDateTime, endDateTime)/3600;
            int hoursAmountRound = (int) Math.ceil(hoursAmount);
            if(hoursAmount < 1.0){
                hoursAmountRound = hoursAmountRound + 1;
            }

            reportServiceHours = hoursObject(reportServiceHours, startDateTime, endDateTime, hoursAmountRound);
        }
        return reportServiceHours;
    }

    //traverse the number of hours between two dates of an object
    private final ReportServiceHours hoursObject(ReportServiceHours reportServiceHours, LocalDateTime startDateTime, LocalDateTime endDateTime, int hoursAmountRound){
        LocalDateTime startDateTimeHoursObject = startDateTime;
        LocalDateTime endDateTimeHoursObject = startDateTimeHoursObject.plusHours(1);
        for(int j = 0; j < hoursAmountRound; j++){
            StartAndEndTimes startAndEndTimes = validateStartAndEndTimes(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject, j, endDateTime, hoursAmountRound);
            reportServiceHours = startAndEndTimes.getReportServiceHours();
            startDateTimeHoursObject = startAndEndTimes.getStartDateTimeHoursObject();
            endDateTimeHoursObject = startAndEndTimes.getEndDateTimeHoursObject();
        }
        return reportServiceHours;
    }

    //Method to validate start, end and less than 48 hours for each record
    private final StartAndEndTimes validateStartAndEndTimes (ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject,
                                                             LocalDateTime endDateTimeHoursObject, int position, LocalDateTime endDateTime, int hoursAmountRound){
        if((startDateTimeHoursObject.isBefore(twentyHours(startDateTimeHoursObject)) && endDateTimeHoursObject.isAfter(twentyHours(endDateTimeHoursObject)))
            && (position != hoursAmountRound -1 || hoursAmountRound == 1)){
            endDateTimeHoursObject = twentyHours(endDateTimeHoursObject);
        } else if((startDateTimeHoursObject.isBefore(sevenHours(startDateTimeHoursObject)) && endDateTimeHoursObject.isAfter(sevenHours(endDateTimeHoursObject)))
                    && (position != hoursAmountRound -1 || hoursAmountRound == 1)){
            endDateTimeHoursObject = sevenHours(endDateTimeHoursObject);
        } else if (position == hoursAmountRound -1){
            endDateTimeHoursObject = endDateTime;
        }

        if(reportServiceHours.getTotalHours().getValue() < 48 ){
            reportServiceHours = normalHours(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject);
        } else {
            reportServiceHours = overtimeHours(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject);
        }
        startDateTimeHoursObject = endDateTimeHoursObject;
        endDateTimeHoursObject = endDateTimeHoursObject.plusHours(1);
        return new StartAndEndTimes(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject);
    }

    //Method that allows me to calculate normal hours, night hours and Sunday hours
    private final ReportServiceHours normalHours(ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject, LocalDateTime endDateTimeHoursObject){
        LocalDateTime sevenHour = sevenHours(startDateTimeHoursObject);
        LocalDateTime twentyHour = twentyHours(startDateTimeHoursObject);
        LocalDateTime midnightDateDay = midnightDate(startDateTimeHoursObject);
        LocalDateTime midnightDateDayAfter = midnightDateDay.plusDays(1);

        reportServiceHours = calculateNormalHours(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject, sevenHour, twentyHour, midnightDateDay, midnightDateDayAfter);

        return reportServiceHours;
    }

    //Method for calculating regular overtime, overtime night hours and overtime Sunday hours
    private final ReportServiceHours overtimeHours(ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject, LocalDateTime endDateTimeHoursObject){
        LocalDateTime sevenHour = sevenHours(startDateTimeHoursObject);
        LocalDateTime twentyHour = twentyHours(startDateTimeHoursObject);
        LocalDateTime midnightDateDay = midnightDate(startDateTimeHoursObject);
        LocalDateTime midnightDateDayAfter = midnightDateDay.plusDays(1);

        reportServiceHours = calculateOvertimeHours(reportServiceHours, startDateTimeHoursObject, endDateTimeHoursObject, sevenHour, twentyHour, midnightDateDay, midnightDateDayAfter);

        return reportServiceHours;
    }

    //Set the 07:00:00 of a date
    private final static LocalDateTime sevenHours(LocalDateTime localDateTime){
        LocalTime localTime = LocalTime.of(07,00,00);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime localDateTimeFinal = LocalDateTime.of(localDate, localTime);
        return  localDateTimeFinal;
    }

    //Set the 20:00:00 of a date
    private final static LocalDateTime twentyHours(LocalDateTime localDateTime){
        LocalTime localTime = LocalTime.of(20,00,00);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime localDateTimeFinal = LocalDateTime.of(localDate, localTime);
        return  localDateTimeFinal;
    }

    //Calculate hours as appropriate
    private final static List<Double> calculateHours(LocalDateTime startDateTimeHoursObject, LocalDateTime endDateTimeHoursObject, double typeHours, double totalHours){
        double hoursAmount = (double)ChronoUnit.SECONDS.between(startDateTimeHoursObject, endDateTimeHoursObject)/(double)3600;
        double typeHoursAmount = hoursAmount + typeHours;
        double totalHoursFinal = hoursAmount + totalHours;
        List<Double> listHours = new ArrayList<>();
        listHours.add(typeHoursAmount);
        listHours.add(totalHoursFinal);
        return listHours;
    }

    //Calculate normal hours
    public final static ReportServiceHours calculateNormalHours(ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject,
                                                                LocalDateTime endDateTimeHoursObject, LocalDateTime sevenHour, LocalDateTime twentyHour,
                                                                LocalDateTime midnightDateDay, LocalDateTime midnightDateDayAfter){
        DayOfWeek f = startDateTimeHoursObject.getDayOfWeek();
        if ((startDateTimeHoursObject.isAfter(sevenHour) || startDateTimeHoursObject.isEqual(sevenHour))
                && (endDateTimeHoursObject.isBefore(twentyHour) || endDateTimeHoursObject.isEqual(twentyHour))
                &&  startDateTimeHoursObject.getDayOfWeek() != DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getRegularHours().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setRegularHours(new RegularHours(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
        } else if ((((startDateTimeHoursObject.isAfter(midnightDateDay) || startDateTimeHoursObject.isEqual(midnightDateDay))
                && (endDateTimeHoursObject.isBefore(sevenHour) || endDateTimeHoursObject.isEqual(sevenHour)))
                || ((startDateTimeHoursObject.isAfter(twentyHour) || startDateTimeHoursObject.isEqual(twentyHour))
                && (endDateTimeHoursObject.isBefore(midnightDateDayAfter) || endDateTimeHoursObject.isEqual(midnightDateDayAfter))))
                && startDateTimeHoursObject.getDayOfWeek() != DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getNightHours().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setNightHours(new NightHours(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
        } else if (startDateTimeHoursObject.getDayOfWeek() == DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getSundayHours().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setSundayHours(new SundayHours(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
        }
        return reportServiceHours;
    }

    //Calculate overtime hours
    private final static ReportServiceHours calculateOvertimeHours(ReportServiceHours reportServiceHours, LocalDateTime startDateTimeHoursObject,
                                                                   LocalDateTime endDateTimeHoursObject, LocalDateTime sevenHour, LocalDateTime twentyHour,
                                                                   LocalDateTime midnightDateDay, LocalDateTime midnightDateDayAfter){
        if ((startDateTimeHoursObject.isAfter(sevenHour) || startDateTimeHoursObject.isEqual(sevenHour))
                && (endDateTimeHoursObject.isBefore(twentyHour) || endDateTimeHoursObject.isEqual(twentyHour))
                &&  startDateTimeHoursObject.getDayOfWeek() != DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getRegularOvertime().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setRegularOvertime(new RegularOvertime(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
            DayOfWeek f = startDateTimeHoursObject.getDayOfWeek();
            System.out.println("");
        } else if ((((startDateTimeHoursObject.isAfter(midnightDateDay) || startDateTimeHoursObject.isEqual(midnightDateDay))
                && (endDateTimeHoursObject.isBefore(sevenHour) || endDateTimeHoursObject.isEqual(sevenHour)))
                || ((startDateTimeHoursObject.isAfter(twentyHour) || startDateTimeHoursObject.isEqual(twentyHour))
                && (endDateTimeHoursObject.isBefore(midnightDateDayAfter) || endDateTimeHoursObject.isEqual(midnightDateDayAfter))))
                && startDateTimeHoursObject.getDayOfWeek() != DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getNightOvertime().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setNightOvertime(new NightOvertime(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
            System.out.println("");
        } else if (startDateTimeHoursObject.getDayOfWeek() == DayOfWeek.SUNDAY){
            List<Double> listHoursFinal = calculateHours(startDateTimeHoursObject, endDateTimeHoursObject, reportServiceHours.getSundayOvertime().getValue(), reportServiceHours.getTotalHours().getValue());
            reportServiceHours.setSundayOvertime(new SundayOvertime(listHoursFinal.get(0)));
            reportServiceHours.setTotalHours(new TotalHours(listHoursFinal.get(1)));
            System.out.println("");
        }
        return reportServiceHours;
    }
}
