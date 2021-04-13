package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto){
        return new Group(
                groupDto.getId(),
                groupDto.getName(),
                groupDto.getProducts());
    }

    public GroupDto mapToGroupDto(final Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getProducts());
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList){
        return groupList.stream()
                .map(g -> new GroupDto(
                        g.getId(),
                        g.getName(),
                        g.getProducts()))
                .collect(Collectors.toList());
    }
    public List<Group> mapToGroupList(final List<GroupDto> groupDtoList){
        return groupDtoList.stream()
                .map(g -> new Group(
                        g.getId(),
                        g.getName(),
                        g.getProducts()))
                .collect(Collectors.toList());
    }
}

