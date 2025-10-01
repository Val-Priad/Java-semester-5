package org.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final int id;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Недостатньо коштів");
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
