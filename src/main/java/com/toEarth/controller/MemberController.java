package com.toEarth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toEarth.dto.SignDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
public class MemberController {

	@GetMapping(value="sign")
	public String sign() {
		return "member/sign";
	}
	
	@PostMapping(value="signup")
	public String signUp(Model model) {
		model.addAttribute("signDto", new SignDto());
		return "member/sign";
	}
	
	@GetMapping(value="login")
	public String logIn() {
		return "member/login";
	}
}
