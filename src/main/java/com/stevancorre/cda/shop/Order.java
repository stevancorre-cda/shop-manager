package com.stevancorre.cda.shop;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Date date;

    private final Client client;
    private final OrderProduct[] products;

    Order(final Client client, final OrderProduct[] products) {
        this.id = UUID.randomUUID();
        this.date = new Date();

        this.client = client;
        this.products = products;
    }

    public OrderProduct[] getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return Arrays
                .stream(products)
                .mapToDouble(x -> x.product().getPrice() * x.quantity())
                .sum();
    }
}
