package edu.upc.eetac.dsa.jmale.llibres.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibresError;

public class LlibreNotFoundException extends WebApplicationException {
		private final static String MESSAGE = "Llibre not found";

		public LlibreNotFoundException() {
			super(Response
					.status(Response.Status.NOT_FOUND)
					.entity(new LlibresError(Response.Status.NOT_FOUND
							.getStatusCode(), MESSAGE))
					.type(MediaType.LLIBRES_API_ERROR).build());
		}

	}

	
