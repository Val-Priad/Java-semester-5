package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[32m";
    private static final String CYAN = "\033[36m";

    public static void main(String[] args) {
        while (true) {
            String input = getUserInput();

            if (isQuit(input)) {
                System.out.println("Exiting program...");
                break;
            }

            try {
                TagSorter sorter = new TagSorter(input);

                List<Map.Entry<String, Integer>> byName =
                        sorter.sortByTagName();
                List<Map.Entry<String, Integer>> byFreq =
                        sorter.sortByFrequency();

                prettyPrintResult(byName, byFreq);

            } catch (IOException e) {
                System.out.println(
                        "Invalid or unreachable URL: " + e.getMessage());
            }
        }
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter URL (or 'q'/'quit' to exit): ");
        return scanner.nextLine()
                      .trim();
    }

    private static boolean isQuit(String input) {
        return input.equalsIgnoreCase("q") ||
               input.equalsIgnoreCase("quit");
    }

    private static void prettyPrintResult(
            List<Map.Entry<String, Integer>> byName,
            List<Map.Entry<String, Integer>> byFreq) {
        System.out.printf("%-30s%-30s%n",
                          GREEN + "Sorted by Tag Name" + RESET,
                          CYAN + "Sorted by Frequency" + RESET);

        int max = Math.max(byName.size(), byFreq.size());
        for (int i = 0; i < max; i++) {
            String left = i < byName.size()
                    ? String.format("%-30s", byName.get(i)
                                                   .getKey() + ": " +
                                             byName.get(i)
                                                   .getValue())
                    : "";
            String right = i < byFreq.size()
                    ? String.format("%-30s", byFreq.get(i)
                                                   .getKey() + ": " +
                                             byFreq.get(i)
                                                   .getValue())
                    : "";
            System.out.printf("%s%-30s%s%s%-30s%s%n",
                              GREEN, left, RESET,
                              CYAN, right, RESET);
        }

    }
}
