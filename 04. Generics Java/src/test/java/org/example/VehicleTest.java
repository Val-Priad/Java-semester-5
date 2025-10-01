package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Bus bus;
    private Taxi taxi;
    private FireEngine fireEngine;
    private PoliceCar policeCar;

    private Person person;
    private Firefighter firefighter;
    private Policeman policeman;

    @BeforeEach
    void setUp() {
        bus = new Bus(2);
        taxi = new Taxi(1);
        fireEngine = new FireEngine(2);
        policeCar = new PoliceCar(2);

        person = new Person("Val");
        firefighter = new Firefighter("Priad");
        policeman = new Policeman("Sasha");
    }


    @Test
    void testBusCanTakeAnyPassenger() {
        bus.addPassenger(person);
        bus.addPassenger(firefighter);
        assertEquals(2, bus.getSeatsTaken());
    }

    @Test
    void testTaxiCanTakeAnyPassenger() {
        taxi.addPassenger(firefighter);
        assertEquals(1, taxi.getSeatsTaken());
    }

    @Test
    void testFireTruckOnlyFiremen() {
        fireEngine.addPassenger(firefighter);
        assertEquals(1, fireEngine.getSeatsTaken());
    }

    @Test
    void testPoliceCarOnlyPolicemen() {
        policeCar.addPassenger(policeman);
        assertEquals(1, policeCar.getSeatsTaken());
    }


    @Test
    void testCannotExceedCapacity() {
        bus.addPassenger(person);
        bus.addPassenger(firefighter);
        assertThrows(IllegalStateException.class,
                     () -> bus.addPassenger(policeman));
    }

    @Test
    void testRemovePassengerNotInVehicle() {
        bus.addPassenger(person);
        assertThrows(NoSuchElementException.class,
                     () -> bus.removePassenger(firefighter));
    }


    @Test
    void testRoadCountsHumans() {
        Road road = new Road();
        bus.addPassenger(person);
        bus.addPassenger(firefighter);
        taxi.addPassenger(policeman);
        fireEngine.addPassenger(firefighter);
        fireEngine.addPassenger(firefighter);
        policeCar.addPassenger(policeman);
        policeCar.addPassenger(policeman);

        road.addVehicleToRoad(bus);
        road.addVehicleToRoad(taxi);
        road.addVehicleToRoad(policeCar);
        road.addVehicleToRoad(fireEngine);

        assertEquals(7, road.getCountOfHumans());
    }
}

