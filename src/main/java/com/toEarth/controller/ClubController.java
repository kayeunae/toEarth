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
import com.toEarth.service.ClubImgService;
import com.toEarth.service.ClubService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/club")
@Controller
@RequiredArgsConstructor
public class ClubController {
	private final ClubService clubService;
	private final ClubImgService clubImgService;

	@GetMapping(value = "create")
	public String crateClub(Model model) {
		model.addAttribute("clubDto", new ClubDto());
		return "club/createClub";
	}

	
	@PostMapping(value="createClub")
	public String newClub(@Valid ClubDto clubDto, BindingResult bindingResult, 
			Model model, @RequestParam("file") MultipartFile file) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "값을 받아오는 동안 문제가 생겼습니다.");
			return "club/createClub";
		}
		
		if(file == null) {
			model.addAttribute("errorMessage", "이미지 등록은 필수입니다.");
			return "club/createClub";
		}
		
		try {
			Club club = Club.createClub(clubDto);
			Club savedClub = clubService.saveClub(club);
			
			clubImgService.saveClubImg(savedClub, file);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "소모임 생성 중에 에러가 발생했습니다.");
			return "club/createClub";
		}
		
		return "main";
	}
	

	@GetMapping(value = "list")
	public String clubList() {
		return "club/clubList";
	}

}
