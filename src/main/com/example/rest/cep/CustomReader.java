package com.example.rest.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.example.rest.cep.Student;

@Provider @Consumes("application/vnd.custom+json")
public class CustomReader implements MessageBodyReader<Student>{

	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return type == Student.class;
	}

	public Student readFrom(Class<Student> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException, WebApplicationException {
		
		Student st = new Student();
		StringBuffer opBuff= new StringBuffer();
		String line;
		try{
			BufferedReader buff = new BufferedReader(new InputStreamReader(arg5));
			while((line = buff.readLine())!=null) {
				opBuff.append(line);
			}
			
			String inputString = opBuff.toString();
			String[] tokens = inputString.split(",");
			for(String token : tokens) {
				String[] innerToken = token.split(">>");
				if(innerToken[0].equalsIgnoreCase("name")) {
					st.setName(innerToken[1]);
				} else if (innerToken[0].equalsIgnoreCase("age")) {
					st.setAge(innerToken[1]);
				}
			}
				
		} catch (IOException e) {
			
		}
		return st;
	}

}
