package com.stevancorre.cda.shop;

/**
 * Represent a ship error for a single product
 */
public record ShipError(Product product, int quantity) {
}
