package com.epicode.flowershop.utilities;

import com.epicode.flowershop.models.SellableItem;

import java.util.Iterator;
import java.util.List;

public class CartIterator implements Iterator<SellableItem> {
    private final List<SellableItem> items;
    private int index = 0;

    public CartIterator(List<SellableItem> items) { this.items = items; }

    public boolean hasNext() { return index < items.size(); }
    public SellableItem next() { return items.get(index++); }
}