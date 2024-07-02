package model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private final Map<SectionsContact, String> mapContact = new EnumMap<>(SectionsContact.class);
    private final Map<SectionType, String> mapSectionType = new EnumMap<>(SectionType.class);
    private final List<TextSection> listInfo = new ArrayList<>();
    private final List<CompanySection> listCompany = new ArrayList<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<SectionsContact, String> getMapContact() {
        return mapContact;
    }

    public Map<SectionType, String> getMapSectionType() {
        return mapSectionType;
    }

    public List<TextSection> getListInfo() {
        return listInfo;
    }

    public List<CompanySection> getLsitCompany() {
        return listCompany;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mapContact=" + mapContact +
                ", mapSectionType=" + mapSectionType +
                ", listInfo=" + listInfo +
                ", listCompany=" + listCompany +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) &&
                Objects.equals(mapContact, resume.mapContact) && Objects.equals(mapSectionType,
                resume.mapSectionType) && Objects.equals(listInfo, resume.listInfo) &&
                Objects.equals(listCompany, resume.listCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, mapContact, mapSectionType, listInfo, listCompany);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.getUuid());
    }
}
