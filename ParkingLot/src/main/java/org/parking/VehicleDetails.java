package org.parking;

/**
 * vehicle details and fees
 * used while retrieve of vehicle
 */
public class VehicleDetails {
    private Vehicle vehicle;
    private double fee;

    public VehicleDetails(Vehicle vehicle, double fee) {
        this.vehicle = vehicle;
        this.fee = fee;
    }
    public String toString() {
        return "REG NO: " + vehicle.getRegistrationNumber() + ", Fee: " + fee;
    }
}
