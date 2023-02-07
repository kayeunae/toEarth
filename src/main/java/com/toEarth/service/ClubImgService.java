package com.toEarth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	public final FileService fileService;
	
	public void saveClubImg()

}
