package com.daniel.wanjema.drone.delivery.model;

public class Enums {
    public enum DroneModel {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    public enum DroneState{
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }
}
