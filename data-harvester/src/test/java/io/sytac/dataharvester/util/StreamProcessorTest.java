package io.sytac.dataharvester.util;

import io.sytac.dataharvester.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.ServerSentEvent;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

class StreamProcessorTest {

    @Test
    void test_count_event_by_release_year() {
        Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events = buildEvents(LocalDateTime.now());
        Assertions.assertEquals(2, StreamProcessor.countEventByReleaseYear(events, 2020));
    }

    @Test
    void test_aggregate_events_by_user() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events = buildEvents(localDateTime);
        Assertions.assertArrayEquals(getUserData(localDateTime).toArray(), StreamProcessor.aggregateEventsByUser(events).toArray());

    }

    private List<UserData> getUserData(LocalDateTime localDateTime) {
        return List.of(new UserData(1, "foo bar", 53,
                        List.of(new UserShow("123", "test", "sytflix", "test", Util.getDefaultZonedTime(Util.getShowZonedDateTime(localDateTime, "US"))),
                                new UserShow("124", "test", "sytflix", "test", Util.getDefaultZonedTime(Util.getShowZonedDateTime(localDateTime, "US"))),
                                new UserShow("125", "test", "sytflix", "test", Util.getDefaultZonedTime(Util.getShowZonedDateTime(localDateTime, "US")))
                        )
                )
        );
    }

    private Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> buildEvents(LocalDateTime localDateTime) {

        Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events = new HashMap<>();
        LinkedHashSet<ServerSentEvent<ShowEvent>> showEvents = new LinkedHashSet<>();
        showEvents.add(
                ServerSentEvent
                        .builder(new ShowEvent(get2020Show(), localDateTime, getUser()))
                        .build()
        );

        showEvents.add(
                ServerSentEvent
                        .builder(new ShowEvent(get2021Show(), localDateTime, getUser()))
                        .build()
        );

        showEvents.add(
                ServerSentEvent
                        .builder(new ShowEvent(get2019Show(), localDateTime, getUser()))
                        .build()
        );
        events.put(1, showEvents);
        return events;

    }

    private Show get2020Show() {
        return new Show("123", "test", null, null, "test", null, null, "test", null, 2020, "test", "test", "sytflix");
    }

    private Show get2021Show() {
        return new Show("124", "test", null, null, "test", null, null, "test", null, 2021, "test", "test", "sytflix");
    }

    private Show get2019Show() {
        return new Show("125", "test", null, null, "test", null, null, "test", null, 2019, "test", "test", "sytflix");
    }

    private User getUser() {
        return new User(1, "31/10/1970", "test@test.com", "foo", "male", null, "US", "bar");
    }
}