package converter;

import java.util.Scanner;

public class Main {
    static boolean isRunning = true;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (isRunning) {
            showMenu();
        }


    }

    static void showMenu() {
        System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
        String command = scanner.next();
        switch (command) {
            case "/from" -> showConvertFromDecMenu();
            case "/to" -> showConvertToDecMenu();
            case "/exit" -> isRunning = false;
        }
    }

    private static void showConvertToDecMenu() {
        System.out.println("Enter source number:");
        String numberInVarBase = scanner.next();
        System.out.println("Enter source base: ");
        int base = scanner.nextInt();
        int conversionResult = 0;
        switch (base) {
            case 2 -> conversionResult = NumberConvertors.fromBinToDec(numberInVarBase);
            case 8 -> conversionResult = NumberConvertors.fromOctToDec(numberInVarBase);
            case 16 -> conversionResult = NumberConvertors.fromHexToDec(numberInVarBase);
            default -> System.out.println("Wrong base entered");
        }
        System.out.println("Conversion to decimal result: " + conversionResult);
        System.out.println();
    }

    private static void showConvertFromDecMenu() {
        System.out.println("Enter number in decimal system:");
        int numberInDec = scanner.nextInt();
        System.out.println("Enter target base: ");
        int base = scanner.nextInt();
        String conversionResult = "";
        switch (base) {
            case 2 -> conversionResult = NumberConvertors.numberToBin(numberInDec);
            case 8 -> conversionResult = NumberConvertors.numberToOctal(numberInDec);
            case 16 -> conversionResult = NumberConvertors.numberToHex(numberInDec);
            default -> System.out.println("Wrong base entered");
        }
        System.out.println("Conversion result: " + conversionResult);
        System.out.println();
    }
}
