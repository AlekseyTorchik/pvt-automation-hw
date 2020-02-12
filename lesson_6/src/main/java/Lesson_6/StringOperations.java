package Lesson_6;

public class StringOperations {
    public char lastSymbolInString(String str) {
        return str.charAt(str.length() - 1);
    }

    public boolean searchInString(String str2, String strPart) {
        return str2.contains(strPart);
    }

    public String upperString(String upStr) {
        return upStr.toUpperCase();
    }

    public String[] splitStringOnParts(String str3, String splitter) {
        return str3.split(splitter);
    }

    public boolean compareStringIgnoreCase(String str4, String str5) {
        return str4.equalsIgnoreCase(str5);
    }

    public String findLongestString(String str6, String str7) {
        return str6.length() > str7.length() ? str6 : str7;
    }

    public char[] modificationSpaceToPointOInStringArray(String str8) {
        char[] chArray = str8.toCharArray();
        for (int i = 0; i < chArray.length; i++) {
            if (chArray[i] == ' ') {
                chArray[i] = '.';
            }
        }
        return chArray;
    }

    public int findLastIndexOfSymbolInString(String str9, char ch) {
        return str9.lastIndexOf(ch);
    }

    public boolean checkNumberOrNot(char chSymbol, char chBegin, char chEnd) {
        return chSymbol >= chBegin && chSymbol <= chEnd;
    }

    public char [] modificationCommaToPointOInStringArray(String str10) {
        char[] chArray = str10.toCharArray();
        for (int i = 0; i < chArray.length; i++) {
            if (chArray[i] == ',') {
                chArray[i] = '.';
            }
        }
        return chArray;
    }

    public String removeSpacesInString(String strWithSpace) {
        return strWithSpace.trim();
    }

}

