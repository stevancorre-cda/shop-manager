package com.stevancorre.cda.shop;

/**
 * Represents a product in an order, associating a product with a quantity
 */
public final class OrderProduct {
    private final Product product;
    private int quantity;

    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct[" +
                "product=" + product + ", " +
                "quantity=" + quantity + ']';
    }
}
