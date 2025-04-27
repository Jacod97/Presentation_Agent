package com.ll.backend.domain.member.member.repository;

import com.ll.backend.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
