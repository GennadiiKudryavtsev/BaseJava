package storage;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractArrayStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
