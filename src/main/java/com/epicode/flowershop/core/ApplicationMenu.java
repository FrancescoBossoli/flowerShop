package com.epicode.flowershop.core;

import com.epicode.flowershop.handlers.BigOrderDiscountHandler;
import com.epicode.flowershop.handlers.BulkDiscountHandler;
import com.epicode.flowershop.handlers.DiscountHandler;
import com.epicode.flowershop.handlers.RegularPaymentHandler;
import com.epicode.flowershop.services.CartService;
import com.epicode.flowershop.services.InventoryService;
import com.epicode.flowershop.templates.Shop;
import com.epicode.flowershop.utilities.CustomLogger;
import com.epicode.flowershop.utilities.UniversalLogger;

import java.util.Scanner;

public class ApplicationMenu {
    private static ApplicationMenu instance;
    private static final CustomLogger logger = UniversalLogger.getInstance().getGlobalLogger();
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventoryService = InventoryService.getInstance();
    private static Shop shop;
    private static CartService cartService;

    private ApplicationMenu() {
        DiscountHandler handler1 = new RegularPaymentHandler();
        DiscountHandler handler2 = new BulkDiscountHandler();
        DiscountHandler handler3 = new BigOrderDiscountHandler();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        shop = new FlowerShop(inventoryService.getInventory(), handler1);
        cartService = CartService.getInstance(shop, new Cart(), inventoryService, logger);
    }

    public static synchronized ApplicationMenu getInstance() {
        if (instance == null) instance = new ApplicationMenu();
        return instance;
    }

    public void startEmulationSystem() {
        inventoryService.inventoryRandomRefill();
        boolean isRunning = true;
        while (isRunning) {
            printMainMenu();
            try {
                int mainMenuChoice = Integer.parseInt(scanner.nextLine());
                switch (mainMenuChoice) {
                    case 1:
                        printStockMenu();
                        break;
                    case 2: {
                        boolean onAddToCartMenu = true;
                        while (onAddToCartMenu) {
                            printBuyMenu();
                            try {
                                int addToCartMenuChoice = Integer.parseInt(scanner.nextLine());
                                if (addToCartMenuChoice == 0) onAddToCartMenu = false;
                                else cartService.addItem(inventoryService.pickItem(addToCartMenuChoice));
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                System.out.println("Your choice must be expressed using valid integer inputs \n");
                            }
                        }
                        break;
                    }
                    case 3: {
                        printCartList();
                        System.out.println("\nInsert any key to return to the main menu \n");
                        scanner.nextLine();
                        break;
                    }
                    case 4: {
                        checkOut();
                        break;
                    }


                    case 0:
                        scanner.close();
                        isRunning = false;
                        break;
                    default:
                        System.out.println("The pressed key wasn't recognized, retry with a key from the list, please");
                        break;
                }
            } catch (NumberFormatException e) {
                logger.info("Your choice must be expressed using valid integer inputs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void printMainMenu() {
        System.out.print("""
            -------------------------------------------------
            Flower Shop Emulation System
            -------------------------------------------------
            1) Print the Shop Inventory Goods
            2) Add Items to Cart
            3) View the Items currently in the Cart
            4) Proceed to Checkout
            
            0) Close the Application
            
            """ );
    }

    public void printBuyMenu() {
        inventoryService.retrieveSellingPrices();
        System.out.print("""
            \n0  - Return to main menu
            -------------------------------------------------
            Select the Item you'd like to buy \n
            """);
    }

    public void printStockMenu() {
        System.out.print("""
            -------------------------------------------------
            Inventory Available Goods
            -------------------------------------------------
            """);
        inventoryService.retrieveStockItemList();
    }

    public void printCartList() {
        System.out.print("""
            -------------------------------------------------
            Cart's Content
            -------------------------------------------------
            """);
        cartService.printCartContent();
    }

    public void checkOut() {
        cartService.checkout();
    }
}
