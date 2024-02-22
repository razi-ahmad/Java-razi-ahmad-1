package io.sytac.dataharvester.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.sytac.dataharvester.config.PlatformConfig;
import io.sytac.dataharvester.dto.ShowEvent;
import io.sytac.dataharvester.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventStreamClient implements IEventStreamClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventStreamClient.class);
    private final PlatformConfig platformConfig;
    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventStreamClient(PlatformConfig platformConfig,
                             WebClient webClient) {
        this.platformConfig=platformConfig;
        this.webClient = webClient;
        mapper.registerModule(new JavaTimeModule());
    }


    public Disposable execute(String platform,
                              Instant startTime,
                              AtomicInteger counter,
                              Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events) {
        return webClient.get()
                .uri(platform)
                .headers(headers -> headers.setBasicAuth(platformConfig.getUsername(), platformConfig.getPassword()))
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                })
                .takeUntil(content -> {
                    try {
                        ShowEvent showEvent = mapper.readValue(content.data(), ShowEvent.class);
                        if (platformConfig.getIdentifier().equals(showEvent.user().firstName())) {
                            counter.incrementAndGet();
                        }

                        addEvent(events, content, showEvent);
                    } catch (JsonProcessingException e) {
                        LOGGER.error("Json error parsing issue, Event id: {}", content.id());
                    }
                    return 3 == counter.get() || Util.isXSecondsPassed(startTime, platformConfig.getMaxWaitTime());
                }).subscribe();
    }

    private synchronized void addEvent(Map<Integer, LinkedHashSet<ServerSentEvent<ShowEvent>>> events, ServerSentEvent<String> content, ShowEvent showEvent) {
        Integer userId = showEvent.user().id();
        if (!events.containsKey(userId)) {
            events.put(userId, new LinkedHashSet<>());
        }
        events.get(userId).add(
                ServerSentEvent
                        .builder(showEvent)
                        .id(content.id())
                        .event(content.event())
                        .build()
        );
    }
}
