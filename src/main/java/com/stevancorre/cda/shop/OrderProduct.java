package com.stevancorre.cda.shop;

/**
 * Represents a product in an order, associating a product with a quantity
 */
public final class OrderProduct {
    private final Product product;
    private int quantity;

    /**
     * Initializes a new order product (= ordery entry)
     *
     * @param product The product
     * @param quantity The quantity
     */
    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Get the associated product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get the quantity of products
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of products
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
