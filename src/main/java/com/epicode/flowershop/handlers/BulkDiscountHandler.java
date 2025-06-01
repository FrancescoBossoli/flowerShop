package com.epicode.flowershop.handlers;

import com.epicode.flowershop.core.Cart;

public class BulkDiscountHandler extends DiscountHandler {

    protected double handle(Cart cart, double total) {
        return cart.size() < 7 ? total * 0.95 : this.next.handle(cart, total);
    }
}