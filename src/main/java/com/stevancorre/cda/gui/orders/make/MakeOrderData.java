package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.Client;
import com.stevancorre.cda.shop.OrderProduct;

public record MakeOrderData(Client client, OrderProduct[] products) {
}