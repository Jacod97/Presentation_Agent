package com.ll.backend.domain.member.member.service;

import com.ll.backend.domain.member.member.entity.Member;
import com.ll.backend.domain.member.member.repository.MemberRepository;
import com.ll.backend.global.exception.GlobalErrorCode;
import com.ll.backend.global.exception.GlobalException;
import com.ll.backend.global.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final HttpServletResponse response;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil, HttpServletResponse response) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
        this.response = response;
    }

    public Member join(String username, String password, String nickname, String email) {
        String encodingPassword = bCryptPasswordEncoder.encode(password);

        Member member = new Member(username, encodingPassword, nickname, email, "ROLE_MEMBER");

        try {
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new GlobalException(GlobalErrorCode.ALREADY_USER);
        }
    }

    public void login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.NON_EXISTING_USERNAME));

        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            throw new GlobalException(GlobalErrorCode.INCORRECT_PASSWORD);
        }

        createToken(member);
    }

    private void createToken(Member member) {
        String accessToken = jwtUtil.generateAccessToken(member, 1);
        String refreshToken = jwtUtil.generateAccessToken(member, 2);

        String refreshCookie = ResponseCookie
                .from("refresh", refreshToken)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build()
                .toString();

        response.addHeader("Set-Cookie", refreshCookie);
        response.addHeader("accessToken", accessToken);
    }
}
