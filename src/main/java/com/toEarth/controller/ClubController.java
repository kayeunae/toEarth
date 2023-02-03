package com.toEarth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@RequestMapping("/club")
@Controller
public class ClubController {

	@GetMapping(value="create")
	public String signUp() {
		return "club/createClub";
	}
	
	@GetMapping(value="list")
	public String clubList() {
		return "club/clubList";
	}
}
