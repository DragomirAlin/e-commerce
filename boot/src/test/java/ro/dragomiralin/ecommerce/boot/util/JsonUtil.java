package ro.dragomiralin.ecommerce.boot.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String asJsonString(final Object obj) {
        try {
            return JacksonObjectMapper.OBJECT_MAPPER.getObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    enum JacksonObjectMapper {
        OBJECT_MAPPER;

        private final ObjectMapper objectMapper = new ObjectMapper();

        public ObjectMapper getObjectMapper() {
            return objectMapper;
        }
    }
}
