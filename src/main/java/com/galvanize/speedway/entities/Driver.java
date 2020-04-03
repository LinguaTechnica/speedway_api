package com.galvanize.speedway.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private int age;

    public Driver () {}

    public Driver(String nickname, String firstName, String lastName, LocalDate birthday) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthday, now);
        this.age = period.getYears();
    }
}
