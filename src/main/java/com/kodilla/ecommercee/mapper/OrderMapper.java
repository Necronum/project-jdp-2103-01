package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public GenericEntity mapToOrder(OrderDto orderDto) {
        return new GenericEntity(orderDto.getValue());
    }

    public OrderDto mapToOrderDto(GenericEntity order) {
        return OrderDto.builder()
                .id(order.getId())
                .value(order.getValue())
                .build();
    }

    public List<OrderDto> mapToOrderDtoList(List<GenericEntity> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
