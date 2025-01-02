package com.glofox.glofox.testUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String getCorrectClassRequest() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/correctClassRequest.json";
        return Files.readString(Paths.get(path));
    }

    public static String getCorrectClassResponse() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/correctClassResponse.json";
        return Files.readString(Paths.get(path));
    }

    public static String getBadRequestClassResponse() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/badRequestClassResponse.json";
        return Files.readString(Paths.get(path));
    }

    public static String getCorrectBookingRequest() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/correctBookingRequest.json";
        return Files.readString(Paths.get(path));
    }

    public static String getCorrectBookingResponse() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/correctBookingResponse.json";
        return Files.readString(Paths.get(path));
    }

    public static String getWrongBookingRequest() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/wrongBookingRequest.json";
        return Files.readString(Paths.get(path));
    }

    public static String getWrongBookingResponse() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/wrongBookingResponse.json";
        return Files.readString(Paths.get(path));
    }

    public static String getAlreadyBookedResponse() throws IOException {
        //Read the file from documents and return the string
        String path = "src/test/resources/documents/alreadyBookedResponse.json";
        return Files.readString(Paths.get(path));
    }

    /**
     * Converts a JSON string into an object of the specified type.
     *
     * @param data    The JSON string to be deserialized.
     * @param typeRef The type of the target type.
     * @param <T>     The type of the object to be returned.
     * @return The deserialized object of type T.
     */
    public static <T> T getObjectFromString(String data, Class<T> typeRef) {
        try {
            return objectMapper.readValue(data, typeRef);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse JSON", e);
        }
    }
}