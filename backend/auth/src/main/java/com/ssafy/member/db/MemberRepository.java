package com.ssafy.member.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    boolean existsByEmail(String email);
    Optional<MemberEntity> findByEmail(String email);
    MemberEntity findByRandomMemberId(String randomMemberId);
    public List<MemberEntity> findByRole(Role role);

}
