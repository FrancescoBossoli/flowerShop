package com.epicode.flowershop.templates;

import com.epicode.flowershop.core.Cart;
import com.epicode.flowershop.core.Inventory;
import com.epicode.flowershop.interfaces.CartObserver;
import com.epicode.flowershop.models.SellableItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Shop {
    protected Inventory inventory;
    protected List<CartObserver> observers = new ArrayList<>();

    public Shop(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addObserver(CartObserver obs) {
        observers.add(obs);
    }

    public final void checkout(Cart cart) {
        Iterator<SellableItem> iterator = cart.getIterator();
        int items = 0;
        while (iterator.hasNext()) {
            items++;
            iterator.next();
        }
        double total;
        total = calculateTotal(cart);
        System.out.println("In total you paid " + total + "€");
        if (items > 2) System.out.println("You received a " + (items > 7 ? 10 : 5) + "% of discount since you had " + items + " items in your cart");

        observers.forEach(obs -> obs.onCheckout(cart));
    }

    protected abstract double calculateTotal(Cart cart);
}
