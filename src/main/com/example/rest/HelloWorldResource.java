package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.example.HelloService;
import com.sun.jersey.spi.inject.Inject;

@Path("/")
public class HelloWorldResource {

	@Inject("helloService")
	HelloService hello;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response helloWorld(@Context SecurityContext sec) {
		
		if(sec.isUserInRole("customer")) {
			return Response.ok(hello.sayHello()).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}	
	
}
