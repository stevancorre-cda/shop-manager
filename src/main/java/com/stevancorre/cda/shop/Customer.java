package com.stevancorre.cda.shop;

import java.util.UUID;

/**
 * Represents a customer in the shop
 */
public class Customer {
    private final UUID id;

    private final String firstName;
    private final String lastName;

    /**
     * Initialize a new customer
     *
     * @param uuid The UUID
     * @param firstName The customer's first name
     * @param lastName The customer's last name
     */
    Customer(final UUID uuid, final String firstName, final String lastName) {
        this.id = uuid;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Initialize a new customer with a random UUID
     *
     * @param firstName The customer's first name
     * @param lastName The customer's last name
     */
    Customer(final String firstName, final String lastName) {
        this(UUID.randomUUID(), firstName, lastName);
    }

    /**
     * Get the UUID of the customer
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Return customer formatted like FirstName LASTNAME
     */
    public String getFullName() {
        return String.format("%s %S",
                getFirstName().substring(0, 1).toUpperCase() + getFirstName().substring(1),
                getLastName());
    }

    /**
     * Return customer formatted like FirstName LASTNAME
     */
    @Override
    public String toString() {
        return getFullName();
    }
}
