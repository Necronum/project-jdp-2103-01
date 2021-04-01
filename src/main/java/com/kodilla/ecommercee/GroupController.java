package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
    public List<GroupDto> getGroups(){
        return new ArrayList<>();
    }

    public GroupDto getGroup(Long groupId){
        return new GroupDto(1L, "Ubrania");
    }

    public void createGroup(GroupDto groupDto){
        //do nothing
    }

    public GroupDto updateGroup(GroupDto groupDto){
        return new GroupDto(1L, "Dodatki");
    }
}
