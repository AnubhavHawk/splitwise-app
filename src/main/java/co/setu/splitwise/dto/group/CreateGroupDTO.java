package co.setu.splitwise.dto.group;

import co.setu.splitwise.model.Group;
import co.setu.splitwise.model.User;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
public class CreateGroupDTO {
    private String groupName;
    private List<String> groupMemberIdList; // Only userId
    private String groupAdmin;

    public Group mapToGroup() {
        Set userSet = new TreeSet();
        List<User> memberList = groupMemberIdList.stream()
                .map(id -> User.builder().userId(id).build())
                .collect(Collectors.toList());
        userSet.addAll(memberList);

        return Group.builder()
                .groupName(groupName)
                .groupMembers(userSet)
                .createdBy(User.builder().userId(groupAdmin).build())
                .build();
    }
}
