package com.stevancorre.cda.shop;

import java.util.List;

/**
 * Represent errors in an order (missing stocks)
 */
public record OrderErrors(Order order, List<ShipError> errors) {
}
