package co.setu.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "splitwise_group") // group is a reserved keyword in SQL, so we need to rename the table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String groupId = UUID.randomUUID().toString();
    private String groupName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User createdBy;
    @OneToMany
    private Set<User> groupMembers;
}
