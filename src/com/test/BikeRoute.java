package com.test;

public abstract class BikeRoute implements IRouteStrategy{
    private Station src;
    private Station dest;

    public Station getSrc() {
        return src;
    }

    public void setSrc(Station src) {
        this.src = src;
    }

    public Station getDest() {
        return dest;
    }

    public void setDest(Station dest) {
        this.dest = dest;
    }
}
