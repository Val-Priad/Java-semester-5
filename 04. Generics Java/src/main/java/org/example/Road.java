package org.example;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<?>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        return carsInRoad.stream().mapToInt(Vehicle::getSeatsTaken).sum();
    }

    public void addVehicleToRoad(Vehicle<?> vehicle) {
        carsInRoad.add(vehicle);
    }

}
