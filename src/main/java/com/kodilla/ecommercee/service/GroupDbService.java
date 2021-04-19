package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupDbService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group){
        return groupRepository.save(group);
    }

    public void deleteById(final Long id){
        groupRepository.deleteById(id);
    }

    public Optional<Group> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

    public GroupDto updateGroup(Long groupId, GroupDto groupDto){
        return groupRepository.findById(groupId)
                .map(group -> {
                    group.setName(groupDto.getName());
                    groupRepository.save(group);
                    return groupMapper.mapToGroupDto(group);
                })
                .orElseThrow(() -> new GroupNotFoundException("Group not found!"));
    }

}