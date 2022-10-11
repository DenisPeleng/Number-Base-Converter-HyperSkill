package converter;

public class Number {

    public static String numberToHex(int numberInDec) {
        return universalConverter(numberInDec, 16);
    }

    public static String numberToOctal(int numberInDec) {
        return universalConverter(numberInDec, 8);
    }

    public static String numberToBin(int numberInDec) {
        return universalConverter(numberInDec, 2);
    }

    private static String universalConverter(int numberInDec, int base) {

        StringBuilder result = new StringBuilder();
        int wholeNumber = numberInDec;
        while (wholeNumber != 0) {
            if (base == 16) {
                result.append(Integer.toHexString(wholeNumber % base));
            } else {
                result.append(wholeNumber % base);
            }

            wholeNumber = wholeNumber / base;
        }
        result.reverse();
        return result.toString();
    }


}
