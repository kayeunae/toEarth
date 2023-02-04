package com.toEarth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toEarth.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
}
