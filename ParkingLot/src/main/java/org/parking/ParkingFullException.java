package org.parking;

/**
 * Custom excception - if Parking is full
 */
public class ParkingFullException extends RuntimeException{
    private String message;
    public ParkingFullException(String message){
        super(message);
    }
}
