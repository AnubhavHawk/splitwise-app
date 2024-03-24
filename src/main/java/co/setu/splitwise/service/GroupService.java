package co.setu.splitwise.service;

import co.setu.splitwise.model.Group;
import co.setu.splitwise.model.User;
import co.setu.splitwise.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public boolean addMember(String groupId, List<User> userList) {
        Group group = groupRepository.getById(groupId);
        if(group == null) {
            throw new IllegalArgumentException("Group Id is not valid");
        }
        // Check if user is already present
        group.getGroupMembers().containsAll(userList);

    }
}
