package storage;

import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamStorage;

class ObjectStreamStorageTest extends AbstractArrayStorageTest {

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}