package com.stevancorre.cda.shop;

import java.util.UUID;

public class Client {
    private final UUID id;

    private final String name;

    Client(final String name) {
        this.id = UUID.randomUUID();

        this.name = name;
    }
}
