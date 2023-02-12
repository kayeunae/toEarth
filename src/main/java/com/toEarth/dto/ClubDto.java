package com.toEarth.dto;

import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;
import com.toEarth.entity.Club;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubDto {
	private Long id;
	
	private String clubName;
	
	private String region;
	
	private String keyword;
	
	private String announce;
	
	private String imgName;
	
	private String imgUrl;
	
	private String oriImgName;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Club creatClub() {
		return modelMapper.map(this, Club.class);
	}
	
	public static ClubDto of(Club club) {
		return modelMapper.map(club, ClubDto.class);
	}
	
}
