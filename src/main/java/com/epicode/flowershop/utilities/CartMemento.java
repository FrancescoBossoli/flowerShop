package com.epicode.flowershop.utilities;

import com.epicode.flowershop.models.SellableItem;

import java.util.List;

public class CartMemento {
    private final List<SellableItem> items;

    public CartMemento(List<SellableItem> items) {
        this.items = items;
    }

    public List<SellableItem> getItems() {
        return items;
    }
}