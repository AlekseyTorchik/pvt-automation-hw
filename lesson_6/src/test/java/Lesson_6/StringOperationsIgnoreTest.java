package Lesson_6;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class StringOperationsIgnoreTest {
    @Ignore
    @Test
    public void testCompareStringIgnoreCase() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.compareStringIgnoreCase("fast", "FAST");
        Assert.assertTrue(actualResult, "not OK!");
    }
}
