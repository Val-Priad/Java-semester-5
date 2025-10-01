
package org.example;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String literal = "Hello world";

        System.out.print("Enter string: ");
        String userInput = sc.nextLine();

        System.out.println("\n--- BEFORE ---");
        System.out.println("Literal string: " + literal);
        System.out.println("User string: " + userInput);

        replaceStringContent(literal, "Replaced literal");
        replaceStringContent(userInput, "Replaced input");

        System.out.println("\n--- AFTER ---");
        System.out.println("Literal string: " + literal);
        System.out.println("User string: " + userInput);
    }

    private static void replaceStringContent(String target, String newContent) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);

            byte[] updated = newContent.getBytes(StandardCharsets.UTF_8);

            byte[] copy = java.util.Arrays.copyOf(updated, updated.length);

            field.set(target, copy);

        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection error: " + e.getMessage());
        }
    }
}
