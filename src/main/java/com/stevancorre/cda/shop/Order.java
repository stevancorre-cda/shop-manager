package com.stevancorre.cda.shop;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Date date;

    private OrderStatus status;

    private final Client client;
    private final OrderProduct[] products;

    Order(final Client client, final OrderProduct[] products) {
        this.id = UUID.randomUUID();
        this.date = new Date();
        this.status = OrderStatus.Preparing;

        this.client = client;
        this.products = products;
    }

    public void updateStatus() {
        status = switch (status) {
            case Preparing -> OrderStatus.Shipped;
            case Shipped -> OrderStatus.Finalized;

            default -> throw new IllegalArgumentException("Order already finalized");
        };
    }

    public OrderProduct[] getProducts() {
        return products;
    }

    public int getProductsCount() {
        return Arrays.stream(this.getProducts())
                .toList().stream().mapToInt(OrderProduct::quantity)
                .sum();
    }

    public double getTotalPrice() {
        return Arrays
                .stream(products)
                .mapToDouble(x -> x.product().getPrice() * x.quantity())
                .sum();
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }
}
