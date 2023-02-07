package com.toEarth.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toEarth.entity.Club;
import com.toEarth.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {
	private final ClubRepository clubRepository;
	
	public Club saveClub(Club club) throws Exception {
		return clubRepository.save(club);
	}
	
	
	
}
