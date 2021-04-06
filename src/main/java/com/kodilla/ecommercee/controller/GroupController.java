package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups(){
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto getGroup(@PathVariable Long groupId){
        return new GroupDto(1L, "Ubrania");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGroup(@RequestBody GroupDto groupDto){
        //do nothing
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto){
        return new GroupDto(1L, "Updated group");
    }
}
