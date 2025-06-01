# FlowerShop

**FlowerShop** is a Java SE application built to emulate the management functionalities of a flower shop dedicated software. It was built with the idea of showcasing various OOP and Java related design patterns and functionalities.

---

## 🎯 Project Objective

The goal of this project is to develop a well-structured, secure, and maintainable application that demonstrates:

The effective use of object-oriented programming (OOP) principles.
The proper implementation of design patterns.
Proficiency in leveraging core Java technologies.
Special emphasis is placed on code security and robust exception handling.

---

## 🥇 Achieved Requirements

### **Mandatory Design Patterns (16 points)**

1) **Factory**

**Implementation**: The `PlantNursery` class handles the creation of various types of inventory items that the store would later sell.
**Usage**: The overloaded method `spawn()` is used to decide which specific `SellableItem` (`Plant`, `Tree`, or `Bouquet`) to instantiate and return.

2) **Composite** 
**Implementation**: `Bouquet` instances are built using the Composite pattern. 
**Usage**: The `Bouquet` instance acts as the "Composite" node, capable of holding various `BouquetElement` instances ("Leaf" components, representing individual items within a bouquet) together. In the current implementation the price of each element will determine the price of the composed item.  

3) **Iterator** 
**Implementation**: The `Cart` class has a nested inner class `CartIterator` that maintains its own index to traverse the items list within the Cart.
**Usage**: The `CartService` class uses  the `getIterator()` method to display the cart's items without needing to know how `Cart` stores its items. Similarly, The `Shop` class also uses `getIterator()` in its `checkout()` method to iterate through items in the cart.

4) **Exception Shielding**
**Implementation**: `UnrecognizedBouquetSizeException` and `UnrecognizedItemCreationRequestException` ensure robust exception handling during the `SellableItem` construction.
**Usage**: All exceptions are caught and shielded to prevent crashes or visible stack traces.

---

### **Mandatory Design Patterns (16 points)**

1) **Collections Framework**
**Implementation**: `List` and `Map` are widely used throughout the application.
**Example**: `Cart` items are in an `ArrayList`, `Inventory`'s stock is in a `HashMap`.

2) **Generics**
**Implementation**: Generics are employed to write flexible code, particularly for collections.
**Example**: Almost every instance of `HashMap` or `ArrayList` are created through the use of generics.

3) **Java I/O**
**Implementation**: The user is forced to use I/O to interact with the application.
**Example**: Depending on the input inserted the application returns a visible output. In case of errors a log file is written.

4) **Logging**
**Implementation**: Advanced logging is provided by the `UniversalLogger` and `CustomLogger` classes.
**Example**: The system logs significant events such as game restarts and timer endings.

5) **JUnit Testing**
**Implementation**: Some unit tests are present, in particular to analyze the correct functioning of the `PlantNursery` creation methods.
**Example**: Tests check if inputs are correctly sanitized and the correct creation of instances based on such inputs. They also test if the correct exceptions are launched.