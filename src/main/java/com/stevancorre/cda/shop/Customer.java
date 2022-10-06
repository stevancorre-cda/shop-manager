package com.stevancorre.cda.shop;

import java.util.UUID;

/**
 * Represents a customer in the shop
 */
public class Customer {
    private final UUID id;

    private final String firstName;
    private final String lastName;

    Customer(final UUID uuid, final String firstName, final String lastName) {
        this.id = uuid;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    Customer(final String firstName, final String lastName) {
        this(UUID.randomUUID(), firstName, lastName);
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * FirstName LASTNAME
     */
    public String getFullName() {
        return String.format("%s %S",
                getFirstName().substring(0, 1).toUpperCase() + getFirstName().substring(1),
                getLastName());
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
