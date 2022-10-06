package com.stevancorre.cda.shop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Represents the shop
 */
public class Shop {
    private final ArrayList<Order> orders;
    private final ArrayList<Product> products;
    private final ArrayList<Customer> customers;

    public Shop() {
        orders = new ArrayList<>();
        products = new ArrayList<>();
        customers = new ArrayList<>();
    }

    /**
     * Create shop with data from file
     *
     * @throws IOException Thrown when file is not found or it's impossible reading it
     */
    public Shop(final String productsData, final String ordersData) throws IOException {
        orders = new ArrayList<>();
        products = new ArrayList<>(parseFromFile(productsData, Shop::parseProduct));
        customers = new ArrayList<>();
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

    /**
     * Create a new product in the shop
     */
    public Product createProduct(final String name, final double price, final int quantity) throws NegativeQuantityException {
        if (quantity < 0)
            throw new NegativeQuantityException(name);

        final Product product = new Product(name, price, quantity);
        products.add(product);

        return product;
    }

    /**
     * Register a new custom in the stocks
     */
    public Customer registerCustomer(final String firstName, final String lastName) {
        final Customer customer = new Customer(firstName, lastName);
        customers.add(customer);

        return customer;
    }

    /**
     * Make a new order
     */
    public Order makeOrder(final Customer customer, final OrderProduct[] products) {
        final Order order = new Order(customer, products);
        orders.add(order);

        return order;
    }

    /**
     * Try to ship a specific order
     *
     * @return If there was no error, null. Otherwise a string containing them
     */
    public OrderErrors tryShip(final Order order) {
        if (order.getStatus() == OrderStatus.Finalized) return null;

        final ArrayList<ShipError> errors = new ArrayList<>();

        // loop through all entries and check if there are enough products in stocks
        // if no, add the error to the errors list
        for (final OrderProduct entry : order.getProducts()) {
            final Product product = entry.getProduct();
            if (entry.getQuantity() > product.getAvailableQuantity())
                errors.add(new ShipError(product, entry.getQuantity() - product.getAvailableQuantity()));
        }

        // if there was no error, remove the products from stocks then update the order status
        if (errors.isEmpty()) {
            for (final OrderProduct entry : order.getProducts()) {
                entry.getProduct().getFromStocks(entry.getQuantity());
            }

            order.updateStatus();
            return null;
        }

        return new OrderErrors(order, errors);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
