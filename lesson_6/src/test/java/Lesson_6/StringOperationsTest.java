package Lesson_6;

import org.testng.Assert;
import org.testng.annotations.*;


import static org.testng.Assert.*;

public class StringOperationsTest {

    @Test
    public void testSearchInString() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.searchInString("lathe", "vise");
        Assert.assertFalse(actualResult, "not OK!");
    }

    @Test
    public void testUpperString() {
        StringOperations stringOperations = new StringOperations();
        String actualResult = stringOperations.upperString("grow");
        String expectedResult = "GROW";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSplitStringOnParts() {
        StringOperations stringOperations = new StringOperations();
        String[] actualResult = stringOperations.splitStringOnParts("1234", "-");
        String[] expectedResult = {"1234"};
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getStrings() {
        return new Object[][]{
                {"longest", "short", "longest"},
                {"name", "properties", "properties"},
                {"_", "__", "__"}
        };
    }

    @Test(dataProvider = "getStrings")
    public void testFindLongestString(String str3, String str4, String expectedResultFind) {
        StringOperations stringOperations = new StringOperations();
        String actualResult = stringOperations.findLongestString(str3, str4);
        Assert.assertEquals(actualResult, expectedResultFind);
    }

    @Test
    public void testModificationSpaceToPointOInStringArray() {
        StringOperations stringOperations = new StringOperations();
        char[] actualResult = stringOperations.modificationSpaceToPointOInStringArray("1 0");
        char[] expectedResult = {'1', '.', '0'};
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFindLastIndexOfSymbolInString() {
        StringOperations stringOperations = new StringOperations();
        int actualResult = stringOperations.findLastIndexOfSymbolInString("Password", 's');
        int expectedResult = 3;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckNumberOrNot() {
        StringOperations stringOperations = new StringOperations();
        boolean actualResult = stringOperations.checkNumberOrNot('4', '1', '5');
        Assert.assertTrue(actualResult, "ERROR, check it!");
    }
    @Test
    public void testModificationCommaToPointOInStringArray () {
        StringOperations stringOperations = new StringOperations();
        char [] actualResult = stringOperations.modificationCommaToPointOInStringArray ("1,1");
        char [] expectedResult = {'1','.','1'};
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testRemoveSpacesInString() {
        StringOperations stringOperations = new StringOperations();
        String actualResult = stringOperations.removeSpacesInString("     All over the world ");
        String expectedResult = "All over the world";
        Assert.assertEquals(actualResult, expectedResult);
    }
}