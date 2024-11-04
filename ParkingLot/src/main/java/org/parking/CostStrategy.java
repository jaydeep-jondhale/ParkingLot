package org.parking;

import java.util.Map;

/**
 * to configure cost for vehicle
 * need to provide cost structure which include vehicle type and its parking cost (per hour)
 */

public class CostStrategy {


    private Map<VehicleType, Double> costStructure;

    public CostStrategy(Map<VehicleType, Double> costStructure) {
        this.costStructure = costStructure;
    }

    public double calculateCost(Vehicle vehicle, int hours) {
        double rate;

        switch (vehicle.getVehicleType()) {
            case CAR:
                rate = costStructure.get(vehicle.getVehicleType());
                break;
            case SPORTS_CAR:
                rate = costStructure.get(vehicle.getVehicleType());
                break;
            case BUS:
                rate = costStructure.get(vehicle.getVehicleType());
                break;
            case BIKE:
                rate = costStructure.get(vehicle.getVehicleType());
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicle.getVehicleType());
        }
        return rate * hours;
    }
}
