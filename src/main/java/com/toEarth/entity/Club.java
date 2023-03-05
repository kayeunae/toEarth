package com.toEarth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.toEarth.dto.ClubDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity   
@Table(name="club")
@Getter
@Setter
@ToString
public class Club {
	
	@Id
	@Column(name="club_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String clubName;
	
	@Column(nullable = false)
	private String region;
	
	@Column(nullable = false)
	private String keyword;
	
	@Lob
	@Column(nullable = false)
	private String announce;
	
	@Column
	private String imgName;
	
	@Column
	private String imgUrl;
	
	@Column
	private String oriImgName;
	
	public static Club createClub(ClubDto clubDto) {
		Club club = new Club();
		club.setClubName(clubDto.getClubName());
		club.setRegion(clubDto.getRegion());
		club.setKeyword(clubDto.getKeyword());
		club.setAnnounce(clubDto.getAnnounce());
		
		return club;
	}
	
	public void updateImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
	
	public void updateClub(ClubDto clubDto) {
		this.clubName = clubDto.getClubName();
		this.region = clubDto.getRegion();
		this.keyword = clubDto.getKeyword();
		this.announce = clubDto.getAnnounce();
	}
	
	
	
	
}
