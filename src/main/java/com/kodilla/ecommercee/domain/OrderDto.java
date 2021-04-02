package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderDto {
    private final Long id;
    private final String value;

    public static void updateFields(OrderDto orderDto, GenericEntity order) {
        if (orderDto.getValue() != null) {
            order.setValue(orderDto.getValue());
        }
    }

}
