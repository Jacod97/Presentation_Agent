package com.ll.backend.domain.member.member.controller;

import com.ll.backend.domain.member.member.entity.Member;
import com.ll.backend.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    record LoginRequest (
            String username,
            String password
    ){}

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest dto
    ) {
        memberService.login(dto.username, dto.password);

        return ResponseEntity.ok(Map.of("message", "로그인 성공"));
    }

    record RegisterRequest (
            @NotBlank
            @Length(min = 4)
            String username,

            @NotBlank
            @Length(min = 4)
            String password,

            @NotBlank
            @Length(min = 2)
            String nickname,

            @NotBlank
            @Email
            String email
    ){}

    @PostMapping("/join")
    public ResponseEntity<Member> join(
            @Valid @RequestBody RegisterRequest dto
    ) {
        Member member = memberService.join(dto.username, dto.password, dto.nickname, dto.email);
        return ResponseEntity.ok(member);
    }
}
