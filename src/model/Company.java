package model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    Period period;
    private String name;
    private String website;
    private List<Period> listPeriod = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Period> getListPeriod() {
        return listPeriod;
    }

    public void setListPeriod(List<Period> listPeriod) {
        this.listPeriod = listPeriod;
    }

    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }
}
