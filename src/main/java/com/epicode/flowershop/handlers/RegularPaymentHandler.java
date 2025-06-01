package com.epicode.flowershop.handlers;

import com.epicode.flowershop.core.Cart;

public class RegularPaymentHandler extends DiscountHandler {

    protected double handle(Cart cart, double total) {
        return cart.size() < 3 ? total : this.next.handle(cart, total);
    }
}