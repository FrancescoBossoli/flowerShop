package com.epicode.flowershop.models;

public class SellableItem {
    String name;
    double price;

    public SellableItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    };

    public double getPrice() {
        return price;
    }
}