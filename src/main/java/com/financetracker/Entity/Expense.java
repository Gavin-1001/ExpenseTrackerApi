package com.financetracker.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="expense")
public class Expense {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Column()
    private String expenseName;

    @Column()
    private String expenseDescription;

    @Column()
    private double price;

    @Column()
    private LocalDateTime dateCreated;

}
