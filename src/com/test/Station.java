package com.test;

import java.util.List;

public class Station {
    private int x;
    private int y;
    private int inventory;
    private List<Bike> outBike;
    private List<Car> carList;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<Bike> getOutBike() {
        return outBike;
    }

    public void setOutBike(List<Bike> outBike) {
        this.outBike = outBike;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
