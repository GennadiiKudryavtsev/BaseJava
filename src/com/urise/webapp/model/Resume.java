package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;

    private final Map<ContactType, String> mapContact = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> mapSectionType = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume() {
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

    public Map<ContactType, String> getMapContact() {
        return mapContact;
    }

    public Map<SectionType, Section> getMapSectionType() {
        return mapSectionType;
    }

    public void addContact(ContactType type, String value) {
        mapContact.put(type, value);
    }

    public void addSection(SectionType type, Section section) {
        mapSectionType.put(type, section);
    }


    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mapContact=" + mapContact +
                ", mapSectionType=" + mapSectionType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) &&
                Objects.equals(mapContact, resume.mapContact) && Objects.equals(mapSectionType,
                resume.mapSectionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, mapContact, mapSectionType);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.getUuid());
    }
}
