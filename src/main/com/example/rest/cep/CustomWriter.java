package com.example.rest.cep;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.example.rest.cep.Student;

@Provider @Consumes("application/vnd.custom+json")
public class CustomWriter implements MessageBodyWriter<Student> {

	public long getSize(Student arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {		
		return -1;
	}

	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return type == Student.class;
	}

	public void writeTo(Student student, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType mediaType,
			MultivaluedMap<String, Object> arg5, OutputStream output)
			throws IOException, WebApplicationException {
		
		if (mediaType.toString().equalsIgnoreCase("application/vnd.custom+json")) {
			StringBuilder strBuild = new StringBuilder();
			strBuild.append("name").append(">>").append(student.getName()).append("!");
			strBuild.append("age").append(">>").append(student.getAge());
				
			output.write(strBuild.toString().getBytes());
		} else {
			// TODO put in a JSON deserializer here
			String json="{'name':'Sample JSON'}";
			output.write(json.toString().getBytes());
		}
		
		
	}

}
