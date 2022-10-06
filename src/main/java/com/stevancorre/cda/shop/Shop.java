package com.stevancorre.cda.shop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Shop {
    private final ArrayList<Order> orders;
    private final ArrayList<Product> products;
    private final ArrayList<Client> clients;

    public Shop() {
        orders = new ArrayList<>();
        products = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public Shop(final String productsData, final String ordersData) throws IOException {
        // TODO: load ordersData

        orders = new ArrayList<>();
        products = new ArrayList<>(parseFromFile(productsData, Shop::parseProduct));
        clients = new ArrayList<>();
    }

    private static Product parseProduct(final String source) {
        final String[] parts = source.split(";");
        return new Product(
                parts[0],
                Double.parseDouble(parts[1]),
                Integer.parseInt(parts[2]));
    }

    private static <E> List<E> parseFromFile(final String source, final Function<String, E> parser) throws IOException {
        final Path filePath = Paths.get(source);
        final List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        return lines
                .stream()
                .map(parser)
                .toList();
    }

    public Product createProduct(final String name, final double price, final int quantity) throws NegativeQuantityException {
        if(quantity < 0)
            throw new NegativeQuantityException(name);

        final Product product = new Product(name, price, quantity);
        products.add(product);

        return product;
    }

    public Client registerClient(final String firstName, final String lastName) {
        final Client client = new Client(firstName, lastName);
        clients.add(client);

        return client;
    }

    public Order makeOrder(final Client client, final OrderProduct[] products) {
        final Order order = new Order(client, products);
        orders.add(order);

        for (final OrderProduct orderProduct : products) {
            orderProduct.getProduct().getFromStocks(orderProduct.getQuantity());
        }

        return order;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}
