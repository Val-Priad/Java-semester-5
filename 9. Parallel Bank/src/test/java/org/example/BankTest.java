package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class BankTest {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Bank bank = new Bank();

        int accountsQty = 100;
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountsQty; i++) {
            accounts.add(new Account(i, random.nextInt(1000)));
        }

        int initialTotal = accounts.stream()
                                   .mapToInt(Account::getBalance)
                                   .sum();
        System.out.println("Initial total = " + initialTotal);

        int nThreads = 5000;
        try (ExecutorService executor = Executors.newFixedThreadPool(12)) {
            for (int i = 0; i < nThreads; i++) {
                executor.submit(() -> {
                    Account from = accounts.get(random.nextInt(accountsQty));
                    Account to = accounts.get(random.nextInt(accountsQty));
                    int amount = random.nextInt(50);
                    try {
                        bank.transfer(from, to, amount);
                    } catch (IllegalArgumentException ignored) {
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }

        int finalTotal = accounts.stream()
                                 .mapToInt(Account::getBalance)
                                 .sum();
        System.out.println("Final total = " + finalTotal);

        if (initialTotal == finalTotal) {
            System.out.println(
                    "Баланс збережений, алгоритм працює правильно");
        } else {
            System.out.println("Помилка! Баланс не співпадає");
        }
    }
}
