import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Fibonacci {
    public BigInteger fibonacciRecursive(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return n;
        }
        return fibonacciRecursive(n.subtract(BigInteger.ONE)).add(fibonacciRecursive(n.subtract(BigInteger.valueOf(2))));
    }

    public BigInteger fibonacciDynamic(int n) {
        BigInteger[] fib = new BigInteger[n + 2];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }

        return fib[n];
    }

    public BigInteger fibonacciMatrix(int n) {
        BigInteger F[][] = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        if (n == 0) {
            return BigInteger.ZERO;
        }
        power(F, n - 1);

        return F[0][0];
    }

    private void multiply(BigInteger F[][], BigInteger M[][]) {
        BigInteger x = F[0][0].multiply(M[0][0]).add(F[0][1].multiply(M[1][0]));
        BigInteger y = F[0][0].multiply(M[0][1]).add(F[0][1].multiply(M[1][1]));
        BigInteger z = F[1][0].multiply(M[0][0]).add(F[1][1].multiply(M[1][0]));
        BigInteger w = F[1][0].multiply(M[0][1]).add(F[1][1].multiply(M[1][1]));

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    private void power(BigInteger F[][], int n) {
        if (n == 0 || n == 1) {
            return;
        }
        BigInteger M[][] = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};

        power(F, n / 2);
        multiply(F, F);

        if (n % 2 != 0) {
            multiply(F, M);
        }
    }

    public void calculateAndPrintFibonacci(int n, int choice, PrintWriter printWriter) {
        long startTime = 0, endTime = 0;
        BigInteger result = BigInteger.ZERO;

        switch (choice) {
            case 1:
                startTime = System.currentTimeMillis();
                result = fibonacciRecursive(BigInteger.valueOf(n));
                endTime = System.currentTimeMillis();
                break;
            case 2:
                startTime = System.currentTimeMillis();
                result = fibonacciDynamic(n);
                endTime = System.currentTimeMillis();
                break;
            case 3:
                startTime = System.currentTimeMillis();
                result = fibonacciMatrix(n);
                endTime = System.currentTimeMillis();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                System.exit(0);
        }
        double timeInSeconds = (endTime - startTime) / 1000.0;

        System.out.println("Fibonacci number at position " + n + ": " + result);
        System.out.println("Time to calculate: " + timeInSeconds + " seconds");
        printWriter.println("INPUT: " + n + ", Method " + choice + ": " + result + ", Time: " + timeInSeconds + " seconds");
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter("fibonacci.txt", true));
        } catch (IOException e) {
            System.out.println("Error opening file fibonacci.txt: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.print("Enter the position of the Fibonacci number: ");
        int n = scanner.nextInt();

        System.out.println("1. Recursive method");
        System.out.println("2. Dynamic programming method");
        System.out.println("3. Matrix multiplication method");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        fibonacci.calculateAndPrintFibonacci(n, choice, printWriter);

        printWriter.close();
    }
}