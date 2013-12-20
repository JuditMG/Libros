package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibresError;


public class ResenaNotFoundException extends WebApplicationException {
	private final static String MESSAGE = "Sting not found";

	public ResenaNotFoundException() {
		super(Response
				.status(Response.Status.NOT_FOUND)
				.entity(new LlibresError(Response.Status.NOT_FOUND
						.getStatusCode(), MESSAGE))
				.type(MediaType.LLIBRES_API_ERROR).build());
	}

}
