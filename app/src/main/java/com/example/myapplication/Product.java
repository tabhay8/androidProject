package com.example.myapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private String pizzaName;
    private double pizzaPrice;
    private double pizzaDiscount;

    public Product(String pizzaName, double pizzaPrice, double pizzaDiscount) {
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaDiscount = pizzaDiscount;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public double getPizzaDiscount() {
        return pizzaDiscount;
    }

    public void setPizzaDiscount(double pizzaDiscount) {
        this.pizzaDiscount = pizzaDiscount;
    }
}
