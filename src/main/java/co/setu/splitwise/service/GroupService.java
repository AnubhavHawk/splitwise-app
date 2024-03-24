package co.setu.splitwise.service;

import co.setu.splitwise.model.Group;
import co.setu.splitwise.model.User;
import co.setu.splitwise.repository.GroupRepository;
import co.setu.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static co.setu.splitwise.util.Util.randomId;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group createGroup(Group group) {
        group.setGroupId(randomId());
        // Before saving checking if the users are registered.
        User creator = userRepository.getById(group.getCreatedBy().getUserId());
        if(creator == null) {
            throw new IllegalArgumentException("Group Admin "+ group.getCreatedBy().getUserId() +" is not registered");
        }
        validateGroupMemberRegistration(group.getGroupMembers());
        Group saved = groupRepository.save(group);
        return saved;
    }

    public Group addMember(String groupId, List<User> userList) {
        int totalMemberCount = 0;
        Group group = groupRepository.getById(groupId);

        if(group == null) {
            throw new IllegalArgumentException("Group Id is not valid");
        }
        if(userList == null) {
            throw new IllegalArgumentException("Members list can not be null");
        }
        else {
            validateGroupMemberRegistration(group.getGroupMembers());
            group.getGroupMembers().addAll(userList);

            // Update to DB
            Group saved = groupRepository.save(group);
            return saved;
        }
    }

    private void validateGroupMemberRegistration(Set<User> groupMembers) {
        List<User> membersIndDB =  userRepository.findAllById(groupMembers.stream()
                .map(User::getUserId)
                .collect(Collectors.toList()));
        if(membersIndDB.size() != groupMembers.size()) {
            groupMembers.removeAll(membersIndDB);
            throw new IllegalArgumentException("Some members are not registered: " + groupMembers);
        }
    }
}