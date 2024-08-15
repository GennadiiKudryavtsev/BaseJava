package storage;

import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.strategy.DataStreamSerializer;
import com.urise.webapp.storage.strategy.ObjectStreamStorage;

class DataStreamSerializerTest extends AbstractArrayStorageTest {

    public DataStreamSerializerTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamSerializer()));
    }
}