package storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                MapStorageTest.class,
                MapResumeStorageTest.class,
                ListStorageTest.class,
                FileStorageTest.class,
                PathStorageTest.class,
                XmlPathStorageTest.class,
                JsonPathStorageTest.class
        })
public class AllStorageTest {
}
