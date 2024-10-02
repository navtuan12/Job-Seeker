package com.navtuan12.job_seeker_server.config;

import java.io.IOException;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@Configuration
public class ObjectIdSerializationConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Create a module for custom serializers
        SimpleModule module = new SimpleModule();
        // Register the custom ObjectId serializer
        module.addSerializer(ObjectId.class, new StdSerializer<ObjectId>(ObjectId.class) {
            @Override
            public void serialize(ObjectId objectId, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider)
                    throws IOException {
                // Serialize ObjectId to a hex string
                jsonGenerator.writeString(objectId.toHexString());
            }
        });

        // Create a new ObjectMapper and register the module
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        return mapper;
    }
}
