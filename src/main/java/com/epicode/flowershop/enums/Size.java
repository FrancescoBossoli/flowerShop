package com.epicode.flowershop.enums;

public enum Size {
    SM("Small"),
    MD("Medium"),
    LG("Large"),
    XL("Extra Large");

    private final String description;

    Size(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Size fromString(String input) {
        if (input == null) return null;
        for (Size size : Size.values()) {
            if (size.name().equalsIgnoreCase(input) || size.description.equalsIgnoreCase(input)) return size;
        }
        return null;
    }
}