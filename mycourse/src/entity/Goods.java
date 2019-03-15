package entity;

import javax.persistence.*;


public class Goods {
    private  String id;

    private  String name;
    //id and name are both only
    private  double price;
    private  int num;

    public  void setId(String id) {
        this.id = id;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  void setNum(int num) {
        this.num = num;
    }

    public  void setPrice(double price) {
        this.price = price;
    }

    public  String getId() {
        return id;
    }

    public  String getName() {
        return name;
    }

    public  int getNum() {
        return num;
    }

    public  double getPrice() {
        return price;
    }
}
