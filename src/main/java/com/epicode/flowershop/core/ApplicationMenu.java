package com.epicode.flowershop.core;

import com.epicode.flowershop.handlers.BigOrderDiscountHandler;
import com.epicode.flowershop.handlers.BulkDiscountHandler;
import com.epicode.flowershop.handlers.DiscountHandler;
import com.epicode.flowershop.handlers.RegularPaymentHandler;
import com.epicode.flowershop.services.CartService;
import com.epicode.flowershop.services.InventoryService;
import com.epicode.flowershop.templates.Shop;
import com.epicode.flowershop.utilities.CustomLogger;
import com.epicode.flowershop.utilities.ReceiptWriter;
import com.epicode.flowershop.utilities.UniversalLogger;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        shop.addObserver(new ReceiptWriter());
        cartService = CartService.getInstance(shop, new Cart(), inventoryService, logger);
    }

    public static synchronized ApplicationMenu getInstance() {
        if (instance == null) instance = new ApplicationMenu();
        return instance;
    }

    public Shop getShop() {
        return shop;
    }

    public CartService getCartService() {
        return cartService;
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
                    case 2:
                        printBouquetCompositionMenu();
                        break;
                    case 3: {
                        boolean onAddToCartMenu = true;
                        while (onAddToCartMenu) {
                            printBuyMenu();
                            try {
                                int choice = Integer.parseInt(scanner.nextLine());
                                if (choice == 0) onAddToCartMenu = false;
                                else cartService.addItem(inventoryService.pickItem(choice));
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                System.out.println("Your choice must be expressed using valid integer inputs \n");
                            }
                        }
                        break;
                    }
                    case 4: {
                        if (cartService.isEmpty()) {
                            System.out.println("The Cart is Empty");
                            getDelay();
                        } else {
                            boolean onRemoveFromCartMenu = true;
                            while (onRemoveFromCartMenu) {
                                printCartItemRemovalMenu();
                                try {
                                    int choice = Integer.parseInt(scanner.nextLine());
                                    if (choice == 0) onRemoveFromCartMenu = false;
                                    else cartService.removeItem(inventoryService.pickItem(choice));
                                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                    System.out.println("Your choice must be expressed using valid integer inputs \n");
                                }
                            }
                        }
                        break;
                    }
                    case 5: {
                        printCartList();
                        getDelay();
                        break;
                    }
                    case 6: {
                        cartService.checkout();
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
        printMenuHeading("Flower Shop Emulation System");
        System.out.print("""            
            1) Print the Shop Inventory Goods
            2) Examine bouquet compositions
            3) Add Items to Cart
            4) Remove Items from Cart
            5) View the Items currently in the Cart
            6) Proceed to Checkout
            
            0) Close the Application
            
            """ );
    }

    public void printBouquetCompositionMenu() {
        printMenuHeading("Our Bouquet Compositions");
        inventoryService.getAvailableBouquetList();
        getDelay();
    }

    public void printBuyMenu() {
        inventoryService.retrieveSellingPrices();
        System.out.print("""
            \nSelect the Item you'd like to buy
            -------------------------------------------------
            Press 0 to return to the main menu \n
            """);
    }

    public void printStockMenu() {
        printMenuHeading("Inventory Available Goods");
        inventoryService.retrieveStockItemList();
        getDelay();
    }

    public void printCartItemRemovalMenu() {
        printMenuHeading("Cart's Content");
        cartService.printCartRemovalList();
        System.out.print("""
            \nWhich item do you wish to remove from the cart?
            -------------------------------------------------
            Press 0 to return to the main menu \n
            """);
    }

    public void printCartList() {
        printMenuHeading("Cart's Content");
        cartService.printCartContent();
    }

    public void printMenuHeading(String content) {
        System.out.println("-------------------------------------------------");
        System.out.println(content);
        System.out.println("-------------------------------------------------");
    }

    public void getDelay() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("\n");
        } catch (InterruptedException e) {
            logger.error("The thread was interrupted during the 2 seconds timeout");
        }
    }
}
