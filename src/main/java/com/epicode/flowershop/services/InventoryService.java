package com.epicode.flowershop.services;

import com.epicode.flowershop.core.Inventory;
import com.epicode.flowershop.enums.Size;
import com.epicode.flowershop.exceptions.UnrecognizedBouquetSizeException;
import com.epicode.flowershop.exceptions.UnrecognizedItemCreationRequestException;
import com.epicode.flowershop.models.SellableItem;
import com.epicode.flowershop.utilities.CustomLogger;
import com.epicode.flowershop.utilities.PlantNursery;
import com.epicode.flowershop.utilities.UniversalLogger;

import java.util.*;
import java.util.stream.Stream;

public class InventoryService {
    private static InventoryService instance;
    public static synchronized InventoryService getInstance() {
        if (instance == null) instance = new InventoryService();
        return instance;
    }

    private static final Inventory inventory = Inventory.getInstance();
    private static final CustomLogger logger = UniversalLogger.getInstance().getGlobalLogger();

    public Inventory getInventory() {
        return inventory;
    }

    public void inventoryRandomRefill() {
        List<String> plants = new ArrayList<>(Arrays.asList("aloe vera", "rose", "lily", "cactus", "orchid", "lavender", "hydrangea", "peony", "sansevieria"));
        List<String> trees = new ArrayList<>(Arrays.asList("olive", "lemon", "bonsai", "orange", "cherry"));
        List<String> bouquets = new ArrayList<>(Arrays.asList("roses", "mixed flowers"));

        Stream.of(plants, trees)
            .flatMap(Collection::stream)
            .forEach(plant -> {
                int random = randomize();
                try {
                    inventory.addItem(PlantNursery.spawn(plants.contains(plant) ? "plant" : "tree", plant), random);
                } catch (UnrecognizedItemCreationRequestException | IllegalArgumentException e) {
                    logger.error(e.getMessage());
                }
        });

        bouquets.forEach(type -> {
            for (Size size : Size.values()) {
                int random = randomize() + 3;
                try {
                    inventory.addItem(PlantNursery.spawn("bouquet", type, size.getDescription()), random);
                } catch (UnrecognizedItemCreationRequestException | UnrecognizedBouquetSizeException | IllegalArgumentException e) {
                    logger.error(e.getMessage());
                }
            }
        });
    }

    public int randomize() {
        Random r = new Random();
        return r.nextInt(10) + 1;
    }

    public void removeItem(SellableItem item) {
        inventory.removeItem(item);
    }

    public void addItem(SellableItem item) {
        inventory.addItem(item, 1);
    }

    public SellableItem pickItem(int index) {
        Map<SellableItem, Integer> stockMap = inventory.getStockMap();
        List<SellableItem> orderedList = stockMap.keySet().stream()
            .sorted(Comparator.comparing(SellableItem::getName))
            .toList();
        return orderedList.get(--index);
    }

    public void retrieveSellingPrices() {
        inventory.printSellingPrices();
    }

    public void retrieveStockItemList() {
        inventory.printStockItemList();
    }

}
