package com.toEarth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
public class MemberController {

	@GetMapping(value="sign")
	public String signUp() {
		return "member/sign";
	}
	
	@GetMapping(value="login")
	public String logIn() {
		return "member/login";
	}
}
