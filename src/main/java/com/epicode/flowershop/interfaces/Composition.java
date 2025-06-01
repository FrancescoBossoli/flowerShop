package com.epicode.flowershop.interfaces;

public interface Composition<T extends CompositionElement<T>> extends CompositionElement<T> {
    void listElements();
}
