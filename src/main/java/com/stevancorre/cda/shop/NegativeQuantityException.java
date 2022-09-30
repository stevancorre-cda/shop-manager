package com.stevancorre.cda.shop;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException(final String productName) {
        super(String.format("Trying to assign to '%s' a negative quantity", productName));
    }

    public NegativeQuantityException(final Product product) {
        this(product.getName());
    }
}
