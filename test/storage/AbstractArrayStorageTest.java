package storage;

import exceptions.ExistStorageException;
import exceptions.NotExistStorageException;
import exceptions.StorageException;
import model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class  AbstractArrayStorageTest {

    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final Resume resume1 = new Resume(UUID_1);
    protected final Resume resume2 = new Resume(UUID_2);
    protected final Resume resume3 = new Resume(UUID_3);

    protected final Storage storage;
    protected final int size = 3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void size() {
        assertEquals(size, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        assertGet(resume4);
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
        Resume resume4 = new Resume("uuid4Test");
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(resume4));
    }

    @Test
    void updateIfExist() {
        Resume resume4 = new Resume("uuid1Test");
        assertThrows(ExistStorageException.class, () -> storage.update(resume4));
    }

    @Test
    void delete() {
        storage.delete(resume1.getUuid());
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(resume1.getUuid()));
        assertSize(size - 1);
    }

    @Test
    void get() {
        assertGet(resume1);
    }

    @Test
    void getNotExist() {
        Resume resume4 = new Resume("uuid4Test");
        assertGetNull(resume4);
    }

    @Test
    void getAll() {
        assertEquals(size, storage.getAll().length);
    }
}