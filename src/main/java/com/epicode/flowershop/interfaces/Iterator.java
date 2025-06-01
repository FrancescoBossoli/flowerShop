package com.epicode.flowershop.interfaces;

import com.epicode.flowershop.models.SellableItem;

public interface Iterator {
    boolean hasNext();
    SellableItem next();
}