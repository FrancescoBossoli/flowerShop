package com.epicode.flowershop.interfaces;

public interface CompositionElement<T extends CompositionElement<T>> {
    void addElement(T e);
}
