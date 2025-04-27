package com.ll.backend.domain.member.member.service;

import com.ll.backend.domain.member.member.entity.Member;
import com.ll.backend.domain.member.member.repository.MemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Member join(String username, String password, String nickname, String email) {
        String encodingPassword = bCryptPasswordEncoder.encode(password);

        Member member = new Member(username, encodingPassword, nickname, email);

        try {
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("이미 존재하는 사용자 정보입니다");
        }
    }
}
