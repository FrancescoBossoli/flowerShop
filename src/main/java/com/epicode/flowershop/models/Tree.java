package com.epicode.flowershop.models;

public class Tree extends SellableItem {

    public Tree(String name, double price) {
        super(name, price);
    }

    @Override
    public String getName() {
        return name + " Tree";
    }
}