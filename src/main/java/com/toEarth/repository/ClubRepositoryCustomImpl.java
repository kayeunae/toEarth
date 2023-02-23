package com.toEarth.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toEarth.dto.ClubSearchDto;
import com.toEarth.entity.Club;
import com.toEarth.entity.QClub;

public class ClubRepositoryCustomImpl implements ClubRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public ClubRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("clubName", searchBy)) {
			return QClub.club.clubName.like("%" + searchQuery + "%" );
		} else if (StringUtils.equals("keyword", searchBy)) {
			return QClub.club.keyword.like("%" + searchQuery + "%" );
			
			
			
			
		}
		return null;
	}
	
	private BooleanExpression clubNameLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QClub.club.clubName.like("%" + searchQuery + "%");
	}
	
	@Override
	public Page<Club> getClubList(ClubSearchDto clubSearchDto, Pageable pageable) {
		List<Club> content = queryFactory
				.selectFrom(QClub.club)
				.where(clubNameLike(clubSearchDto.getSearchQuery()))
				.orderBy(QClub.club.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QClub.club)
				.where(searchByLike(clubSearchDto.getSearchBy(), clubSearchDto.getSearchQuery()))
				.fetchOne();
		return new PageImpl<>(content, pageable, total);
	}
	
	

}
