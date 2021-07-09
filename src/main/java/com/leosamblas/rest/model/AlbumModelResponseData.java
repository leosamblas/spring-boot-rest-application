package com.leosamblas.rest.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumModelResponseData {
	
	@JsonProperty("data")
	private Collection<AlbumModel> albumModelResponse;
	
	private LinksCustom links;
}
