package com.toEarth.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.toEarth.dto.ClubDto;
import com.toEarth.entity.Club;
import com.toEarth.service.ClubService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/club")
@Controller
public class ClubController {
	private final ClubService clubService;

	@GetMapping(value = "create")
	public String crateClub(Model model) {
		model.addAttribute("clubDto", new ClubDto());
		return "club/createClub";
	}

	
	@PostMapping(value="create") public String newClub(@Valid ClubDto clubDto, BindingResult bindingResult, 
			Model model, @RequestParam("file") MultipartFile file) {
		
		if(bindingResult.hasErrors()) {
			return "club/createClub";
		}
		
		try {
			Club club = Club.createClub(clubDto);
			club = 
			
		} catch (Exception e) {

		}
		
		
	}
	

	@GetMapping(value = "list")
	public String clubList() {
		return "club/clubList";
	}

}
