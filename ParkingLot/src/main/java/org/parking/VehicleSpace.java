package org.parking;

/**
 * Vehicle space for floors
 *
 */
public class VehicleSpace {
    private int spaceNo;
    private boolean available;
    private VehicleType vehicleType ;
    private Vehicle parkedVehicle;

    public VehicleSpace(int spaceNumber, VehicleType vehicleType) {
        this.spaceNo = spaceNumber;
        this.vehicleType = vehicleType;
        this.available = true;
    }

    public int getSpaceNumber() {
        return spaceNo;
    }

    public boolean isAvailable() {
        return available;
    }

    // parking on space
    public void park(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.available = false;
    }

    // free up space
    public void leave() {
        this.parkedVehicle = null;
        this.available = true;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
