package com.ll.backend.domain.member.member.entity;

import io.jsonwebtoken.Claims;
import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique=true)
    String username;
    String password;
    String nickname;
    String email;
    String role;

    public Member() {}

    public Member(String username, String password, String nickname, String email, String role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public Member(Claims claims) {
        this.id = claims.get("memberId", Long.class);
        this.username = claims.get("username", String.class);
        this.role = claims.get("role", String.class);
        this.nickname = claims.get("nickname", String.class);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
