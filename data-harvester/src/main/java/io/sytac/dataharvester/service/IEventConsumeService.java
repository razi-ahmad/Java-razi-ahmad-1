package io.sytac.dataharvester.service;

import io.sytac.dataharvester.dto.AggregateData;
import reactor.core.publisher.Mono;

public interface IEventConsumeService {
    Mono<AggregateData> consumeEvents();
}
