package com.example.project.Food_classes;

import java.io.IOException;


import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.fasterxml.jackson.databind.DeserializationFeature;


/**
 * converter class used to transform json to Welcome
 */
public class Converter implements Serializable {
    private static ObjectReader reader;
    private static ObjectWriter writer;

    /**
     * sets up an ObjectMapper with certain configurations, making it ready for deserialization and serialization of JSON data,
     * particularly for instances of the Welcome class.
     * The configuration includes module registration and the ability to ignore unknown properties during deserialization.
     */

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignores the information that doesnt have the fields for

        // You can add more configurations if needed

        reader = mapper.readerFor(Welcome.class);
        writer = mapper.writerFor(Welcome.class);
    }

    /**
     * from json to Welcome
     * @param json
     * @return class Welcome
     * @throws IOException
     */
    public static Welcome fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    /**
     * from Welcome to json
     * @param obj
     * @return json string
     * @throws IOException
     */
    public static String toJsonString(Welcome obj) throws IOException {
        return getObjectWriter().writeValueAsString(obj);
    }

    /**
     *the ObjectReader is responsible for reading (deserializing) JSON data into Java objects.
     * @return  ObjectReader
     */
    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    /**
     * ObjectWriter is responsible for writing (serializing) Java objects into JSON data.
     * @return
     */
    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}
