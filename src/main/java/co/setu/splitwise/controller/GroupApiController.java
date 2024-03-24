package co.setu.splitwise.controller;

import co.setu.splitwise.dto.group.AddGroupMemberDto;
import co.setu.splitwise.dto.group.CreateGroupDTO;
import co.setu.splitwise.model.Group;
import co.setu.splitwise.model.User;
import co.setu.splitwise.repository.UserRepository;
import co.setu.splitwise.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("group")
public class GroupApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    private static final Logger logger = LoggerFactory.getLogger(GroupApiController.class);

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody CreateGroupDTO createGroupDTO) {
        Group created = groupService.createGroup(createGroupDTO.mapToGroup());
        return ResponseEntity.ok().body("Created: " + created);
    }

    @PostMapping("/add-member")
    public ResponseEntity addGroupMember(@RequestBody AddGroupMemberDto addGroupMemberDto) {
        int totalCount = 0;
        try {
            totalCount = groupService.addMember(addGroupMemberDto.getGroupId(), addGroupMemberDto.mapUser()).getGroupMembers().size();
        }
        catch (IllegalArgumentException e) {
            logger.error("Unable to add member to group {}", e.getMessage(), e);
        }
        return ResponseEntity.ok().body(totalCount);
    }

    @GetMapping("/members/{groupId}")
    public ResponseEntity getGroupMembers(@PathVariable String groupId) {

        return null;
    }

    @GetMapping("/test")
    public Object getUsers() {
        List<User> all = userRepository.findAll();
        return all;
    }
}
