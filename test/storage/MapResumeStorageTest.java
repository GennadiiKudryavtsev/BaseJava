package storage;

import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapResumeStorageTest {
    protected final String UUID_1 = "uuid1Test";
    protected final String UUID_2 = "uuid2Test";
    protected final String UUID_3 = "uuid3Test";
    protected final Resume RESUME1 = new Resume(UUID_1, "Name1");
    protected final Resume RESUME2 = new Resume(UUID_2, "Name2");
    protected final Resume RESUME3 = new Resume(UUID_3, "Name3");
    protected final Resume RESUME4 = new Resume("uuid4Test", "Name4");

    Storage storage = new MapResumeStorage();

    @BeforeEach
    void setUp() {
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
        assertSize(0);
    }

    @Test
    void doUpdate() {
        Resume newResume = new Resume("uuid2Test", "Name2");
        storage.update(newResume);
        assertSame(newResume.getUuid(), RESUME2.getUuid());
    }

    @Test
    void doSave() {
        storage.save(RESUME4);
        assertSize(4);
    }

    @Test
    void doDelete() {
        storage.delete(RESUME1.getUuid());
        assertSize(2);
    }

    @Test
    void doGet() {
        assertEquals(RESUME2, storage.get(UUID_2));
    }
    @Test
    void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertSize(3);
        assertEquals(list, Arrays.asList(RESUME2, RESUME3, RESUME1));

    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }
}