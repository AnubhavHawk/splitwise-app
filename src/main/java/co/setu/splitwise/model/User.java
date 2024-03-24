package co.setu.splitwise.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    private String userId = UUID.randomUUID().toString();
    private String userName;
    private LocalDateTime registeredAt;
    private String mobile;
}
