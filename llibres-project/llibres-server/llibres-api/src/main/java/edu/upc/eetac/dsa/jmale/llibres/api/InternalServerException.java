package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibresError;


public class InternalServerException extends WebApplicationException {
	public InternalServerException(String message) {
		super(Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new LlibresError(Response.Status.INTERNAL_SERVER_ERROR
						.getStatusCode(), message))
				.type(MediaType.LLIBRES_API_ERROR).build());
	}
}
