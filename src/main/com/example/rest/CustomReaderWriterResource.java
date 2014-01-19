package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.rest.cep.Student;

@Path("/customWriter")
public class CustomReaderWriterResource {

	@POST
	@Path("/customWriter")
	@Consumes({"application/vnd.custom+json","application/json"})
	@Produces({"application/vnd.custom+json","application/json"})
	public Response testCustomWriter(Student std) {		
		return Response.ok(std).build();
	}
	
}
