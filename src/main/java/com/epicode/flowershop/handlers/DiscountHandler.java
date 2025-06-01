package com.epicode.flowershop.handlers;

import com.epicode.flowershop.core.Cart;

public abstract class DiscountHandler {
    protected DiscountHandler next;

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    public double applyDiscount(Cart cart, double total) {
        return handle(cart, total);
    }

    protected abstract double handle(Cart cart, double total);
}