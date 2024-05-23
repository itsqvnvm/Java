import java.util.*;
import java.io.*;

public class BaseConverter {
    public String convert(String number, int baseFrom, int baseTo) {
        return Integer.toString(Integer.parseInt(number, baseFrom), baseTo);
    }
}

class Main {
    private BaseConverter baseConverter = new BaseConverter();
    private Scanner scanner = new Scanner(System.in);
    private PrintWriter printWriter;

    public Main() {
        try {
            printWriter = new PrintWriter(new FileOutputStream("output.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            System.out.println("1. Convert number");
            System.out.println("2. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 1) {
                System.out.print("Enter the number: ");
                String number = scanner.nextLine();
                System.out.print("Enter the base of the number: ");
                int baseFrom = scanner.nextInt();
                System.out.print("Enter the base to convert to: ");
                int baseTo = scanner.nextInt();
                scanner.nextLine();  // consume newline

                String result = baseConverter.convert(number, baseFrom, baseTo);
                System.out.println("Result: " + result);
                printWriter.println("Converted " + number + " from base " + baseFrom + " to base " + baseTo + ": " + result);
                printWriter.flush();
            } else if (choice == 2) {
                printWriter.close();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}