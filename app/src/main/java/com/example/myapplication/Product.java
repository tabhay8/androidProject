package com.example.myapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private String pizza_name;
    private double pizza_price;
    private double pizza_discount;

    public String getPizza_name() {
        return pizza_name;
    }

    public void setPizza_name(String pizza_name) {
        this.pizza_name = pizza_name;
    }

    public double getPizza_price() {
        return pizza_price;
    }

    public void setPizza_price(double pizza_price) {
        this.pizza_price = pizza_price;
    }

    public double getPizza_discount() {
        return pizza_discount;
    }

    public void setPizza_discount(double pizza_discount) {
        this.pizza_discount = pizza_discount;
    }
}
