package co.setu.splitwise.controller;

import co.setu.splitwise.dto.group.AddGroupMemberDto;
import co.setu.splitwise.dto.group.CreateGroupDTO;
import co.setu.splitwise.model.Group;
import co.setu.splitwise.model.RegisteredUser;
import co.setu.splitwise.repository.UserRepository;
import co.setu.splitwise.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static co.setu.splitwise.util.Util.jsonResponse;

@RestController(value = "/group")
public class GroupApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    private static final Logger logger = LoggerFactory.getLogger(GroupApiController.class);

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody CreateGroupDTO createGroupDTO) {
        Group created = groupService.createGroup(map(createGroupDTO));
        return ResponseEntity.ok().body("Created: " + created);
    }
    private Group map(CreateGroupDTO createGroupDTO) {
        RegisteredUser createdBy = RegisteredUser.builder().userId(createGroupDTO.getGroupAdmin()).build();
        List<RegisteredUser> members = new ArrayList<>();
        for(String memeberId: createGroupDTO.getGroupMemberIdList()) {
            members.add(RegisteredUser.builder().userId(memeberId).build());
        }
        return Group.builder()
                .groupName(createGroupDTO.getGroupName())
                .createdBy(createdBy)
                .groupMembers(members)
                .build();
    }

    @PostMapping("/add-member")
    public ResponseEntity addGroupMember(@RequestBody AddGroupMemberDto addGroupMemberDto) { // BUG: It only stores the new member and removes the existing group members
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
        List<RegisteredUser> members = groupService.getGroupMembers(groupId);
        return jsonResponse("members", members);
    }
}
