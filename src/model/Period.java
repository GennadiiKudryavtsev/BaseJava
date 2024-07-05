package model;

import java.time.LocalDate;

public class Period {
    private LocalDate startDay;
    private LocalDate endDay;
    private String title;
    private String description;

    public Period(LocalDate startDay, LocalDate endDay, String title, String description) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.title = title;
        this.description = description;
    }

    public Period(LocalDate startDay, LocalDate endDay, String description) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.description = description;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
