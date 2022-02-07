package com.example.desafiobackend.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname")
    private String nickname;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registration_date")
    private Timestamp registration_date;

    public User(Long id, String nickname, Timestamp registration_date) {
        this.id = id;
        this.nickname = nickname;
        this.registration_date = registration_date;
    }

    public User() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
