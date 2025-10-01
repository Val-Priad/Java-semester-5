package org.example;

class Bank {
    public void transfer(Account from, Account to, int amount) {
        if (from == to) return;

        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        first.lock();
        second.lock();
        try {
            if (from.getBalance() >= amount) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        } finally {
            second.unlock();
            first.unlock();
        }
    }
}
