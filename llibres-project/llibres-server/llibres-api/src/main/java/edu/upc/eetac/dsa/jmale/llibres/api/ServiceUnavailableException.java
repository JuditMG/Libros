package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibresError;


public class ServiceUnavailableException extends WebApplicationException {
	public ServiceUnavailableException(String message) {
		super(Response
				.status(Response.Status.SERVICE_UNAVAILABLE)
				.entity(new LlibresError(Response.Status.SERVICE_UNAVAILABLE
						.getStatusCode(), message))
				.type(MediaType.LLIBRES_API_ERROR).build());
	}
}