package co.setu.splitwise.dto.group;

import lombok.Getter;

import java.util.List;

@Getter
public class AddGroupMemberDto {
    private String groupId;
    private List<String> groupMemberList;
}
