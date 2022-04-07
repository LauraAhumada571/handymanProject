package com.calculator.handyman.shared.domain.reportServicesHours;

public class ReportServiceHours {
    private RegularHours regularHours;
    private NightHours nightHours;
    private SundayHours sundayHours;
    private RegularOvertime regularOvertime;
    private NightOvertime nightOvertime;
    private SundayOvertime sundayOvertime;
    private TotalHours totalHours;

    public ReportServiceHours() {
        this.regularHours = new RegularHours(0.0);
        this.nightHours = new NightHours(0.0);
        this.sundayHours = new SundayHours(0.0);
        this.regularOvertime = new RegularOvertime(0.0);
        this.nightOvertime = new NightOvertime(0.0);
        this.sundayOvertime = new SundayOvertime(0.0);
        this.totalHours = new TotalHours(0.0);
    }

    public ReportServiceHours(RegularHours regularHours, NightHours nightHours, SundayHours sundayHours, RegularOvertime regularOvertime, NightOvertime nightOvertime, SundayOvertime sundayOvertime, TotalHours totalHours) {
        this.regularHours = regularHours;
        this.nightHours = nightHours;
        this.sundayHours = sundayHours;
        this.regularOvertime = regularOvertime;
        this.nightOvertime = nightOvertime;
        this.sundayOvertime = sundayOvertime;
        this.totalHours = totalHours;
    }

    public RegularHours getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(RegularHours regularHours) {
        this.regularHours = regularHours;
    }

    public NightHours getNightHours() {
        return nightHours;
    }

    public void setNightHours(NightHours nightHours) {
        this.nightHours = nightHours;
    }

    public SundayHours getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(SundayHours sundayHours) {
        this.sundayHours = sundayHours;
    }

    public RegularOvertime getRegularOvertime() {
        return regularOvertime;
    }

    public void setRegularOvertime(RegularOvertime regularOvertime) {
        this.regularOvertime = regularOvertime;
    }

    public NightOvertime getNightOvertime() {
        return nightOvertime;
    }

    public void setNightOvertime(NightOvertime nightOvertime) {
        this.nightOvertime = nightOvertime;
    }

    public SundayOvertime getSundayOvertime() {
        return sundayOvertime;
    }

    public void setSundayOvertime(SundayOvertime sundayOvertime) {
        this.sundayOvertime = sundayOvertime;
    }

    public TotalHours getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(TotalHours totalHours) {
        this.totalHours = totalHours;
    }
}
