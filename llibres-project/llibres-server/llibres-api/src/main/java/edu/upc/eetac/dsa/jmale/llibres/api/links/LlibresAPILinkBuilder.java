package edu.upc.eetac.dsa.jmale.llibres.api.links;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import edu.upc.eetac.dsa.jmale.llibres.api.LlibreResource;
import edu.upc.eetac.dsa.jmale.llibres.api.LlibresRootAPIResource;
import edu.upc.eetac.dsa.jmale.llibres.api.MediaType;
import edu.upc.eetac.dsa.jmale.llibres.api.model.Llibre;

public class LlibresAPILinkBuilder {

	public final static Link buildURIRootAPI(UriInfo uriInfo) {
		URI uriRoot = uriInfo.getBaseUriBuilder()
				.path(LlibresRootAPIResource.class).build();

		Link link = new Link();
		link.setUri(uriRoot.toString());
		link.setRel("self bookmark");
		link.setTitle("llibres API");
		link.setType(MediaType.LLIBRES_API_LINK_COLLECTION);

		return link;
	}

	public static final Link buildURIStings(UriInfo uriInfo, String rel) {
		return buildURIStings(uriInfo, null, null, null, rel);
	}

	public static final Link buildURIStings(UriInfo uriInfo, String offset,
			String length, String username, String rel) {

		URI uriLlibres;
		if (offset == null && length == null)
			uriLlibres = uriInfo.getBaseUriBuilder().path(LlibreResource.class)
					.build();
		else {

			if (username == null)
				uriLlibres = uriInfo.getBaseUriBuilder()
						.path(LlibreResource.class)
						.queryParam("offset", offset)
						.queryParam("length", length).build();
			else
				uriLlibres = uriInfo.getBaseUriBuilder()
						.path(LlibreResource.class)
						.queryParam("offset", offset)
						.queryParam("length", length)
						.queryParam("username", username).build();
		}

		Link self = new Link();
		self.setUri(uriLlibres.toString());
		self.setRel(rel);
		self.setTitle("llibres collection");
		self.setType(MediaType.LLIBRES_API_LIBRO_COLLECTION);

		return self;
	}

	public static final Link buildTemplatedURIStings(UriInfo uriInfo, String rel) {

		return buildTemplatedURIStings(uriInfo, rel, false);
	}

	public static final Link buildTemplatedURIStings(UriInfo uriInfo,
			String rel, boolean username) {
		URI uriLlibres;
		if (username)
			uriLlibres = uriInfo.getBaseUriBuilder().path(LlibreResource.class)
					.queryParam("offset", "{offset}")
					.queryParam("length", "{length}")
					.queryParam("username", "{username}").build();
		else
			uriLlibres = uriInfo.getBaseUriBuilder().path(LlibreResource.class)
					.queryParam("offset", "{offset}")
					.queryParam("length", "{length}").build();

		Link link = new Link();
		link.setUri(URITemplateBuilder.buildTemplatedURI(uriLlibres));
		link.setRel(rel);
		if (username)
			link.setTitle("llibre collection resource filtered by {username}");
		else
			link.setTitle("Stings collection resource");
		link.setType(MediaType.LLIBRES_API_LIBRO_COLLECTION);

		return link;
	}

	public final static Link buildURIStingId(UriInfo uriInfo, int libroid,
			String rel) {
		URI stingURI = uriInfo.getBaseUriBuilder().path(LlibreResource.class)
				.path(LlibreResource.class, "getLibre").build(libroid);
		Link link = new Link();
		link.setUri(stingURI.toString());
		link.setRel("self");
		link.setType(MediaType.LLIBRES_API_LLIBRE);

		return link;
	}

	public final static Link buildURISting(UriInfo uriInfo, Llibre llibre) {
		URI stingURI = uriInfo.getBaseUriBuilder().path(LlibreResource.class)
				.build();
		Link link = new Link();
		link.setUri(stingURI.toString());
		link.setRel("self");
		link.setTitle("Llibre " + llibre.getLlibreid());
		link.setType(MediaType.LLIBRES_API_LLIBRE);

		return link;
	}

}
