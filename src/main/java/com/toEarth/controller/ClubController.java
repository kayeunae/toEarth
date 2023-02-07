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

import lombok.RequiredArgsConstructor;

@RequestMapping("/club")
@Controller
public class ClubController {

	@GetMapping(value = "create")
	public String crateClub(Model model) {
		model.addAttribute("clubDto", new ClubDto());
		return "club/createClub";
	}

	
	@PostMapping(value="create") public String newClub(@Valid ClubDto clubDto, BindingResult bindingResult, 
			Model model, @RequestParam("file") MultipartFile file) {
		Club club = new Club();
		club = club.createClub(clubDto);
		
	}
	

	@GetMapping(value = "list")
	public String clubList() {
		return "club/clubList";
	}

}
