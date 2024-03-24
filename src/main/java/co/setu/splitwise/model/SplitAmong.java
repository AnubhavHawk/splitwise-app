package co.setu.splitwise.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
public class SplitAmong {
    @Id
    private String splitId = UUID.randomUUID().toString();
    @OneToOne
    private Expense expense;

    @OneToOne
    private User user;
    private ExpenseStatus status;
}
