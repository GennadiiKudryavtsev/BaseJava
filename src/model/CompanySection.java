package model;

import java.time.LocalDate;
import java.util.Date;

public class CompanySection extends Section {
    private String name;
    private String url;
    private LocalDate startDay;
    private LocalDate endDay;
    private String description;

    public CompanySection(String name, String url, LocalDate startDay, LocalDate endDay, String description) {
        this.name = name;
        this.url = url;
        this.startDay = startDay;
        this.endDay = endDay;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
