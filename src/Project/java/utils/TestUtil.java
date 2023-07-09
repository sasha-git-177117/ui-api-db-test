package utils;

import models.TestInfoModel;

import java.util.Base64;
import java.util.List;
import java.util.Random;

public class TestUtil {
    private TestUtil() {}

    public static Integer getNumberFromString(String str) {
        StringBuilder myNumbers = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                myNumbers.append(str.charAt(i));
            }
        }
        return Integer.parseInt(myNumbers.toString());
    }

    public static String getGeneratorText(int firstSymbol, int lastSymbol, int countSymbols) {
        Random random = new Random();

        return random.ints(firstSymbol,
                lastSymbol + 1)
                .limit(countSymbols)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String parseByteArrayToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    public static <T> boolean isAllListItemsContainsInList(List<T> uiTests, List<T> dbTests) {
        for (T uiTest: uiTests) {
            if(!dbTests.contains(uiTest)) return false;
        }
        return true;
    }

    public static TestInfoModel getTestInfoByTestName(String testName, List<TestInfoModel> uiTests) {
        for (TestInfoModel uiTest: uiTests) {
            if(uiTest.getName().equals(testName)) return uiTest;
        }
        return new TestInfoModel();
    }
}
