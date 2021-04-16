package com.kodilla.ecommercee;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum OrderStatus {
    NEW, PAID, DELIVERED;

    public static Optional<OrderStatus> parseString(String value) {
        return Arrays.stream(values())
                .filter(status -> Objects.equals(status.name(), value))
                .findFirst();
    }
}
