package ectimel.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ectimel.exceptions.JsonMappingException;

public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonMappingException("Error converting " + obj.getClass().getSimpleName() +  " to JSON");
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonMappingException("Error converting JSON to " + clazz.getSimpleName());
        }
    }
}
