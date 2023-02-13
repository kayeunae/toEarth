package com.toEarth.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.toEarth.dto.ClubDto;
import com.toEarth.dto.ClubListDto;
import com.toEarth.dto.ClubSearchDto;
import com.toEarth.entity.Club;
import com.toEarth.entity.ClubMember;
import com.toEarth.service.ClubImgService;
import com.toEarth.service.ClubService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/club")
@Controller
@RequiredArgsConstructor
public class ClubController {
	private final ClubService clubService;
	private final ClubImgService clubImgService;

	// 소모임 생성
	@GetMapping(value = "create")
	public String crateClub(Model model) {
		model.addAttribute("clubDto", new ClubDto());
		return "club/createClub";
	}

	// 소모임 생성 버튼 클릭
	@PostMapping(value = "createClub")
	public String newClub(@Valid ClubDto clubDto, BindingResult bindingResult, Model model,
			@RequestParam("file") MultipartFile file) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "값을 받아오는 동안 문제가 생겼습니다.");
			return "club/createClub";
		}

		if (file == null) {
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

	// 소모임 리스트 출력
	@GetMapping(value = { "list", "list/{page}" })
	public String clubList(ClubSearchDto clubSearchDto, Optional<Integer> page, Model model) {
//		Page<ClubListDto> clubs = clubService.getClubList(clubSearchDto, pageable);

		// page.isPresent() ? page.get() : 0 => url 경로에 페이지 넘버가 있으면 그걸 출력, 아니면 0
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);

		Page<Club> clubs = clubService.getClubList(clubSearchDto, pageable);

		model.addAttribute("clubs", clubs);
		model.addAttribute("clubSearchDto", clubSearchDto);
		model.addAttribute("maxPage", 5);

//		List<Club> clubs = clubService.getClubListBasic();
//		model.addAttribute("clubs", clubs);

		return "club/clubList";

	}

	// 소모임 둘러보기 버튼 클릭(소모임 상세 페이지)
	@GetMapping(value = "{club_id}")
	public String clubDetail(Model model, @PathVariable("club_id") Long clubId) {
		ClubDto clubDto = clubService.getclubDtl(clubId);
		model.addAttribute("club", clubDto);
		return "club/clubDtl";
	}

	// 소모임 수정 페이지 띄우기
	@GetMapping(value = "/modify/{club_id}")
	public String clubModifyPage(@PathVariable("club_id") Long clubId, Model model) {

		try {
			ClubDto clubDto = clubService.getclubDtl(clubId);
			model.addAttribute("clubDto", clubDto);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다.");
			model.addAttribute("newclubDto", new ClubDto());
			return "club/clubMdf";
		}
		return "club/clubMdf";
	}

	
	 @PostMapping(value = "/modify/{club_id}")
	 public String clubUpdate(@Valid ClubDto clubDto, BindingResult bindingResult,
			 Model model, @RequestParam("file") MultipartFile file) {
		 if(bindingResult.hasErrors()) {
			 return "club/clubMdf";
		 }
		 
		if (file == null) {
				model.addAttribute("errorMessage", "이미지 등록은 필수입니다.");
				return "club/clubMdf";
		}
		
		try {
			clubService.updateClub(clubDto, file);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "소모임 정보 수정 중 에러가 발생하였습니다.");
			return "club/clubMdf";
		}
		return "redirect:/";
		
	 }
	 

}
