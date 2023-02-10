package com.toEarth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.toEarth.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long>,
	QuerydslPredicateExecutor<Club>, ClubRepositoryCustom{
	
	// select * from Club where clubname = ?;
	List<Club> findByClubName(String clubName);
}

