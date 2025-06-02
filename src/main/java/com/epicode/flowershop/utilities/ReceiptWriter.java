package com.epicode.flowershop.utilities;

import com.epicode.flowershop.interfaces.CartObserver;
import com.epicode.flowershop.models.SellableItem;

import java.util.List;

public class ReceiptWriter implements CartObserver {
    @Override
    public void onCheckout(List<SellableItem> list) {
        double modifier = 1;
        int totalItems = list.size();
        if (totalItems > 2) modifier -= (totalItems > 7) ? 0.1 :  0.05;
        double finalModifier = modifier;
        System.out.print("""
            -------------------------------------------------
            Printing Customer Receipt
            -------------------------------------------------
            """ );
        list.forEach(article ->
            System.out.println(article.getName() + " - " + String.format("%.2f", (article.getPrice() * finalModifier)) + "€")
        );
    }
}
