package com.epicode.flowershop.strategies;

import com.epicode.flowershop.interfaces.DescriptionStrategy;
import com.epicode.flowershop.models.Bouquet;
import com.epicode.flowershop.models.BouquetElement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SpecificDescriptionStrategy implements DescriptionStrategy {

    @Override
    public String describe(Bouquet bouquet) {
        Map<String, Integer> summaryMap = new LinkedHashMap<>();
        for (BouquetElement element : bouquet.getElements()) {
            String elementDescription = describeElement(element);
            summaryMap.put(elementDescription, summaryMap.getOrDefault(elementDescription, 0) + 1);
        }

        String summary = summaryMap.entrySet().stream()
            .map(entry -> entry.getValue() + "x " + entry.getKey())
            .collect(Collectors.joining(", "));

        return bouquet.getName() + " | Composed of " + summary;
    }

    private String describeElement(BouquetElement element) {
        if (element.getElements().isEmpty()) return element.getName();
        Map<String, Integer> subSummary = new LinkedHashMap<>();
        for (BouquetElement sub : element.getElements()) {
            String subDesc = describeElement(sub);
            subSummary.put(subDesc, subSummary.getOrDefault(subDesc, 0) + 1);
        }

        String subPart = subSummary.entrySet().stream()
            .map(e -> e.getValue() + "x " + e.getKey())
            .collect(Collectors.joining(", "));

        return element.getName() + " with " + subPart;
    }
}
