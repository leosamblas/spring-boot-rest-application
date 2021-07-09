package com.leosamblas.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.leosamblas.rest.entity.ActorEntity;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long>{

}
