package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.NotFoundException;

@Path("/errors")
public class ErrorResource {

	/**
	 * Use this for 1. Unhandled Exceptions 2. Demo path
	 * params usage
	 * **/
	@GET
	@Path("/unhandled/{throwError}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response throwErrorUnhandled(
			@PathParam("throwError") boolean throwError) {		
		
		if (throwError) {
			throw new NullPointerException();
		}
		return Response.ok("Am in unhandled Exception").build();
	}

	/**
	 * Use this for 1. Handled Exceptions 2. Subpaths in path 3. Demo path
	 * params usage
	 * **/
	@GET
	@Path("/handled/{throwError}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response throwErrorHandled(@PathParam("throwError") boolean throwError) {
		try {
			if (throwError) {
				throw new NullPointerException();
			}
		} catch (RuntimeException e) {
			throw new NotFoundException();
		}
		return Response.ok("Am in Handled Exception").build();
	}
}
