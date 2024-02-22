package io.sytac.dataharvester.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Util.getLocalDateTimeFromString(jsonParser.getText());
    }
}