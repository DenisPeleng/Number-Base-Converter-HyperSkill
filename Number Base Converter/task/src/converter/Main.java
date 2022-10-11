package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number in decimal system:");
        int numberInDec = scanner.nextInt();
        System.out.println("Enter target base: ");
        int base = scanner.nextInt();
        String conversionResult = "";
        switch (base) {
            case 2 -> conversionResult = Number.numberToBin(numberInDec);
            case 8 -> conversionResult = Number.numberToOctal(numberInDec);
            case 16 -> conversionResult = Number.numberToHex(numberInDec);
            default -> System.out.println("Wrong base entered");
        }
        System.out.println("Conversion result: " + conversionResult);
    }
}
