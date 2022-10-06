package com.stevancorre.cda.shop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Represents an order in the shop
 */
public class Order {
    private final UUID id;
    private final Date date;

    private OrderStatus status;

    private final Customer customer;
    private final OrderProduct[] products;

    /**
     * Initialize a new order
     *
     * @param id The UUID
     * @param date The date
     * @param customer The customer
     * @param products Array of products associated with quantities
     */
    Order(final UUID id, final Date date, final Customer customer, final OrderProduct[] products) {
        this.id = id;
        this.date = date;
        this.status = OrderStatus.Preparing;

        this.customer = customer;
        this.products = products;
    }

    /**
     * Initialize a new order with default UUID and date
     *
     * @param customer The customer
     * @param products Array of products associated with quantities
     */
    Order(final Customer customer, final OrderProduct[] products) {
        this(UUID.randomUUID(), new Date(), customer, products);
    }

    /**
     * Update to next status (preparing -> finalized..)
     */
    public void updateStatus() {
        status = switch (status) {
            case Preparing -> OrderStatus.Finalized;
            case Finalized -> throw new IllegalArgumentException("Order already finalized");
        };
    }

    /**
     * Get list of products in the order
     */
    public OrderProduct[] getProducts() {
        return products;
    }

    /**
     * Get total products count (with quantities)
     */
    public int getProductsCount() {
        return Arrays.stream(this.getProducts())
                .toList().stream().mapToInt(OrderProduct::getQuantity)
                .sum();
    }

    /**
     * Calculate the total price of the order
     */
    public double getTotalPrice() {
        return Arrays
                .stream(products)
                .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                .sum();
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Returns a date formatted like dd/MM/yyyy hh:mm
     */
    public String getFormattedDate() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return format.format(getDate());
    }

    /**
     * Get the current order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Get the customer who made the orderx
     */
    public Customer getCustomer() {
        return customer;
    }
}
