package com.epicode.flowershop.strategies;

import com.epicode.flowershop.interfaces.DescriptionStrategy;
import com.epicode.flowershop.models.Bouquet;

public class CompactDescriptionStrategy implements DescriptionStrategy {

    @Override
    public String describe(Bouquet bouquet) {
        return bouquet.getName() + " - Total items included: " + bouquet.getElements().size();
    }
}
