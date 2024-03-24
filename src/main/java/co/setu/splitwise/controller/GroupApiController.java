package co.setu.splitwise.controller;

import co.setu.splitwise.dto.group.AddGroupMemberDto;
import co.setu.splitwise.dto.group.CreateGroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("group")
public class GroupApiController {

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody CreateGroupDTO createGroupDTO) {

        return null;
    }

    @PostMapping("/add-member")
    public ResponseEntity addGroupMember(@RequestBody AddGroupMemberDto addGroupMemberDto) {

        return null;
    }

    @GetMapping("/members/{groupId}")
    public ResponseEntity getGroupMembers(@PathVariable String groupId) {

        return null;
    }
}
