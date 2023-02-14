package com.toEarth.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	//소모임 상세페이지 가져오기
	@Transactional(readOnly=true)
	public ClubDto getclubDtl(Long clubId) {
		//club 테이블에 있는 데이터를 가져온다.
		Club club= clubRepository.findById(clubId)
								 .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> DTO 객체로 변환
		ClubDto clubDto = ClubDto.of(club);
		
		return clubDto;
	}
	
	//소모임 정보 수정하기
	public Long updateClub(ClubDto clubDto, MultipartFile file) throws Exception {
		Club club = clubRepository.findById(clubDto.getId())
								  .orElseThrow(EntityNotFoundException::new);
		
		club.updateClub(clubDto);
		
		return club.getId();
	}
	
	//소모임 삭제
	public void deleteClub(Long id) {
		Club club = clubRepository.findById(id)
								  .orElseThrow(EntityNotFoundException::new);
		clubRepository.delete(club);
	}

	
	
	
}
