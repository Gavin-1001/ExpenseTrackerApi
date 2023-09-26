package com.financetracker.Entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private String emailAddress;

    @Column()
    private String dateOfBirth;

    @Column()
    private String phoneNumber;

    @Column()
    private String userGender;

    @Column()
    private String streetAddress;

    @Column()
    private String homeCity;

    @Column()
    private String homeAreaCode;

    @Column()
    private String homeCountry;


    @Column()
    private String collegeDegree;

    @Column()
    private String collegeName;

    @Column()
    private String collegeGrade;

    /*
    @Column()
    private String cvUrl;

//     */
//    add a url for uploading cv


    public User(Long id, String firstName, String lastName, String emailAddress, String dateOfBirth, String phoneNumber, String streetAddress, String collegeDegree, String collegeName, String collegeGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.collegeDegree = collegeDegree;
        this.collegeName = collegeName;
        this.collegeGrade = collegeGrade;

    }

    public User(){}
}

