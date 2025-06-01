package com.epicode.flowershop.interfaces;

import com.epicode.flowershop.core.Cart;

public interface CartObserver {
    void onCheckout(Cart cart);
}