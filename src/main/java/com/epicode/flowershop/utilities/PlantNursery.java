package com.epicode.flowershop.utilities;

import com.epicode.flowershop.enums.Size;
import com.epicode.flowershop.exceptions.UnrecognizedBouquetSizeException;
import com.epicode.flowershop.exceptions.UnrecognizedItemCreationRequestException;
import com.epicode.flowershop.models.*;

import java.util.Map;

public class PlantNursery {
    private PlantNursery(){}
    public static SellableItem spawn(String type, String name) throws UnrecognizedItemCreationRequestException {
        if (type == null || name == null || type.trim().isEmpty() || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Type and name properties must not be null or empty");
        }

        String cleanType = type.trim().toLowerCase();
        String cleanName = name.trim().toLowerCase();

        Map<String, Plant> plants = Map.of(
        "aloe vera", new Plant("Aloe Vera", 4),
        "rose", new Plant("Rose", 8),
        "lily", new Plant("Lily", 12),
        "cactus", new Plant("Cactus", 4),
        "orchid", new Plant("Orchid", 15),
        "lavender", new Plant("Lavender", 5),
        "hydrangea", new Plant("Hydrangea", 8),
        "peony", new Plant("Peony", 4.5),
        "sansevieria", new Plant("Sansevieria", 10)
        );

        Map<String, Tree> trees = Map.of(
        "olive", new Tree("Olive", 17),
        "lemon", new Tree("Lemon", 20),
        "bonsai", new Tree("Bonsai", 25),
        "orange", new Tree("Orange", 22),
        "cherry", new Tree("Cherry", 20)
        );

        return switch (cleanType) {
            case "plant" -> {
                if (plants.containsKey(cleanName)) yield plants.get(cleanName);
                else throw new UnrecognizedItemCreationRequestException("The selected plant is not available");
            }
            case "tree" -> {
                if (trees.containsKey(cleanName)) yield trees.get(cleanName);
                else throw new UnrecognizedItemCreationRequestException("The selected tree is not available");
            }
            default -> throw new UnrecognizedItemCreationRequestException("The type inserted was not recognized");
        };
    }

    public static SellableItem spawn(String type, String name, String size) throws UnrecognizedItemCreationRequestException, UnrecognizedBouquetSizeException {
        if (size == null || size.trim().isEmpty()) return spawn(type, name);
        else {
            if (type == null || name == null || type.trim().isEmpty() || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Type and name properties must not be null or empty");
            }

            String cleanType = type.trim().toLowerCase();
            String cleanName = name.trim().toLowerCase();
            String cleanSize = size.trim().toLowerCase();

            if (cleanType.equals("bouquet")) {
                Size sz = Size.fromString(cleanSize);
                if (sz != null) {
                    BouquetElement rose = new BouquetElement("Rose", 4);
                    BouquetElement sunflower = new BouquetElement("Sunflower", 0.4);
                    BouquetElement daisy = new BouquetElement("Daisy", 0.6);
                    BouquetElement snapDragon = new BouquetElement("Snapdragon", 1.2);
                    BouquetElement statice = new BouquetElement("Statice", 0.5);
                    BouquetElement chrysanthemum = new BouquetElement("Chrysanthemum", 1);
                    BouquetElement leaf = new BouquetElement("Leaf", 0);
                    BouquetElement mixOfLeaves = new BouquetElement("Mix of Leaves", 0.5);
                    BouquetElement velvetBow = new BouquetElement("Velvet Bow", 2);
                    BouquetElement velvetRibbon = new BouquetElement("Velvet Ribbon", 2);
                    BouquetElement simpleRibbon = new BouquetElement("Ribbon", 1);
                    return switch (cleanName) {
                        case ("roses") -> {
                            Bouquet bouquet = new Bouquet("Bouquet of Roses - " + sz.getDescription() + " Size");
                            switch (sz) {
                                case XL -> {
                                    for (int i = 0; i < 2; i++) rose.addElement(leaf);
                                    for (int i = 0; i < 15; i++) bouquet.addElement(rose);
                                    bouquet.addElement(velvetBow);
                                    bouquet.addElement(velvetRibbon);
                                    yield bouquet;
                                }
                                case LG -> {
                                    for (int i = 0; i < 2; i++) rose.addElement(leaf);
                                    for (int i = 0; i < 10; i++) bouquet.addElement(rose);
                                    bouquet.addElement(mixOfLeaves);
                                    bouquet.addElement(velvetRibbon);
                                    yield bouquet;
                                }
                                case MD -> {
                                    for (int i = 0; i < 2; i++) rose.addElement(leaf);
                                    for (int i = 0; i < 5; i++) bouquet.addElement(rose);
                                    bouquet.addElement(mixOfLeaves);
                                    bouquet.addElement(simpleRibbon);
                                    yield bouquet;
                                }
                                case SM -> {
                                    for (int i = 0; i < 3; i++) rose.addElement(leaf);
                                    for (int i = 0; i < 3; i++) bouquet.addElement(rose);
                                    bouquet.addElement(mixOfLeaves);
                                    bouquet.addElement(simpleRibbon);
                                    yield bouquet;
                                }
                                default ->
                                        throw new UnrecognizedItemCreationRequestException("Unknown size for roses");
                            }
                        }
                        case ("mixed flowers") -> {
                            Bouquet bouquet = new Bouquet("Bouquet of Mixed Flowers - " + sz.getDescription() + " Size");
                            switch (sz) {
                                case XL -> {
                                    for (int i = 0; i < 10; i++) bouquet.addElement(daisy);
                                    for (int i = 0; i < 3; i++) bouquet.addElement(sunflower);
                                    for (int i = 0; i < 6; i++) bouquet.addElement(snapDragon);
                                    for (int i = 0; i < 4; i++) bouquet.addElement(statice);
                                    for (int i = 0; i < 5; i++) bouquet.addElement(chrysanthemum);
                                    bouquet.addElement(velvetBow);
                                    bouquet.addElement(velvetRibbon);
                                    yield bouquet;
                                }
                                case LG -> {
                                    for (int i = 0; i < 8; i++) bouquet.addElement(daisy);
                                    for (int i = 0; i < 2; i++) bouquet.addElement(sunflower);
                                    for (int i = 0; i < 4; i++) bouquet.addElement(snapDragon);
                                    for (int i = 0; i < 4; i++) bouquet.addElement(statice);
                                    for (int i = 0; i < 3; i++) bouquet.addElement(chrysanthemum);
                                    bouquet.addElement(velvetRibbon);
                                    yield bouquet;
                                }
                                case MD -> {
                                    for (int i = 0; i < 6; i++) bouquet.addElement(daisy);
                                    for (int i = 0; i < 2; i++) bouquet.addElement(sunflower);
                                    for (int i = 0; i < 3; i++) bouquet.addElement(snapDragon);
                                    for (int i = 0; i < 3; i++) bouquet.addElement(statice);
                                    for (int i = 0; i < 2; i++) bouquet.addElement(chrysanthemum);
                                    bouquet.addElement(simpleRibbon);
                                    yield bouquet;
                                }
                                case SM -> {
                                    for (int i = 0; i < 5; i++) bouquet.addElement(daisy);
                                    for (int i = 0; i < 2; i++) sunflower.addElement(leaf);
                                    bouquet.addElement(sunflower);
                                    bouquet.addElement(snapDragon);
                                    for (int i = 0; i < 2; i++) statice.addElement(leaf);
                                    for (int i = 0; i < 2; i++) bouquet.addElement(statice);
                                    bouquet.addElement(chrysanthemum);
                                    bouquet.addElement(mixOfLeaves);
                                    bouquet.addElement(simpleRibbon);
                                    yield bouquet;
                                }
                                default ->
                                        throw new UnrecognizedBouquetSizeException("Unknown size for mixed flowers");
                            }
                        }
                        default ->
                                throw new UnrecognizedItemCreationRequestException("The type inserted was not recognized");
                    };
                }
            }
            throw new UnrecognizedItemCreationRequestException("The type inserted was not recognized");
        }
    }
}
