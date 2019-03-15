package entity;

import javax.persistence.*;


public class User {
    private  String name;
    private  String password;
    private  double money;

    public  void setName(String name) {
        this.name = name;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public  String getName() {
        return name;
    }

    public  String getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }
}
