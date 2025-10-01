package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Vehicle<T extends Passenger> {
    private final int capacity;
    private List<T> passengers;

    public Vehicle(int capacity) {
        this.capacity = capacity;
        passengers = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsTaken() {
        return passengers.size();
    }

    public int getFreeSeats() {
        return capacity - getSeatsTaken();
    }


    public void addPassenger(T passenger) {
        if (vehicleIsFull()) {
            throw new IllegalStateException(
                    "Can't add passenger when vehicle is full");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new NoSuchElementException("Passenger not found");
        }
    }

    public boolean vehicleIsFull() {
        return getSeatsTaken() == capacity;
    }

}
