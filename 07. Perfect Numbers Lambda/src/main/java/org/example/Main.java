package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

    private static final String ANSI_ESCAPE = "\033[";
    private static final String COLOR_RED = "31m";
    private static final String COLOR_GREEN = "32m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter top limit int number (or 'q' to quit): ");
            String userInput = sc.nextLine();

            try {
                if (userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("quit")) {
                    break;
                }

                long topLimit = Long.parseLong(userInput);
                if (topLimit < 1) {
                    throw new NumberFormatException();
                }

                System.out.println("Below all perfect numbers below the limit: ");
                List<Long> perfectNumbers = getPerfectNumbersUpTo(topLimit);
                System.out.println(ANSI_ESCAPE + COLOR_GREEN + perfectNumbers + RESET);

            } catch (NumberFormatException e) {
                System.out.println(ANSI_ESCAPE + COLOR_RED +
                                   "incorrect input! Must be positive integer!" +
                                   RESET);
            }
        }
    }

    public static List<Long> getPerfectNumbersUpTo(Long limit) {
        if (limit < 1) throw new IllegalArgumentException();

        return Stream.iterate(2L, Main::nextPrime)
                     .filter(Main::lucasLehmer)
                     .map(p -> (1L << (p - 1)) * getMersseneNumber(p))
                     .takeWhile(n -> n <= limit)
                     .collect(Collectors.toList());
    }

    public static long getPerfectNumber(long p) {
        if (p < 1) {
            throw new IllegalArgumentException("p must be positive integer!");
        }
        if (lucasLehmer(p)) {
            return (1L << (p - 1)) * getMersseneNumber(p);
        }
        throw new IllegalArgumentException(
                "Can't get perfect number, p didn't pass Lucas Lehmer test!");
    }

    public static boolean lucasLehmer(long p) {
        if (p <= 1) {
            throw new IllegalArgumentException("p must be positive integer greater than 1!");
        }
        if (p == 2) {
            return true;
        }

        long M = getMersseneNumber(p);
        long s = LongStream.range(0, p - 2)
                           .reduce(4L, (acc, i) -> (acc * acc - 2) % M);
        return s == 0;
    }

    public static Long getMersseneNumber(long p) {
        if (p < 1) {
            throw new IllegalArgumentException("p must be positive integer!");
        }
        return ((1L << p) - 1);
    }

    public static long nextPrime(long p) {
        return LongStream.iterate(p + 1, n -> n + 1)
                         .filter(Main::isPrime)
                         .findFirst()
                         .orElseThrow();
    }

    public static boolean isPrime(long num) {
        if (num <= 1) throw new IllegalArgumentException("p must be positive integer higher than 1!");
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        return LongStream.iterate(3, i -> i * i <= num, i -> i + 2)
                         .noneMatch(i -> num % i == 0);
    }
}
