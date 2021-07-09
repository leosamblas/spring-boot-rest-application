package com.leosamblas.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.leosamblas.rest.entity.AlbumEntity;

public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Long> {

	@Query(value = "SELECT * FROM album WHERE (?1 is null or title LIKE CONCAT('%',?1,'%'))", 
			countQuery = "SELECT count(*) FROM album WHERE (?1 is null or title LIKE CONCAT('%',?1,'%'))", 
			nativeQuery = true)
	Page<AlbumEntity> findByTitleContaining(String title, Pageable pageable);

}
