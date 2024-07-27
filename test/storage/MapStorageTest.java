package storage;

import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapStorageTest {

    Storage storage = new MapStorage();

    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final String UUID_4 = "uuid4Test";
    protected final Resume RESUME1 = new Resume(UUID_1, "Name");
    protected final Resume RESUME2 = new Resume(UUID_2, "Name");
    protected final Resume RESUME3 = new Resume(UUID_3, "Name");
    protected final Resume RESUME4 = new Resume(UUID_4, "Name");

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    void size() {
        storage.size();
        System.out.println(storage.size());
    }

    @Test
    void clear() {
        storage.clear();
    }

    @Test
    void updateIfExis() {
        Resume newResume = new Resume("uuid1Test", "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test
    void updateIfNotExis() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME4));
    }

    @Test
    void save() {
        storage.save(RESUME4);
        assertGet(RESUME4);
        assertSize(4);
    }

    @Test
    void delete() {
        storage.delete(RESUME1.getUuid());
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME1.getUuid()));
        assertSize(2);
    }

    @Test
    void get() {
        assertGet(RESUME1);
    }

    @Test
    void getAll() {
        assertEquals(storage.size(), storage.getAllSorted().size());
    }
    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
}