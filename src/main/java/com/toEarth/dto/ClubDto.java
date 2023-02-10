package com.toEarth.dto;

import com.querydsl.core.annotations.QueryProjection;

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
	
}
