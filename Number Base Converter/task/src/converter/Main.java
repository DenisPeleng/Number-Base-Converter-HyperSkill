package converter;

import java.util.Scanner;

public class Main {
    static boolean isRunningProgram = true;
    static boolean isRunningSecondMenu = false;
    static int currentBaseSource = 0;
    static int currentBaseTarget = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (isRunningProgram) {
            showMenu();
        }
    }

    static void showMenu() {
        System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
        String command = scanner.nextLine();

        if ("/exit".equals(command)) {
            isRunningProgram = false;
        } else {
            isRunningSecondMenu = true;
            while (isRunningSecondMenu) {
                showSecondLevelMenu(command);
            }
        }
    }

    private static void showSecondLevelMenu(String command) {
        String[] bases = command.split(" ");
        currentBaseSource = Integer.parseInt(bases[0]);
        currentBaseTarget = Integer.parseInt(bases[1]);
        System.out.printf("Enter number in base %d to convert to base %d (To go back type /back)%n", currentBaseSource, currentBaseTarget);
        String newCommand = scanner.nextLine();
        if ("/back".equals(newCommand)) {
            currentBaseSource = 0;
            currentBaseTarget = 0;
            isRunningSecondMenu = false;
        } else {
            String resultNumber;
            if (newCommand.contains(".")) {
                resultNumber = NumberConvertors.universalFractionalConverter(newCommand, currentBaseSource, currentBaseTarget);

            } else {
                resultNumber = NumberConvertors.universalIntegerConverter(newCommand, currentBaseSource, currentBaseTarget);

            }
            System.out.printf("Conversion result: %s%n%n", resultNumber);
        }
    }
}