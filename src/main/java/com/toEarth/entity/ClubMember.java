package com.toEarth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="club_member")
@Getter
@Setter
@ToString
public class ClubMember {
	
	@Id
	@Column(name="club_member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="club_id")
	private Club club;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	private String mention;
	
	public static ClubMember createClubMember (Club club, Member member, String mention) {
		ClubMember clubMember = new ClubMember();
		clubMember.setClub(club);
		clubMember.setMember(member);
		clubMember.setMention(mention);
		
		return clubMember;
	}
	
	
	
	
}
