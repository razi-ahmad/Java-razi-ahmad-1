package io.sytac.dataharvester.service;

import io.sytac.dataharvester.client.IEventStreamClient;
import io.sytac.dataharvester.dto.AggregateData;
import io.sytac.dataharvester.dto.ShowEvent;
import io.sytac.dataharvester.util.StreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class EventConsumeService implements IEventConsumeService {

    private static final int RELEASE_YEAR = 2020;
    public static Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events = new HashMap<>();
    private final IEventStreamClient client;
    private final List<String> platforms;
    private final AtomicInteger counter = new AtomicInteger(0);


    public EventConsumeService(@Value("${sytac.platforms}") List<String> platforms,
                               IEventStreamClient eventStreamClient) {
        this.platforms = platforms;
        this.client = eventStreamClient;
    }

    public Mono<AggregateData> consumeEvents() {
        List<Disposable> disposables = new ArrayList<>();
        Instant startTime = Instant.now();
        platforms.forEach(p -> disposables.add(client.execute(p, startTime, counter, events)));
        waitUntilAllFluxFinished(disposables);
        Instant endTime = Instant.now();
        long totalTime = Duration.between(startTime, endTime).toMillis();

        return Mono.just(new AggregateData(totalTime,
                StreamProcessor.countEventByReleaseYear(events, RELEASE_YEAR),
                StreamProcessor.aggregateEventsByUser((events)))
        );
    }

    private void waitUntilAllFluxFinished(List<Disposable> disposables) {
        while (disposables.stream().filter(Disposable::isDisposed).count() != 3) {
        }

    }
}
