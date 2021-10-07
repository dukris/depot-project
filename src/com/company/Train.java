package com.company;

import java.util.Objects;

public class Train {
    private double loadCapacity;
    private int passengers;

    public Train() {

    }

    public Train(double loadCapacity, int passengers) {
        this.loadCapacity = loadCapacity;
        this.passengers = passengers;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

}
