package com.z9devs.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.z9devs.entities.Director;
import com.z9devs.entities.Movie;

public class CustomDirectoSerializer extends StdSerializer<Director> {
	private static final long serialVersionUID = 1L;

	public CustomDirectoSerializer() {
        this(null);
    }
  
    public CustomDirectoSerializer(Class<Director> t) {
        super(t);
    }

    @Override
    public void serialize(Director director, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
 
        jgen.writeStartObject();
        jgen.writeNumberField("id", director.getId());
        jgen.writeStringField("name", director.getName());
        
        for(Movie m : director.getMovies())
        	m.setDirector(null);
        jgen.writeObjectField("movies", director.getMovies());
        jgen.writeEndObject();
    }
}
