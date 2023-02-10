package com.toEarth.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toEarth.entity.Club;
import com.toEarth.entity.ClubMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubListDto {
	private Long id;
	
	private String clubName;
	
	private String region;
	
	private String keyword;
	
	private String imgUrl;
	
	private ClubMember clubMember;
	
	@QueryProjection
	public ClubListDto(Club club, ClubMember clubMember) {
		this.id = club.getId();
		this.clubName = club.getClubName();
		this.region = club.getRegion();
		this.keyword = club.getKeyword();
		this.imgUrl = club.getImgUrl();
		this.clubMember = clubMember;
	}
}
