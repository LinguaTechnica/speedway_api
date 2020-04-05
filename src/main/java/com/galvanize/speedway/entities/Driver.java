package com.galvanize.speedway.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
