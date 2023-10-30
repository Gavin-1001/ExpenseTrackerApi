package com.financetracker.Entity;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
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
    private String expenseTitle;

    @Column()
    private String expenseDescription;

    @Column()
    private double expensePrice;

    @Column()
    private LocalDateTime timestampExpenseCreated;

    @Column()
    private String expenseCategory;

    @Column()
    private LocalDate expenseDate;


}
