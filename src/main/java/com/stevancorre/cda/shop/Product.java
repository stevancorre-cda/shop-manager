package com.stevancorre.cda.shop;

import java.util.UUID;

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

    public void getFromStocks(final int count) {
        quantity -= count;
    }

    public void addToStocks(final int count) {
        quantity += count;
    }

    @Override
    public String toString() {
        return String.format("%s --- %.2f€", name, price);
    }
}