package com.epicode.flowershop.services;

import com.epicode.flowershop.core.Cart;
import com.epicode.flowershop.models.SellableItem;
import com.epicode.flowershop.templates.Shop;
import com.epicode.flowershop.utilities.CustomLogger;

import java.util.Iterator;

public class CartService {
    private static CartService instance;
    public static synchronized CartService getInstance(Shop shop, Cart cart, InventoryService inventoryService, CustomLogger logger) {
        if (instance == null) instance = new CartService(shop, cart, inventoryService, logger);
        return instance;
    }

    private CartService(Shop shop, Cart cart, InventoryService inventoryService, CustomLogger logger) {
        this.shop = shop;
        this.cart = cart;
        this.inventoryService = inventoryService;
        this.logger = logger;
    }

    private final CustomLogger logger;
    private final InventoryService inventoryService;
    private final Cart cart;
    private final Shop shop;

    public void addItem(SellableItem item) {
        cart.addItem(item);
        inventoryService.removeItem(item);
        System.out.println(item.getName() + " was correctly added to the cart");
    }

    public void removeItem(SellableItem item) {
        try {
            cart.removeItem(item);
            inventoryService.addItem(item);
        } catch (ClassCastException | NullPointerException | UnsupportedOperationException e) {
            logger.error("An error occurred while trying to delete " + item.getName() + " from the cart.");
        }
    }

    public void printCartContent() {
        Iterator<SellableItem> iterator = cart.getIterator();
        if (!iterator.hasNext()) System.out.println("The Cart is currently Empty");
        else while (iterator.hasNext()) {
            SellableItem listedItem = iterator.next();
            System.out.println(listedItem.getName() + " - " + listedItem.getPrice() + "€");
        }
    }
    public void checkout() {
        shop.checkout(cart);
        cart.clear();
    }
}