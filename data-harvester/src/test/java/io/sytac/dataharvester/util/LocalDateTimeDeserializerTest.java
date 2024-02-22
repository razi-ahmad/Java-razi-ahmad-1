package io.sytac.dataharvester.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.sytac.dataharvester.util.UtilTest.ZONED_DATE_TIME_FORMAT;

class LocalDateTimeDeserializerTest {

    @Test
    void deserialize() {
    }

    @Test
    void testDeserialize() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT));
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(module);
        Assertions.assertEquals(dateTime, mapper.readValue(mapper.writeValueAsString(dateTime), LocalDateTime.class).format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT)));
    }
}