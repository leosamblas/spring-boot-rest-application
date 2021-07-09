package com.leosamblas.rest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leosamblas.rest.assembers.ActorModelAssembler;
import com.leosamblas.rest.assembers.AlbumModelAssembler;
import com.leosamblas.rest.entity.ActorEntity;
import com.leosamblas.rest.entity.AlbumEntity;
import com.leosamblas.rest.model.ActorModel;
import com.leosamblas.rest.model.AlbumModel;
import com.leosamblas.rest.model.AlbumModelResponseData;
import com.leosamblas.rest.model.LinksCustom;
import com.leosamblas.rest.repository.ActorRepository;
import com.leosamblas.rest.repository.AlbumRepository;

@RestController
public class WebController {

	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private ActorModelAssembler actorModelAssembler;
	
	@Autowired
	private AlbumModelAssembler albumModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AlbumEntity> pagedResourcesAssembler;
	
	@GetMapping("/api/actors")
	public ResponseEntity<CollectionModel<ActorModel>> getAllActors() 
	{
		List<ActorEntity> actorEntities = (List<ActorEntity>) actorRepository.findAll();
		
		return new ResponseEntity<>(
				actorModelAssembler.toCollectionModel(actorEntities), 
				HttpStatus.OK);
	}
	
	@GetMapping("/api/actors/{id}")
	public ResponseEntity<ActorModel> getActorById(@PathVariable("id") Long id) 
	{
		return actorRepository.findById(id) 
				.map(actorModelAssembler::toModel) 
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/api/albums")
	public ResponseEntity<CollectionModel<AlbumModel>> getAllAlbums() 
	{
		List<AlbumEntity> albumEntities = (List<AlbumEntity>) albumRepository.findAll();
		
		return new ResponseEntity<>(
				albumModelAssembler.toCollectionModel(albumEntities), 
				HttpStatus.OK);
	}
		
	@GetMapping("/api/albums/{id}")
	public ResponseEntity<AlbumModel> getAlbumById(@PathVariable("id") Long id) 
	{
		return albumRepository.findById(id) 
				.map(albumModelAssembler::toModel) 
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Pageable	
	@GetMapping("/api/albums-pageable")
	public ResponseEntity<AlbumModelResponseData> getAllAlbums(
			@RequestParam(required = false) String title,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "2") int size,
	        @RequestParam(defaultValue = "ASC,title") String sort) {
		
		String[] sortParam = sort.split(",");
		Order order = new Order(Direction.fromString(sortParam[0]), sortParam[1]);
		Pageable pageable = PageRequest.of(page, size, Sort.by(order));
		
		Page<AlbumEntity> albumEntities = albumRepository.findAll(pageable);
		
		if(albumEntities.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		PagedModel<AlbumModel> collModel = pagedResourcesAssembler
							.toModel(albumEntities, albumModelAssembler);
		
		LinksCustom links = LinksCustom.montarLinks(collModel.getLinks());
		
		AlbumModelResponseData response = new AlbumModelResponseData(collModel.getContent(), links); 		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
