package co.setu.splitwise.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Expense {
    @Id
    private String expenseId = UUID.randomUUID().toString();
    private String description;
    private Double amount;
    @OneToOne
    private User createdBy;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;
}