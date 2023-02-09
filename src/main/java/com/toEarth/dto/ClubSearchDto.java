package com.toEarth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubSearchDto {
	private String regionBy;
	private String searchQuery = "";
}
