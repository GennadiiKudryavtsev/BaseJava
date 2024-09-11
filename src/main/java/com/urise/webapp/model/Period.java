package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Period() {
    }

    public Period(LocalDate startDay, LocalDate endDay, String title, String description) {
        this.startDate = startDay;
        this.endDate = endDay;
        this.title = title;
        this.description = description;
    }

    public Period(LocalDate startDay, LocalDate endDay, String description) {
        this.startDate = startDay;
        this.endDate = endDay;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
