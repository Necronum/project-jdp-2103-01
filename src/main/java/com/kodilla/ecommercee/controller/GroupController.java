package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @GetMapping(value="getGroups")
    public List<GroupDto> getGroups(){
        return new ArrayList<>();
    }

    @GetMapping(value="getGroup")
    public GroupDto getGroup(@RequestParam Long groupId){
        return new GroupDto(1L, "Ubrania");
    }

    @PostMapping(value="createGroup")
    public void createGroup(@RequestBody GroupDto groupDto){
        //do nothing
    }

    @PutMapping(value="updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto){
        return new GroupDto(1L, "Updated group");
    }
}
