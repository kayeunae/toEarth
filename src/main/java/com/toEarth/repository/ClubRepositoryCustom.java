package com.toEarth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.toEarth.dto.ClubDto;
import com.toEarth.dto.ClubListDto;
import com.toEarth.dto.ClubSearchDto;
import com.toEarth.entity.Club;

//사용자 정의 인터페이스 
public interface ClubRepositoryCustom {
	//검색한 데이터를 페이징 처리해서 받아온다.
	
	Page<ClubListDto> getClubList(ClubSearchDto clubSearchDto, Pageable pageable);
	
	Page<Club> getClubList(ClubSearchDto clubSearchDto, Pageable pageable);
}
