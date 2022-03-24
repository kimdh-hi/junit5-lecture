package com.ex.junit5lecture.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    public Member() {
    }

    public Member(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long memberId) {
        this.id = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }
}
