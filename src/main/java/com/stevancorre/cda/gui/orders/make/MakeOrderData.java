package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.Customer;
import com.stevancorre.cda.shop.OrderProduct;

/**
 * Represent raw order data. Used by the MakeOrderOptionPanel
 */
public record MakeOrderData(Customer customer, OrderProduct[] products) {
}
