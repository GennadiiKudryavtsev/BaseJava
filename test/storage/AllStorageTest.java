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
        })
public class AllStorageTest {
}
