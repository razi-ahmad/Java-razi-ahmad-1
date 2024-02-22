package io.sytac.dataharvester.util;

import io.sytac.dataharvester.dto.*;
import org.springframework.http.codec.ServerSentEvent;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class StreamProcessor {

    public static long countEventByReleaseYear(Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events, Integer releaseYear) {
        return events.values().stream()
                .map(LinkedHashSet::stream)
                .flatMap(
                        sse -> sse.filter(s -> s.data().show().releaseYear() >= releaseYear).map(s -> s.data().show())
                )
                .collect(distinctByKey(Show::showId))
                .count();
    }

    private static <T, R> Collector<T, ?, Stream<T>> distinctByKey(Function<T, R> keyExtractor) {
        return Collectors.collectingAndThen(
                toMap(keyExtractor, t -> t, (t1, t2) -> t1),
                (Map<R, T> map) -> map.values().stream()
        );
    }

    public static List<UserData> aggregateEventsByUser(Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events) {
        return events.entrySet().stream().map(e -> {
            User user = Objects.requireNonNull(e.getValue().stream().findFirst().get().data()).user();
            List<UserShow> userShows = e.getValue().stream().map(content -> {
                ShowEvent showEvent = content.data();
                Show show = showEvent.show();
                return new UserShow(show.showId(), show.title(), show.platform(), UserUtil.getFirstPersonOfCast(show.cast()), Util.getDefaultZonedTime(Util.getShowZonedDateTime(showEvent.eventDate(), user.country())));
            }).collect(Collectors.toList());
            return new UserData(e.getKey(), UserUtil.getFullName(user.firstName(), user.lastName()), Util.getAge(user.dateOfBirth()), userShows);
        }).collect(Collectors.toList());
    }
}
