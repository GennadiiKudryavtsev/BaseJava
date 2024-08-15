package storage;

import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import model.ResumeTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("/Users/gennadykudryavtsev/Desktop/MyCode/MyCourseBJ/basejava/storage");
    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final Resume RESUME1 = new Resume(UUID_1, "Name");
    protected final Resume RESUME2 = new Resume(UUID_2, "Name");
    protected final Resume RESUME3 = new Resume(UUID_3, "Name");
    protected final Resume RESUME4 = new Resume("uuid4Test", "Name");

//    ResumeTestData resumeTestData = new ResumeTestData();
//    protected final Resume RESUME5 = resumeTestData.createResume("uuid5", "Ivanov Ivan Ivanovich");
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected final int size = 4;

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
//        storage.save(RESUME5);

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

    @Test
    void saveOverflow() {
        while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
            storage.save(new Resume("uuid"));
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume("uuid")));
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertGetNull(Resume resume) {
        assertNull(storage.get(resume.getUuid()));
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
        assertSame(newResume, storage.get(RESUME1.getUuid()));

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
//        assertGet(RESUME5);
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