package com.epicode.flowershop.handlers;

import com.epicode.flowershop.core.Cart;

public class BigOrderDiscountHandler extends DiscountHandler {

    protected double handle(Cart cart, double total) {
        return total * 0.9;
    }
}
