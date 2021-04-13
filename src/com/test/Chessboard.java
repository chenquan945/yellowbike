package com.test;

import java.util.List;
import java.util.Random;

public class Chessboard {
    private Station a;
    private Station b;
    private Station c;

    private List<Car> carList;

    private List<Bike> list;

    public Station getA() {
        return a;
    }

    public void setA(Station a) {
        this.a = a;
    }

    public Station getB() {
        return b;
    }

    public void setB(Station b) {
        this.b = b;
    }

    public Station getC() {
        return c;
    }

    public void setC(Station c) {
        this.c = c;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Bike> getList() {
        return list;
    }

    public void setList(List<Bike> list) {
        this.list = list;
    }

    public void paint(int i){

        int total = 0;

        List<Car> aCarList = a.getCarList();
        for (Car car : aCarList) {
          total+=car.getCount();
        }
        List<Car> bCarList = b.getCarList();
        for (Car car : bCarList) {
            total+=car.getCount();
        }
        List<Car> cCarList = c.getCarList();
        for (Car car : cCarList) {
            total+=car.getCount();
        }

        System.out.println("第"+i+"秒：A站车"+a.getInventory()+",B站车"+b.getInventory()+",C站车"+c.getInventory()+
                ",路上车"+(a.getOutBike().size()+b.getOutBike().size()+c.getOutBike().size())+"运输中的车"+total);
        random();
    }

    public void random(){
        Random bikeRandom = new Random();
        int bikeFlag = bikeRandom.nextInt(3);
        //A
        if(bikeFlag==0){
            Random routeRandom = new Random();
            int routeFlag = routeRandom.nextInt(2);
            if(a.getInventory()>=1){
                if(routeFlag==0){
                    //AToB
                    if(a.getOutBike().size()==0){
                        //起点出发
                        a.setInventory(a.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(1);
                        bike.setY(2);
                        a.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            a.setInventory(a.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(1);
                            bike.setY(2);
                            a.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(a.getOutBike().size());
                            Bike bike = a.getOutBike().get(listFlag);
                            if((bike.getX()==1&&bike.getY()<=4)||(bike.getX()==6&&bike.getY()<=6)){
                                bike.setY(bike.getY()+1);
                            }else{
                                bike.setX(bike.getX()+1);
                            }
                            if(bike.getX()==8&&bike.getY()==8){
                                b.setInventory(b.getInventory()+1);
                                a.getOutBike().remove(bike);
                            }

                        }
                    }




                }else {
                    //AToC
                    if(a.getOutBike().size()==0){
                        //起点出发
                        a.setInventory(a.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(2);
                        bike.setY(1);
                        a.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            a.setInventory(a.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(2);
                            bike.setY(1);
                            a.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(a.getOutBike().size());
                            Bike bike = a.getOutBike().get(listFlag);
                            if((bike.getY()==1&&bike.getX()<=3)){
                                bike.setX(bike.getX()+1);
                            }else{
                                bike.setY(bike.getY()+1);
                            }
                            //移动完后判断是否到达终点
                            if(bike.getX()==4&&bike.getY()==3){
                                c.setInventory(c.getInventory()+1);
                                a.getOutBike().remove(bike);
                            }

                        }
                    }
                }
            }else{
                //A没有库存，随机从B和C调车
                if(this.getCarList().size()==2){
                    random();
                }else{
                    Random random = new Random();
                    int i = random.nextInt(2);
                    if(i==0){
                        //从B调
                        if(b.getCarList().size()==0){
                            //从B发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(b.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(5);
                            car.setStep(1);
                            car.setCount(min);
                            b.getCarList().add(car);
                            this.getCarList().add(car);
                            b.setInventory(b.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = b.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                a.setInventory(a.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                b.getCarList().remove(car);
                            }
                        }


                    }else{
                        //从C调




                        if(c.getCarList().size()==0){
                            //从B发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(c.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(2);
                            car.setStep(1);
                            car.setCount(min);
                            c.getCarList().add(car);
                            this.getCarList().add(car);
                            c.setInventory(c.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = c.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                a.setInventory(a.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                c.getCarList().remove(car);
                            }
                        }
                    }
                    random();
                }



            }

        }
        //B
        else if(bikeFlag==1){
            Random routeRandom = new Random();
            int routeFlag = routeRandom.nextInt(2);
            if(b.getInventory()>=1){
                if(routeFlag==0){
                    //BToC
                    if(b.getOutBike().size()==0){
                        //起点出发
                        b.setInventory(b.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(8);
                        bike.setY(7);
                        b.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            b.setInventory(b.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(8);
                            bike.setY(7);
                            b.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(b.getOutBike().size());
                            Bike bike = b.getOutBike().get(listFlag);
                            if((bike.getX()==8&&bike.getY()>=4)){
                                bike.setY(bike.getY()-1);
                            }else{
                                bike.setX(bike.getX()-1);
                            }
                            //移动完后判断是否到达终点
                            if(bike.getX()==4&&bike.getY()==3){
                                c.setInventory(c.getInventory()+1);
                                b.getOutBike().remove(bike);
                            }

                        }
                    }
                }else {
                    //BToA
                    if(b.getOutBike().size()==0){
                        //起点出发
                        b.setInventory(b.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(8);
                        bike.setY(7);
                        b.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            b.setInventory(b.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(8);
                            bike.setY(7);
                            b.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(b.getOutBike().size());
                            Bike bike = b.getOutBike().get(listFlag);
                            if((bike.getY()==7&&bike.getX()>=7)){
                                bike.setX(bike.getX()-1);
                            }else{
                                bike.setY(bike.getY()-1);
                            }
                            //移动完后判断是否到达终点
                            if(bike.getX()==1&&bike.getY()==1){
                                a.setInventory(a.getInventory()+1);
                                b.getOutBike().remove(bike);
                            }

                        }
                    }
                }
            }else{

                //B没有库存，随机从A和C调车
                if(this.getCarList().size()==2){
                    random();
                }else{
                    Random random = new Random();
                    int i = random.nextInt(2);
                    if(i==0){
                        //从A调
                        if(a.getCarList().size()==0){
                            //从A发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(a.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(5);
                            car.setStep(1);
                            car.setCount(min);
                            a.getCarList().add(car);
                            this.getCarList().add(car);
                            a.setInventory(a.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = a.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                b.setInventory(b.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                a.getCarList().remove(car);
                            }
                        }


                    }else{
                        //从C调




                        if(c.getCarList().size()==0){
                            //从C发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(c.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(3);
                            car.setStep(1);
                            car.setCount(min);
                            c.getCarList().add(car);
                            this.getCarList().add(car);
                            c.setInventory(c.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = c.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                b.setInventory(b.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                c.getCarList().remove(car);
                            }
                        }
                    }
                    random();
                }
            }

        }
        //C
        else{
            Random routeRandom = new Random();
            int routeFlag = routeRandom.nextInt(2);
            if(c.getInventory()>=1){
                if(routeFlag==0){
                    //CToA
                    if(c.getOutBike().size()==0){
                        //起点出发
                        c.setInventory(c.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(4);
                        bike.setY(2);
                        c.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            c.setInventory(c.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(4);
                            bike.setY(2);
                            c.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(c.getOutBike().size());
                            Bike bike = c.getOutBike().get(listFlag);
                            if((bike.getX()==4&&bike.getX()>=2)){
                                bike.setY(bike.getY()-1);
                            }else{
                                bike.setX(bike.getX()-1);
                            }
                            //移动完后判断是否到达终点
                            if(bike.getX()==1&&bike.getY()==1){
                                a.setInventory(a.getInventory()+1);
                                c.getOutBike().remove(bike);
                            }

                        }
                    }
                }else {
                    //CtoB
                    if(c.getOutBike().size()==0){
                        //起点出发
                        c.setInventory(c.getInventory()-1);
                        Bike bike = new Bike();
                        bike.setX(5);
                        bike.setY(3);
                        c.getOutBike().add(bike);
                    }else{
                        Random outRandom = new Random();
                        int outFlag = outRandom.nextInt(2);
                        if(outFlag==0){
                            //起点出发
                            c.setInventory(c.getInventory()-1);
                            Bike bike = new Bike();
                            bike.setX(5);
                            bike.setY(3);
                            c.getOutBike().add(bike);
                        }else{
                            //继续出发
                            Random listRandom = new Random();
                            int listFlag = listRandom.nextInt(c.getOutBike().size());
                            Bike bike = c.getOutBike().get(listFlag);
                            if((bike.getY()==3&&bike.getX()<=7)){
                                bike.setX(bike.getX()+1);
                            }else{
                                bike.setY(bike.getY()+1);
                            }
                            //移动完后判断是否到达终点
                            if(bike.getX()==8&&bike.getY()==8){
                                b.setInventory(b.getInventory()+1);
                                c.getOutBike().remove(bike);
                            }

                        }
                }
            }
        }else{
                //C没有库存，随机从A和B调车
                if(this.getCarList().size()==2){
                    random();
                }else{
                    Random random = new Random();
                    int i = random.nextInt(2);
                    if(i==0){
                        //从A调
                        if(a.getCarList().size()==0){
                            //从A发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(a.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(2);
                            car.setStep(1);
                            car.setCount(min);
                            a.getCarList().add(car);
                            this.getCarList().add(car);
                            a.setInventory(a.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = a.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                c.setInventory(c.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                a.getCarList().remove(car);
                            }
                        }


                    }else{
                        //从B调




                        if(b.getCarList().size()==0){
                            //从B发起
                            Random numRandom1 = new Random();
                            int num1 = numRandom1.nextInt(b.getInventory());
                            Random numRandom2 = new Random();
                            int num2 = numRandom2.nextInt(20);
                            int min = Math.min(num1, num2);
                            Car car = new Car();
                            car.setTotalStep(3);
                            car.setStep(1);
                            car.setCount(min);
                            b.getCarList().add(car);
                            this.getCarList().add(car);
                            b.setInventory(b.getInventory()-min);
                        }else{
                            //在路上
                            List<Car> carList = b.getCarList();
                            Random random1 = new Random();
                            int i1 = random1.nextInt(carList.size());
                            Car car = carList.get(i1);
                            car.setStep(car.getStep()+1);
                            //到达后加库存
                            if(car.getTotalStep()==car.getStep()){
                                c.setInventory(c.getInventory()+car.getCount());
                                this.getCarList().remove(car);
                                b.getCarList().remove(car);
                            }
                        }
                    }
                    random();
                }
        }
        }
    }

}
