package co.setu.splitwise.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "splitwise_group") // group is a reserved keyword in SQL, so we need to rename the table
@Data
public class Group {
    @Id
    private String groupId = UUID.randomUUID().toString();
    private String groupName;
    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;
    @OneToMany
    private List<User> groupMembers;
}
