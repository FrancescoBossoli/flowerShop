package com.epicode.flowershop.core;

import com.epicode.flowershop.interfaces.StockField;
import com.epicode.flowershop.models.Bouquet;
import com.epicode.flowershop.models.SellableItem;
import com.epicode.flowershop.utilities.CustomLogger;
import com.epicode.flowershop.utilities.UniversalLogger;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private static Inventory instance;
    private static final CustomLogger logger = UniversalLogger.getInstance().getGlobalLogger();
    private Inventory() {}

    @StockField
    private final Map<SellableItem, Integer> stockMap = new HashMap<>();

    public static synchronized Inventory getInstance() {
        if (instance == null) instance = new Inventory();
        return instance;
    }

    public Map<SellableItem, Integer> getStockMap() {
        return stockMap;
    }

    public void addItem(SellableItem item, int quantity) {
        stockMap.put(item, stockMap.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(SellableItem item) {
        if (!stockMap.containsKey(item)) return;
        int currentStock = stockMap.get(item);
        if (currentStock == 1) stockMap.remove(item);
        else stockMap.put(item, currentStock - 1);
        logStockStatus();
    }

    public int getStock(SellableItem item) {
        return stockMap.getOrDefault(item, 0);
    }

    private void logStockStatus() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(StockField.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(this);
                    if (value instanceof Map<?, ?> map) {
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            SellableItem item = (SellableItem) entry.getKey();
                            int quantity = (Integer) entry.getValue();
                            if (quantity == 1) System.out.printf("Warning: Only 1 unit left of '%s'%n", item.getName());
                            else if (quantity == 0) System.out.printf("Warning: '%s' is out of stock%n", item.getName());
                        }
                    }
                } catch (IllegalAccessException e) {
                    logger.error("An error has occurred while using the reflection functionality");
                }
            }
        }
    }

    public void printStockItemList() {
        stockMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey(Comparator.comparing(SellableItem::getName)))
            .forEach(entry -> System.out.println(entry.getKey().getName() + " - Qty: " + entry.getValue()));
    }

    public void printSellingPrices() {
        System.out.print("""
            -------------------------------------------------
            Inventory Available Goods
            -------------------------------------------------
            """);
        AtomicInteger i = new AtomicInteger(1);
        stockMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey(Comparator.comparing(SellableItem::getName)))
            .forEach(entry -> System.out.println(i.getAndIncrement() + " - " + entry.getKey().getName() + " - " + entry.getKey().getPrice() + "€"));
    }

    public List<Bouquet> getAvailableBouquetList() {
        return stockMap.keySet().stream()
            .filter(item -> item.getName()
            .toLowerCase()
            .contains("bouquet"))
            .map(item -> (Bouquet) item)
            .toList();
    }
}

