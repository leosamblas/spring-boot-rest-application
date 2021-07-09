package com.leosamblas.rest.model;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Links;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinksCustom {

	private String atual = null;
	private String primeira = null;
	private String anterior = null;
	private String proxima = null;
	private String ultima = null;

	public static LinksCustom montarLinks(Links links) {

		LinksCustom linksCustom = new LinksCustom();

		linksCustom.setAtual(links.getLink(IanaLinkRelations.SELF).isPresent()
				? links.getLink(IanaLinkRelations.SELF).get().getHref()
				: null);

		linksCustom.setPrimeira(links.getLink(IanaLinkRelations.FIRST).isPresent()
				? links.getLink(IanaLinkRelations.FIRST).get().getHref()
				: null);

		linksCustom.setAnterior(links.getLink(IanaLinkRelations.PREV).isPresent()
				? links.getLink(IanaLinkRelations.PREV).get().getHref()
				: null);

		linksCustom.setProxima(links.getLink(IanaLinkRelations.NEXT).isPresent()
				? links.getLink(IanaLinkRelations.NEXT).get().getHref()
				: null);

		linksCustom.setUltima(links.getLink(IanaLinkRelations.LAST).isPresent()
				? links.getLink(IanaLinkRelations.LAST).get().getHref()
				: null);

		return linksCustom;
	}
}
