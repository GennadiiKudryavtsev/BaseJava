package storage;

import com.urise.webapp.storage.AbstractFileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamStorage;

class ObjectStreamStorageTest extends AbstractArrayStorageTest {

    public ObjectStreamStorageTest() {
        super(new AbstractFileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}