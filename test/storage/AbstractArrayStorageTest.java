package storage;

import exceptions.ExistStorageException;
import exceptions.NotExistStorageException;
import exceptions.StorageException;
import model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class  AbstractArrayStorageTest {

    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final Resume RESUME1 = new Resume(UUID_1);
    protected final Resume RESUME2 = new Resume(UUID_2);
    protected final Resume RESUME3 = new Resume(UUID_3);
    protected final Resume RESUME4 = new Resume("uuid4Test");

    protected final Storage storage;
    protected final int size = 3;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

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
    void saveIfStorageCrowded() {
        while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
            Resume r = new Resume();
            storage.save(r);
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void updateIfNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME4));
    }

    @Test
    void updateIfExist() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));

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
        assertEquals(size, storage.getAll().length);
    }

}