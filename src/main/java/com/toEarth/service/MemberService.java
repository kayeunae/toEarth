package com.toEarth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toEarth.entity.Member;
import com.toEarth.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service	//service 클래스의 역할을 한다!
@Transactional	//서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다.
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getGrade().toString())
				.build();
	}
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
				
	}
	
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

}
