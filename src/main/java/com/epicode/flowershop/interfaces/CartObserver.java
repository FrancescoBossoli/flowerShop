package com.epicode.flowershop.interfaces;

import com.epicode.flowershop.models.SellableItem;

import java.util.List;

public interface CartObserver {
    void onCheckout(List<SellableItem> list);
}