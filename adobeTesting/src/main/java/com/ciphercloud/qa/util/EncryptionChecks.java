package com.ciphercloud.qa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CipherCloud on 20/6/14.
 */
public class EncryptionChecks {
    private static String regexForStarType1 = "(?i)(\\Q**\\E|\\Q*\\E(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?\\Q*\\E)((?:.(?<!\\Q**\\E))*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q!\\E|!|\\Q%21\\E|%(?:%3Cwbr%3E|\\Q\\x253Cwbr\\x253E\\E|\\Q\\u00253Cwbr\\u00253E\\E|%253Cwbr%253E)?2(?:%3Cwbr%3E|\\Q\\x253Cwbr\\x253E\\E|\\Q\\u00253Cwbr\\u00253E\\E|%253Cwbr%253E)?1\\Q%21\\E|%(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\u0025253Cwbr\u0025253E\\E|%25253Cwbr%25253E)?2(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\u0025253Cwbr\u0025253E\\E|%25253Cwbr%25253E)?1))";
    private static String regexForStarType2 = "(\\Q**\\E)((?:.(?<!\\Q**\\E))*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q!\\E|\\Q%21\\E|\\Q%21\\E))";
    private static String regexForStarType3 = "(\\Q**\\E)(.*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q!\\E|\\Q%21\\E|\\Q%21\\E))";
    private static String regexToSupportZqx_1 = "(\\Qzqx1\\E)((?:.(?<!\\Qzqx1\\E))*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q1xqz\\E))";
    private static String regexToSupportZqx_2 = "(\\Qzqx1\\E)(.*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q1xqz\\E))";
    private static String regexToSupportZqx_3 = "(\\Qzqx*\\E)((?:.(?<!\\Qzqx*\\E))*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q*xqz\\E))";
    private static String regexToSupportZqx_4 = "(\\Qzqx*\\E)(.*?(?:\\d))(([0-9A-Za-z+/]{2})(\\Q*xqz\\E))";
    private static String regexToSupportOverHaul1 = "(?i)(\\Q**\\E|\\Q*\\E(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?\\Q*\\E)((?:[^\\\\_]*[\\\\_]{1})([^\\d]{1}|[0-9a-z]{2}))((?:.(?<!\\Q**\\E))*?)([0-9]{0,5})(\\Q!\\E|!|\\Q%21\\E|%(?:%3Cwbr%3E|\\Q\\x253Cwbr\\x253E\\E|\\Q\\u00253Cwbr\\u00253E\\E|%253Cwbr%253E)?2(?:%3Cwbr%3E|\\Q\\x253Cwbr\\x253E\\E|\\Q\\u00253Cwbr\\u00253E\\E|%253Cwbr%253E)?1\\Q%2521\\E|%(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\\u0025253Cwbr\\u0025253E\\E|%25253Cwbr%25253E)?2(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\\u0025253Cwbr\\u0025253E\\E|%25253Cwbr%25253E)?5(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\\u0025253Cwbr\\u0025253E\\E|%25253Cwbr%25253E)?2(?:%253Cwbr%253E|\\Q\\x25253Cwbr\\x25253E\\E|\\Q\\u0025253Cwbr\\u0025253E\\E|%25253Cwbr%25253E)?1)";
    private static String regexToSupportOverHaul2 = "(?i)(\\Qzq.x\\E|z(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?q(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?\\Q.\\E(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?x)((?:[^\\\\_]*[\\\\_]{1})([^\\d]{1}|[0-9a-z]{2}))((?:.(?<!\\Qzq.x\\E))*?)([0-9]{0,5})(\\Qx.qz\\E|x(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?\\Q.\\E(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?q(?:&lt;wbr&gt;|<wbr>|\\Q\\x3cwbr\\x3e\\E|\\Q\\u003cwbr\\u003e\\E|%3Cwbr%3E)?z)";
    private static String regexForDateTokenizer = "\\d{1,4}[- /\\.]\\s?\\d{1,4}\\s?[- /\\.]\\s?\\d{1,4}";
    private static String regexForCurrencyTokenizer = "(9[,|.]?\\d{3}[,|.]?\\d{3}[,|.]?\\d{3}[,|.]?\\d{3}[,|.]?\\d{2}3(E14)?)(.\\d{1,4})? ";
    private static String portRegex = "http[s]://.*(:.*?)/";

    public static Pattern patterMatchesStarType1 = Pattern
            .compile(regexForStarType1);
    public static Pattern patterMatchesStarType2 = Pattern
            .compile(regexForStarType2);
    public static Pattern patterMatchesStarType3 = Pattern
            .compile(regexForStarType3);
    public static Pattern patterMatchesZqxType1 = Pattern
            .compile(regexToSupportZqx_1);
    public static Pattern patternMatchesZqxType2 = Pattern
            .compile(regexToSupportZqx_2);
    public static Pattern patternMatchesZqxType3 = Pattern
            .compile(regexToSupportZqx_3);
    public static Pattern patternMatchesZqxType4 = Pattern
            .compile(regexToSupportZqx_4);
    public static Pattern patterMathcesOverhaulType1 = Pattern
            .compile(regexToSupportOverHaul1);
    public static Pattern patternMatchesOverhaulType2 = Pattern
            .compile(regexToSupportOverHaul2);
    public static Pattern patternMatchesDateTokenizer = Pattern
            .compile(regexForDateTokenizer);
    public static Pattern patternMatchesCurrencyTokenizer = Pattern
            .compile(regexForCurrencyTokenizer);
    public static Pattern patternToGetPortNumberFromUrl = Pattern
            .compile(portRegex);


    /**
     *
     * @param encryptedValue
     * @param inputValue
     * @return
     */
    public boolean encryptionDataAssert(String encryptedValue, String inputValue) {
        int inputLength = encryptedValue.length();
        int count = 0;
        for (int i = 0; i < inputLength; i++) {
            char eachChar = encryptedValue.charAt(i);
            if (!Character.isLetterOrDigit(eachChar)) {
                count++;
            }
        }

        if (inputLength == count) {
            return true;
        }
        if (encryptedValue != null) {
            if (encryptedValue.equals(inputValue)) {
                return false;
            }
            Matcher matcherForStarType1 = patterMatchesStarType1
                    .matcher(encryptedValue);
            Matcher matcherForStarType2 = patterMatchesStarType2
                    .matcher(encryptedValue);
            Matcher matcherForStarType3 = patterMatchesStarType3
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_1 = patterMatchesZqxType1
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_2 = patternMatchesZqxType2
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_3 = patternMatchesZqxType3
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_4 = patternMatchesZqxType4
                    .matcher(encryptedValue);
            Matcher matcherToSupportOverhaul_1 = patterMathcesOverhaulType1
                    .matcher(encryptedValue);
            Matcher matcherToSupportOverhaul_2 = patternMatchesOverhaulType2
                    .matcher(encryptedValue);
            Matcher matcherForDateTokenizer = patternMatchesDateTokenizer
                    .matcher(encryptedValue);
            Matcher matcherForCurrencyTokenizer = patternMatchesCurrencyTokenizer
                    .matcher(encryptedValue);

            while (matcherForStarType1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }
            while (matcherForStarType2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForStarType3.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_3.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_4.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportOverhaul_1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportOverhaul_2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForDateTokenizer.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForCurrencyTokenizer.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

        }
        return false;
    }
    
    public static boolean verifyEncryptedText(String encryptedValue) {
        int inputLength = encryptedValue.length();
        int count = 0;
        for (int i = 0; i < inputLength; i++) {
            char eachChar = encryptedValue.charAt(i);
            if (!Character.isLetterOrDigit(eachChar)) {
                count++;
            }
        }

        if (inputLength == count) {
            return true;
        }
        if (encryptedValue != null) {
            Matcher matcherForStarType1 = patterMatchesStarType1
                    .matcher(encryptedValue);
            Matcher matcherForStarType2 = patterMatchesStarType2
                    .matcher(encryptedValue);
            Matcher matcherForStarType3 = patterMatchesStarType3
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_1 = patterMatchesZqxType1
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_2 = patternMatchesZqxType2
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_3 = patternMatchesZqxType3
                    .matcher(encryptedValue);
            Matcher matcherToSupportZqx_4 = patternMatchesZqxType4
                    .matcher(encryptedValue);
            Matcher matcherToSupportOverhaul_1 = patterMathcesOverhaulType1
                    .matcher(encryptedValue);
            Matcher matcherToSupportOverhaul_2 = patternMatchesOverhaulType2
                    .matcher(encryptedValue);
            Matcher matcherForDateTokenizer = patternMatchesDateTokenizer
                    .matcher(encryptedValue);
            Matcher matcherForCurrencyTokenizer = patternMatchesCurrencyTokenizer
                    .matcher(encryptedValue);

            while (matcherForStarType1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }
            while (matcherForStarType2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForStarType3.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_3.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportZqx_4.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportOverhaul_1.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherToSupportOverhaul_2.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForDateTokenizer.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

            while (matcherForCurrencyTokenizer.find()) {
                //System.out.println("Encrypted value " + encryptedValue);
                return true;
            }

        }
        return false;
    }
    
}
