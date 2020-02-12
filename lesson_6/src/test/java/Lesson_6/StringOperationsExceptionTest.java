package Lesson_6;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class StringOperationsExceptionTest {

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testLastSymbolInString() {
        StringOperations stringOperations = new StringOperations();
        stringOperations.lastSymbolInString("");
    }
}
