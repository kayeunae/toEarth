package com.toEarth.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toEarth.dto.ClubDto;
import com.toEarth.dto.ClubListDto;
import com.toEarth.dto.ClubSearchDto;
import com.toEarth.entity.Club;
import com.toEarth.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {
	private final ClubRepository clubRepository;
	
	public Club saveClub(Club club) throws Exception {
		return clubRepository.save(club);
	}
	
	//소모임 리스트 가져오기
	@Transactional(readOnly = true)
	public List<Club> getClubListBasic() {
		return clubRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Club> getClubList(ClubSearchDto clubSearchDto, Pageable pageable) {
		return clubRepository.getClubList(clubSearchDto, pageable);
	}
	
	@Transactional(readOnly=true)
	public ClubDto getclubDtl(Long clubId) {
		Club club= clubRepository.findById(clubId)
								 .orElseThrow(EntityNotFoundException::new);
		
		ClubDto clubDto = ClubDto.of(club);
	}

	
	
	
}
