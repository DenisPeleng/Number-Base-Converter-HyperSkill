package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


public class NumberConvertors {
    private static final String[] baseSymbols = new String[36];

    private static void fillBaseSymbols() {

        for (int i = 0; i < 36; i++) {
            if (i > 9) {
                for (char j = 'a'; j <= 'z'; j++) {
                    baseSymbols[i] = String.valueOf(j);
                    i++;
                }
            } else {
                baseSymbols[i] = String.valueOf(i);
            }
        }

    }

    public static String universalIntegerConverter(String number, int sourceBase, int targetBase) {
        fillBaseSymbols();
        BigInteger bigIntegerDec = numberIntegerToDec(number, sourceBase);
        return numberIntegerToTarget(bigIntegerDec, targetBase);
    }

    private static String numberIntegerToTarget(BigInteger bigIntegerDec, int targetBase) {
        StringBuilder result = new StringBuilder();
        BigInteger bigIntegerDivide = bigIntegerDec;
        while (bigIntegerDivide.compareTo(BigInteger.valueOf(0)) >= 1) {
            BigInteger[] bigIntegers = bigIntegerDivide.divideAndRemainder(BigInteger.valueOf(targetBase));
            int indexDigitTargetBase = Integer.parseInt(bigIntegers[1].toString());
            result.append(baseSymbols[indexDigitTargetBase]);
            bigIntegerDivide = bigIntegers[0];
        }
        String resultStr = result.reverse().toString();
        resultStr = resultStr.isEmpty() ? "0" : resultStr;
        return resultStr;
    }

    private static int indexOfElement(String value) {
        for (int i = 0; i < NumberConvertors.baseSymbols.length; i++) {
            if (NumberConvertors.baseSymbols[i].equals(value.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    private static BigInteger numberIntegerToDec(String number, int sourceBase) {
        String[] arrayWithDigits = number.split("");
        BigInteger bigIntegerResult = BigInteger.ZERO;
        BigInteger bigIntegerBase = new BigInteger(String.valueOf(sourceBase));

        for (int i = 0; i < arrayWithDigits.length; i++) {
            BigInteger fromDecTable = new BigInteger(String.valueOf(indexOfElement(arrayWithDigits[arrayWithDigits.length - 1 - i])));
            BigInteger secondNumber = bigIntegerBase.pow(i).multiply(fromDecTable);
            bigIntegerResult = bigIntegerResult.add(secondNumber);
        }

        return bigIntegerResult;
    }

    public static String universalFractionalConverter(String number, int sourceBase, int targetBase) {
        fillBaseSymbols();
        String[] numberInTwoParts = number.split("\\.");
        BigInteger bigIntegerDec = numberIntegerToDec(numberInTwoParts[0], sourceBase);
        String resultStr = numberIntegerToTarget(bigIntegerDec, targetBase);
        BigDecimal bigFractionalDec = numberFractionalToDec(numberInTwoParts[1], sourceBase);
        String resultStr2 = numberFractionalToTarget(bigFractionalDec, targetBase);


        return resultStr + "." + resultStr2;
    }


    private static BigDecimal numberFractionalToDec(String number, int sourceBase) {
        String[] arrayWithDigits = number.split("");
        BigDecimal bigDecimalResult = BigDecimal.ZERO;
        BigDecimal bigDecimalBase = new BigDecimal(String.valueOf(sourceBase));
        for (int i = 0; i < arrayWithDigits.length; i++) {
            BigDecimal fromDecTable = new BigDecimal(String.valueOf(indexOfElement(arrayWithDigits[i])));
            BigDecimal secondNumber = new BigDecimal(1).divide(bigDecimalBase.pow(i + 1), 10, RoundingMode.CEILING).multiply(fromDecTable);
            bigDecimalResult = bigDecimalResult.add(secondNumber);
        }
        return bigDecimalResult;
    }

    private static String numberFractionalToTarget(BigDecimal bigFractionalDec, int targetBase) {
        StringBuilder result = new StringBuilder();
        BigDecimal bigFractionalMulti = bigFractionalDec;
        while (bigFractionalMulti.compareTo(BigDecimal.valueOf(0)) >= 1 && result.length() < 5) {
            bigFractionalMulti = bigFractionalMulti.multiply(BigDecimal.valueOf(targetBase));
            BigDecimal integerPart = bigFractionalMulti.setScale(0, RoundingMode.DOWN);
            BigDecimal fractionalPart = bigFractionalMulti.subtract(integerPart);
            int indexDigitTargetBase = Integer.parseInt(integerPart.toString());
            result.append(baseSymbols[indexDigitTargetBase]);
            bigFractionalMulti = fractionalPart;
        }
        if (result.length() < 5) {
            result.append("0".repeat(Math.max(0, 5 - result.length())));
        }

        return result.toString();
    }
}
