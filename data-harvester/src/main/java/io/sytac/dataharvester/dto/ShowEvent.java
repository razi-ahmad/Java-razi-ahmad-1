package io.sytac.dataharvester.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static io.sytac.dataharvester.util.LocalDateTimeDeserializer.ZONED_DATE_TIME_FORMAT;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ShowEvent(
        @NotNull Show show,
        //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", shape = JsonFormat.Shape.STRING)
        //@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        @JsonFormat(pattern = ZONED_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
        @NotNull LocalDateTime eventDate,
        @NotNull User user) {

    public ShowEvent(@Nonnull Show show, @Nonnull LocalDateTime eventDate, @Nonnull User user) {
        this.show = show;
        this.eventDate = eventDate;
        this.user = user;
    }

    public String toString() {
        return "ShowEvent(show=" + this.show + ", eventDate=" + this.eventDate + ", user=" + this.user + ")";
    }
}

