package org.parking;
/**
 * vehicle details which is going to park
 * includes its type, registration number and color
 */
public class Vehicle {
    private VehicleType vehicleType;
    private String registrationNumber;
    private String color;

    public Vehicle(VehicleType type, String registrationNumber, String color) {
        this.vehicleType = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}
