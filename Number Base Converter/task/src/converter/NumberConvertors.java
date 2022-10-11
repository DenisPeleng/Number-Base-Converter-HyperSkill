package converter;

public class NumberConvertors {

    public static String numberToHex(int numberInDec) {
        return Integer.toHexString(numberInDec);
    }

    public static String numberToOctal(int numberInDec) {
        return Integer.toOctalString(numberInDec);
    }

    public static String numberToBin(int numberInDec) {
        return Integer.toBinaryString(numberInDec);
    }

    public static int fromBinToDec(String numberInBin) {
        return Integer.parseInt(numberInBin, 2);
    }

    public static int fromOctToDec(String numberInOct) {
        return Integer.parseInt(numberInOct, 8);
    }

    public static int fromHexToDec(String numberInHex) {
        return Integer.parseInt(numberInHex, 16);
    }
}
