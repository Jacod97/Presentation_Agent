package com.ll.backend.domain.member.member.controller;

import com.ll.backend.domain.member.member.entity.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    record LoginRequest (
            String id,
            String password
    ){}

    @PostMapping("/login")
    public ResponseEntity<Member> login(
            @RequestBody LoginRequest dto
    ) {
        return ResponseEntity.ok(new Member("name", "", "", ""));
    }
}
