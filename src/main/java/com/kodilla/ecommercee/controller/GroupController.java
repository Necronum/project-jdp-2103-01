package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @Autowired
    private GroupDbService groupService;
    @Autowired
    private GroupMapper groupMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups(){
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }
  
    @GetMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupService.getGroup(groupId).orElseThrow(()
                -> new GroupNotFoundException("Group not found: " + groupId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGroup(@RequestBody GroupDto groupDto){
        groupService.saveGroup(groupMapper.mapToGroup(groupDto));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto){
        return groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        try {
            groupService.deleteById(groupId);
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException("Group not found: " + groupId + " - " + e);
        }
    }
}
