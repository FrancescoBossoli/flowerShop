package com.epicode.flowershop.core;

import com.epicode.flowershop.models.SellableItem;
import com.epicode.flowershop.utilities.CartMemento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private final List<SellableItem> items = new ArrayList<>();

    public void addItem(SellableItem item) {
        items.add(item);
    }

    public void removeItem(SellableItem item) {
        items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public double getTotal() {
        return items.stream()
            .mapToDouble(SellableItem::getPrice)
            .sum();
    }

    public CartMemento save() {
        return new CartMemento(new ArrayList<>(items));
    }

    public void restore(CartMemento m) {
        items.clear();
        items.addAll(m.getItems());
    }

    public CartIterator getIterator() {
        return new CartIterator();
    }

    public class CartIterator implements Iterator<SellableItem> {
        private int index = 0;

        public boolean hasNext() {
            return index < items.size();
        }

        public SellableItem next() {
            return items.get(index++);
        }
    }

    public void clear() {
        this.items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}