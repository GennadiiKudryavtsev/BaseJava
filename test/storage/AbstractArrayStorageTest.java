package storage;

import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import model.ResumeTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    protected static final File STORAGE_DIR = new File("/Users/gennadykudryavtsev/Desktop/MyCode/MyCourseBJ/basejava1/storage");
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    protected static final Resume RESUME1 = new Resume(UUID_1, "Namejjjjjjj");
    protected static final Resume RESUME2 = new Resume(UUID_2, "Name");
    protected static final Resume RESUME3 = new Resume(UUID_3, "Name");
    protected static final Resume RESUME4 = new Resume(UUID_4, "Name");

    static ResumeTestData resumeTestData = new ResumeTestData();
//    protected final Resume RESUME5 = resumeTestData.createResume("uuid5", "Ivanov Ivan Ivanovich");
    protected final Storage storage;

//    static {
//        RESUME1.addContact(ContactType.PHONE, "88888888");
//        RESUME1.addContact(ContactType.EMAIL, "mail-borisov");
//        RESUME1.addContact(ContactType.SKYPE, "skype-borisov");
//        LocalDate ldStart = LocalDate.of(2020, 2, 1);
//        LocalDate ldEnd = LocalDate.of(2022, 2, 1);
//        ListSection listSection = new ListSection("Занимался созданием проекта в рамках ТЗ");
//        Company company = new Company("OZON", "www.ozon.com");
//        Period period = new Period(ldStart, ldEnd, "Developer", "Create projext");
//        CompanySection section = new CompanySection();
//        company.getPeriods().add(period);
//        section.getCompanies().add(company);
//        RESUME1.addSection(SectionType.EXPERIENCE, section);
//
//    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected final int size = 3;

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    void initialSize() {
        assertSize(size);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);

    }

    @Test
    void save() {
        storage.save(RESUME4);
        assertGet(RESUME4);
        assertSize(size + 1);
    }

//    @Test
//    void saveOverflow() {
//        while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
//            storage.save(new Resume("uuid1"));
//        }
//        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume("uuid2")));
//    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));

    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    @Test
    void updateIfNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME4));
    }

    @Test
    void updateIfExist() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    void delete() {
        storage.delete(RESUME1.getUuid());
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME1.getUuid()));
        assertSize(size - 1);
    }

    @Test
    void get() {
        assertGet(RESUME1);
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(RESUME4.getUuid()));
    }

    @Test
    void getAll() {
        assertEquals(size, storage.getAllSorted().size());
    }
}