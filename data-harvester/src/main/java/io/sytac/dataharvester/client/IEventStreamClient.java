package io.sytac.dataharvester.client;

import io.sytac.dataharvester.dto.ShowEvent;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.Disposable;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface IEventStreamClient {
    Disposable execute(String platform, Instant startTime, AtomicInteger counter, Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events);
}
