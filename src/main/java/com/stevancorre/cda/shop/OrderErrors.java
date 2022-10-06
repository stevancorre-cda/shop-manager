package com.stevancorre.cda.shop;

import java.util.List;

public record OrderErrors(Order order, List<ShipError> errors) {
}
