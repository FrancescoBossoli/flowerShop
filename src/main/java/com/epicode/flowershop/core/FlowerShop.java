package com.epicode.flowershop.core;

import com.epicode.flowershop.handlers.DiscountHandler;
import com.epicode.flowershop.templates.Shop;

public class FlowerShop extends Shop {
    private final DiscountHandler handler;

    public FlowerShop(Inventory inventory, DiscountHandler handler) {
        super(inventory);
        this.handler = handler;
    }

    protected double calculateTotal(Cart cart) {
        double raw = cart.getTotal();
        return handler.applyDiscount(cart, raw);
    }
}
