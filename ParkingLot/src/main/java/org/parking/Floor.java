package org.parking;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent floors,
 * includes floor number and spaces for vehicles
 * also has parking count and floor capacity
 *
 */
public class Floor {
    private int floorNumber;
    private Map<Integer, VehicleSpace> spaces;
    private int capacity;
    private int parkedCount;

    public Floor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.capacity = capacity;
        this.parkedCount = 0;
        this.spaces = new HashMap<>();
    }

    // can be used to add spaces as per vehicle type
    public void addSpace(VehicleSpace space) {
        spaces.put(space.getSpaceNumber(), space);

    }

    // to park an vehicle on available space
    public String parkVehicle(Vehicle vehicle) {
        if (parkedCount >= capacity) {
            throw new ParkingFullException("Parking is full on floor :" + floorNumber);
        }
        for (VehicleSpace space : spaces.values()) {
            if (space.getVehicleType().equals(vehicle.getVehicleType()) && space.isAvailable()) {
                String tokenId = System.currentTimeMillis() + "_" + vehicle.getRegistrationNumber(); // Generate unique token ID
                space.park(vehicle);
                parkedCount++;
                return tokenId; // Return the generated token ID
            }
        }
        throw new IllegalStateException("No available space for vehicle type: " + vehicle.getVehicleType());
    }

    // empty the space when any vehicle leaves
    public boolean leaveVehicle(Vehicle vehicle) {
        for (VehicleSpace space : spaces.values()) {
            if (!space.isAvailable() && space.getVehicleType().equals(vehicle.getVehicleType())) {
                space.leave();
                parkedCount--;
                return true;
            }
        }
        throw new IllegalStateException("Vehicle not found in parking.");
    }

    // Displays available spaces on current floor
    public void displayAvailableSpaces() {
        System.out.println("Floor " + floorNumber + ":");
        boolean flag = false;
        for (VehicleSpace space : spaces.values()) {
            if (space.isAvailable()) {
                flag = true;
                System.out.println("Available space: " + space.getSpaceNumber());
            }
        }
        if (!flag) {
            System.out.println("No space on Floor " + floorNumber);
        }
    }

    // Diplays status of current floor,
    // like which floor is occupied and which is available also provide vehicle type
    public void displayStatus() {
        System.out.println("Floor " + floorNumber + ":");

        for (VehicleSpace space : spaces.values()) {
            if (space.isAvailable()) {
                System.out.println("Available space for " + space.getVehicleType());
            } else {
                System.out.println("Space occupied by " + space.getVehicleType());
            }
        }

    }
}
