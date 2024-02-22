package io.sytac.dataharvester.controller;

import io.sytac.dataharvester.dto.AggregateData;
import io.sytac.dataharvester.service.EventConsumeService;
import io.sytac.dataharvester.service.IEventConsumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("Data Harvest Resource")
@RequestMapping("/api/data-harvester")
public class DataHarvesterResource {
    private final IEventConsumeService eventConsumeService;

    DataHarvesterResource(IEventConsumeService service){
        this.eventConsumeService=service;
    }

    @GetMapping
    public Mono<AggregateData> getUserAggregateData(){
        return this.eventConsumeService.consumeEvents();
    }
}
