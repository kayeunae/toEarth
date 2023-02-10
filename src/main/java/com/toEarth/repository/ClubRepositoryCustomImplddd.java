package com.toEarth.repository;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class ClubRepositoryCustomImplddd implements ClubRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public ClubRepositoryCustomImplddd(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


}
