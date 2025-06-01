package com.epicode.flowershop.models;

import com.epicode.flowershop.interfaces.CompositionElement;

import java.util.ArrayList;
import java.util.List;

public class BouquetElement extends SellableItem implements CompositionElement<BouquetElement> {
    private final List<BouquetElement> elements;

    public BouquetElement(String name, double price) {
        super(name, price);
        this.elements = new ArrayList<>();
    }

    @Override
    public void addElement(BouquetElement e) {
        this.elements.add(e);
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + name + '\'' +
                ", elements=" + elements +
                '}';
    }

    public List<BouquetElement> getElements() {
        return elements;
    }

}
