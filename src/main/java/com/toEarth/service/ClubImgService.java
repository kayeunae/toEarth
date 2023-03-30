package com.toEarth.service;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.toEarth.entity.Club;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	public final FileService fileService;
	
	public void saveClubImg(Club club, MultipartFile imgfile) throws Exception {
		String oriImgName = imgfile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, imgfile.getBytes());
			imgUrl = "/images/item/"+imgName;
		}
		
		club.updateImg(oriImgName, imgName, imgUrl);
	}

}
