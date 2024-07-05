package model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends Section {

    public List<Company> organization = new ArrayList<>();

//    public CompanySection(List<Company> organization) {
//        this.organization = organization;
//    }

    public List<Company> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Company> organization) {
        this.organization = organization;
    }
}
