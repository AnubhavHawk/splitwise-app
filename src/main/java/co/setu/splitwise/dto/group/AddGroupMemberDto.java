package co.setu.splitwise.dto.group;

import co.setu.splitwise.model.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddGroupMemberDto {
    private String groupId;
    private List<String> groupMemberList;

    public List<User> mapUser() {
        return groupMemberList.stream().map(userId ->
                User.builder().userId(userId).build()).collect(Collectors.toList());
    }
}
