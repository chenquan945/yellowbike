package com.test;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Chessboard chessboard = new Chessboard();
        Station stationA = new Station();
        stationA.setX(1);
        stationA.setY(1);
        stationA.setOutBike(new ArrayList<Bike>());
        stationA.setCarList(new ArrayList<Car>());
        stationA.setInventory(30);

        Station stationB = new Station();
        stationB.setX(8);
        stationB.setY(8);
        stationB.setOutBike(new ArrayList<Bike>());
        stationB.setCarList(new ArrayList<Car>());
        stationB.setInventory(40);

        Station stationC = new Station();
        stationC.setX(4);
        stationC.setY(3);
        stationC.setOutBike(new ArrayList<Bike>());
        stationC.setCarList(new ArrayList<Car>());
        stationC.setInventory(30);

        chessboard.setA(stationA);
        chessboard.setB(stationB);
        chessboard.setC(stationC);
        List<Car> list = new ArrayList<>();
        chessboard.setCarList(list);

        for(int i=0;i<200;i++){
            chessboard.paint(i);
        }



    }
}
