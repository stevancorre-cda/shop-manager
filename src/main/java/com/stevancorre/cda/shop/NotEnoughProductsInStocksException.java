package com.stevancorre.cda.shop;

public class NotEnoughProductsInStocksException extends Exception {
    public NotEnoughProductsInStocksException(final Product product) {
        super(String.format("Not enough '%s' in stocks (%d left)", product.getName(), product.getAvailableQuantity()));
    }
}
