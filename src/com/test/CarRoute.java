package com.test;

public abstract class CarRoute implements IRouteStrategy{
    private int seconds;
    private int count;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
