package com.epicode.flowershop.models;

import com.epicode.flowershop.interfaces.Composition;
import com.epicode.flowershop.interfaces.DescriptionStrategy;
import com.epicode.flowershop.strategies.SpecificDescriptionStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bouquet extends SellableItem implements Composition<BouquetElement> {
    private final List<BouquetElement> elements;
    private DescriptionStrategy descriptionStrategy = new SpecificDescriptionStrategy();

    @Override
    public void addElement(BouquetElement e) {
        this.elements.add(e);
        this.price += e.getPrice();
    }

    @Override
    public void listElements() {
        System.out.println(descriptionStrategy.describe(this));
    }

    public Bouquet(String name) {
        super(name, 0);
        this.elements = new ArrayList<>();
    }

    public void setDescriptionStrategy(DescriptionStrategy strategy) {
        this.descriptionStrategy = strategy;
    }

    public String getElementList() {
        return descriptionStrategy.describe(this);
    }

    public List<BouquetElement> getElements() {
        return elements;
    }
}
