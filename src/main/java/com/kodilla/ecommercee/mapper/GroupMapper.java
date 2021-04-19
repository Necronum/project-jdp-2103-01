package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GroupMapper {

    private final ProductRepository productRepository;

    public Group mapToGroup(final GroupDto groupDto){
        return new Group(
                groupDto.getId(),
                groupDto.getName(),
                groupDto.getProductsId().stream()
                        .map(productRepository::findById)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }

    public GroupDto mapToGroupDto(final Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList){
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
    public List<Group> mapToGroupList(final List<GroupDto> groupDtoList){
        return groupDtoList.stream()
                .map(this::mapToGroup)
                .collect(Collectors.toList());
    }
}

