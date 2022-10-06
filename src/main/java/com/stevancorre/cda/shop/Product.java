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

    /**
     * Initializes a new product
     *
     * @param uuid The UUID
     * @param name The product name
     * @param price The price per unit
     * @param quantity The quantity in stocks
     */
    Product(final UUID uuid, final String name, final double price, final int quantity) {
        this.id = uuid;

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Initialize a new product with a random UUID
     *
     * @param name The product name
     * @param price The price per unit
     * @param quantity The quantity in stocks
     */
    Product(final String name, final double price, final int quantity) {
        this(UUID.randomUUID(), name, price, quantity);
    }

    /**
     * Get the product id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the product price per unit
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the available quantity for this product
     */
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