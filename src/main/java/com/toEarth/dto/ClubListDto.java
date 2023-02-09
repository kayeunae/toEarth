package com.toEarth.dto;

import com.toEarth.entity.Club;
import com.toEarth.entity.ClubMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubListDto {
	
	private String clubName;
	
	private String region;
	
	private String keyword;
	
	private String imgUrl;
	
	private ClubMember clubMember;
	
	public ClubListDto(Club club, ClubMember clubMember) {
		this.clubName = club.getClubName();
		this.region = club.getRegion();
		this.keyword = club.getKeyword();
		this.imgUrl = club.getImgUrl();
		this.clubMember = clubMember;
	}
}
