package com.toEarth.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toEarth.dto.MemberDto;
import com.toEarth.entity.Member;
import com.toEarth.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping(value="sign")
	public String sign(Model model) {
		model.addAttribute("signDto", new MemberDto());
		return "member/sign";
	}
	
	@PostMapping(value="signup")
	public String signUp(@Valid MemberDto signDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/sign";
		}
		
		try {
			Member member = Member.createMember(signDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/sign";
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value="login")
	public String logIn() {
		return "member/login";
	}
	
	@GetMapping(value="login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/login";
	}
	
}
