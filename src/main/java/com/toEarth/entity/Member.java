package com.toEarth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.toEarth.constant.Grade;
import com.toEarth.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long member_id;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Grade grade;
	
	private String phone;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
		member.setPhone(memberDto.getPhone());
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		member.setPassword(password);
		
		member.setGrade(Grade.BASIC);
		
		return member;
	}
	
	
	
}
