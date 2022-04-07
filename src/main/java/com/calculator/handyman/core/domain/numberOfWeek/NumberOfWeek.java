package com.calculator.handyman.core.domain.numberOfWeek;

public class NumberOfWeek {
    private final NumberOfWeekId numberOfWeekId;
    private final NumberOfWeekNumber numberOfWeekNumber;

    public NumberOfWeek(NumberOfWeekId numberOfWeekId, NumberOfWeekNumber numberOfWeekNumber) {
        this.numberOfWeekId = numberOfWeekId;
        this.numberOfWeekNumber = numberOfWeekNumber;
    }

    public NumberOfWeekId getNumberOfWeekId() {
        return numberOfWeekId;
    }

    public NumberOfWeekNumber getNumberOfWeekNumber() {
        return numberOfWeekNumber;
    }
}
