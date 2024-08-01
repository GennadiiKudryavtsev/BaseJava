package storage;

import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.*;
import com.urise.webapp.storage.strategy.ObjectStreamStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFileStorageTest{

    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final String UUID_4 = "uuid4Test";
    protected final Resume RESUME1 = new Resume(UUID_1, "Name");
    protected final Resume RESUME2 = new Resume(UUID_2, "Name");
    protected final Resume RESUME3 = new Resume(UUID_3, "Name");
    protected final Resume RESUME4 = new Resume(UUID_4, "Name");
    File file = new File("/Users/gennadykudryavtsev/Desktop/MyCode/MyCourseBJ/basejava/storage");
    protected Storage storage = new AbstractFileStorage(file, new ObjectStreamStorage());

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }
    @Test
    void doSize() {
        assertSize(3);
    }

    @Test
    void doClear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void updateIsExist() {
        Resume newResume= new Resume(UUID_1, "Name");
        storage.update(RESUME1);
        assertEquals(newResume, storage.get(RESUME1.getUuid()));
    }

    @Test
    void updateIfNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME4));
    }

    @Test
    void doSave() {
        storage.save(RESUME4);
        assertGet(RESUME4);
    }

    @Test
    void doDelete() {
        storage.delete(UUID_1);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME1.getUuid()));
        assertSize(2);
    }

    @Test
    void doGet() {
        assertGet(RESUME1);
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(RESUME4.getUuid()));
    }

    @Test
    void doCopyAll() {
        assertEquals(storage.size(), storage.getAllSorted().size());
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}