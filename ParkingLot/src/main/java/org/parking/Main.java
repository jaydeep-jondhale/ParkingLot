package org.parking;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        // Initalize parking lot
        // which includes initialization of floors
        // and configure cost and also spaces for vehicles
        ParkingLot parkingLot = main.init();


        Vehicle vehicle1 = new Vehicle(VehicleType.CAR,"MH01C1122","White");
        Vehicle vehicle2 = new Vehicle(VehicleType.CAR,"MH01C1144","Black");


        try {
            // parking both vehicles, will get token
            // token format - timestamp_registration-no (this timestamp is used while fee cal)

            String token1 = parkingLot.parkVehicle(vehicle1); // Park first car
            String token2 = parkingLot.parkVehicle(vehicle2);
            // if try to park 3rd vehicle will get error as Parking full
//            String token3 = parkingLot.parkVehicle(vehicle2);

            System.out.println("Vehicle 1 parked with token: " + token1);
            System.out.println("Vehicle 2 parked with token: " + token2);

            // retrieving vehicle1  by providing token
            VehicleDetails v1 = parkingLot.retrieveVehicle(token1);
            System.out.println("Vehicle Retrieve : "+v1);

        }catch (Exception e){
                e.printStackTrace();
        }
        parkingLot.displayStatus();
        parkingLot.displayAvailableSpaces();

    }

    public Map<VehicleType, Double> configureCostStrategy(){
        Map<VehicleType, Double> vehicleTypeDoubleMap = new HashMap<>();
        vehicleTypeDoubleMap.put(VehicleType.BIKE,10.0);
        vehicleTypeDoubleMap.put(VehicleType.CAR,20.0);
        vehicleTypeDoubleMap.put(VehicleType.BUS,30.0);
        return vehicleTypeDoubleMap;
    }

    public ParkingLot init(){
        ParkingLot parkingLot = new ParkingLot(configureCostStrategy()); // price config provided
        // adding one floor with two vehicle capacity
        Floor floor = new Floor(1,2);// floor number and capacity
        // only having space for cars - 2 cars
        floor.addSpace(new VehicleSpace(1, VehicleType.CAR));
        floor.addSpace(new VehicleSpace(2, VehicleType.CAR));


        parkingLot.addFloor(floor);
        return parkingLot;

    }


}