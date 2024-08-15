package storage;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractArrayStorageTest{

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
