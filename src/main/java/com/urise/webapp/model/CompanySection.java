package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private static final long serialVersionUID = 1L;

    public List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public CompanySection() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}
