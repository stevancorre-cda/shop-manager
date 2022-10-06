package com.stevancorre.cda.shop;

import java.util.UUID;

/**
 * Represents a client in the shop
 */
public class Client {
    private final UUID id;

    private final String firstName;
    private final String lastName;

    Client(final String firstName, final String lastName) {
        this.id = UUID.randomUUID();

        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFullName() {
        return String.format("%s %S", getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
