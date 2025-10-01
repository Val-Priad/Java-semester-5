package org.example;

import java.util.Scanner;
import java.util.concurrent.*;

import static org.example.MonteCarlo.runMonteCarlo;

class Main {
    private static final long ITERATIONS = 100_000_000_000L;

    public static void main(String[] args) {
        while (true) {
            Integer numThreads = readUserInput();

            if (numThreads == null) {
                System.out.println("Program terminated.");
                break;
            }

            try {
                double[] result = runMonteCarlo(numThreads);
                printResult(result[0], numThreads, result[1]);
            } catch (Exception e) {
                System.out.println("Error occurred during computation: " +
                                   e.getMessage());
            }
        }
    }

    private static Integer readUserInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("%nEnter number of threads (or 'q' to quit): ");
            String input = sc.nextLine()
                             .trim();

            if (input.equalsIgnoreCase("q") ||
                input.equalsIgnoreCase("quit")) {
                return null;
            }

            try {
                int numThreads = Integer.parseInt(input);
                if (numThreads < 1) {
                    throw new NumberFormatException();
                }
                return numThreads;
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input! Please enter a positive integer.");
            }
        }
    }

    private static void printResult(double pi, int numThreads, double timeMs) {
        System.out.printf("Calculated PI is %.10f%n", pi);
        System.out.printf("PI is %.10f%n", Math.PI);
        System.out.printf("Characters match - %d%n",
                          countMatchingPrefix(pi, Math.PI));
        System.out.printf("THREADS %d%n", numThreads);
        System.out.printf("ITERATIONS %,d%n", ITERATIONS);
        System.out.printf("TIME %.2fms%n", timeMs);
    }

    public static int countMatchingPrefix(double a, double b) {
        String s1 = String.valueOf(a);
        String s2 = String.valueOf(b);

        int minLength = Math.min(s1.length(), s2.length());
        int count = 0;

        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
