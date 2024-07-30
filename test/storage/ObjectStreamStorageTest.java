package storage;

import com.urise.webapp.storage.ObjectStreamStorage;
import com.urise.webapp.storage.Storage;

class ObjectStreamStorageTest extends AbstractArrayStorageTest {

    public ObjectStreamStorageTest(Storage storage) {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}