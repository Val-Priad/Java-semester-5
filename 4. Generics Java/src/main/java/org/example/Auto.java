package org.example;

public class Auto<T extends Passenger> extends Vehicle<T>  {

    public Auto(int capacity) {
        super(capacity);
    }
}
