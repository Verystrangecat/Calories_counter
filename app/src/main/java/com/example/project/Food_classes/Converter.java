package com.example.project.Food_classes;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Serializable;

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
