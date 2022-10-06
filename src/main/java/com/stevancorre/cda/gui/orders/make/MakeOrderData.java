package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.Customer;
import com.stevancorre.cda.shop.OrderProduct;

public record MakeOrderData(Customer customer, OrderProduct[] products) {
}
