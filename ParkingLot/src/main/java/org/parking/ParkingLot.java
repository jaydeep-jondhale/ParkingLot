package org.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parking lot for managing parking
 * need to provide cost structure to initialize
 * and
 */
public class ParkingLot {
    private List<Floor> floors;
    private Map<String, Vehicle> parkedVehicles;
    private Map<VehicleType, Double> costStructure;

    public ParkingLot(Map<VehicleType, Double> costStructure) {
        this.floors = new ArrayList<>();
        this.parkedVehicles = new HashMap<>();
        this.costStructure = costStructure;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }


    // finds available space on floor and parks vehicle there
    // and returns a token, token includes vehicle reg number and
    // current timestamp(used further for calculating fees)
    public String parkVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            try {
                String tokenId = floor.parkVehicle(vehicle);
                parkedVehicles.put(tokenId, vehicle);
                return tokenId; // Return the generated token ID
            } catch (IllegalStateException e) {
                // Continue trying next floors if parking fails
            }
        }
        throw new IllegalStateException("Parking is full; cannot park vehicle: " + vehicle.getRegistrationNumber());
    }

    // frees up the space and returns vehicle details along with fee
    public VehicleDetails retrieveVehicle(String tokenId) {
        Vehicle vehicle = parkedVehicles.get(tokenId);
        System.out.println("Leaving vehicle TokenId :" + tokenId);
        // timestamp when vehicle was parked
        long timestamp = Long.parseLong(tokenId.split("_")[0]); // getting timestamp, parking time
        for (Floor floor : floors) {
            // leave the space
            boolean leave = floor.leaveVehicle(vehicle);
            if (leave) {
                break;
            }
        }
        if (vehicle != null) {
            // Calculate parking duration and fee
            long parkedDuration = System.currentTimeMillis() - timestamp; // Duration in milliseconds
            // Convert duration to hours
            int hours = (int) (parkedDuration / (1000 * 60 * 60));
            CostStrategy costStrategy = new CostStrategy(costStructure);
            double fee = costStrategy.calculateCost(vehicle, hours);

            return new VehicleDetails(vehicle, fee);
        }
        throw new IllegalStateException("Invalid token ID: " + tokenId);
    }


    // Display Available Spaces
    public void displayAvailableSpaces() {
        for (Floor floor : floors) {
            floor.displayAvailableSpaces();
        }
    }
    // Display Status
    public void displayStatus() {
        System.out.println("Total Number of Vehicles Parked : "+parkedVehicles.size());
        for (Floor floor : floors) {
            floor.displayStatus();
        }
    }


}
