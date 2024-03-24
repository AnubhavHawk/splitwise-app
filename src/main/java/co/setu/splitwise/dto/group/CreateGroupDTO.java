package co.setu.splitwise.dto.group;

import co.setu.splitwise.model.User;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateGroupDTO {
    private String groupName;
    private List<User> groupMemberList;
    private String groupAdmin;
}
