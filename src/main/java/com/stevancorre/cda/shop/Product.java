package com.stevancorre.cda.shop;

import java.util.UUID;

/**
 * Represents a product in stocks
 */
public final class Product {
    private final UUID id;

    private final String name;
    private final double price;

    private int quantity;

    Product(final String name, final double price, final int quantity) {
        this.id = UUID.randomUUID();

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return quantity;
    }

    /**
     * Remove `count` products from its stock
     */
    public void getFromStocks(final int count) {
        quantity -= count;
    }

    /**
     * Add `count` products to its stock
     */
    public void addToStocks(final int count) {
        quantity += count;
    }

    /**
     * Formatted string like `product name -- price`
     */
    @Override
    public String toString() {
        return String.format("%s --- %.2f€", name, price);
    }
}