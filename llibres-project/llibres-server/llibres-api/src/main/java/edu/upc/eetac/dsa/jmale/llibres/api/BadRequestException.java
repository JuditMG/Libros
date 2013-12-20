package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibresError;


public class BadRequestException extends WebApplicationException {
	public BadRequestException(String message) {
		super(Response
				.status(Response.Status.BAD_REQUEST)
				.entity(new LlibresError(Response.Status.BAD_REQUEST
						.getStatusCode(), message))
				.type(MediaType.LLIBRES_API_ERROR).build());
	}
}
