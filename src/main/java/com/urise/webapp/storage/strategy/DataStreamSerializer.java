package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class DataStreamSerializer implements Strategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getMapContact();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, r.getMapSectionType().entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getTextInfo());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getStrings(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((CompanySection) section).getCompanies(), org -> {
                            dos.writeUTF(org.getName());
                            dos.writeUTF(org.getWebsite());
                            writeCollection(dos, org.getPeriods(), period -> {
                                writeLocalDate(dos, period.getStartDate());
                                writeLocalDate(dos, period.getEndDate());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis)  {
        try {
            return LocalDate.parse(dis.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection = new ListSection();
                listSection.getStrings().add(dis.readUTF());
                return listSection;
            case EXPERIENCE:
            case EDUCATION:
                Company company = new Company(dis.readUTF(), dis.readUTF());
                Period period = new Period(readLocalDate(dis),
                        readLocalDate(dis), dis.readUTF(), dis.readUTF());
                CompanySection section = new CompanySection();
                company.getPeriods().add(period);
                section.getCompanies().add(company);
                return section;
            default:
                throw new IllegalStateException();
        }
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }
}


