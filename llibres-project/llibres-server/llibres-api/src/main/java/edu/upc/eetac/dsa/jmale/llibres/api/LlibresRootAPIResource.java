package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//import edu.upc.eetac.dsa.jmale.llibres.api.links.LlibresAPILinkBuilder;


@Path("/")
public class LlibresRootAPIResource {
	@Context
	private UriInfo uriInfo;

	// TODO: Return links
	@GET
	@Produces(MediaType.LLIBRES_API_LINK_COLLECTION)
	public LlibresRootAPI getLinks(){
		LlibresRootAPI root = new LlibresRootAPI();
		//root.addLink(LlibresAPILinkBuilder.buildURIRootAPI(uriInfo));
		//root.addLink(LlibresAPILinkBuilder.buildTemplatedURIStings(uriInfo, "stings"));
		//root.addLink(LlibresAPILinkBuilder.buildTemplatedURIStings(uriInfo, "stings", true));
		//root.addLink(LlibresAPILinkBuilder.buildURIStings(uriInfo, "create"));
		return root;
	}
}