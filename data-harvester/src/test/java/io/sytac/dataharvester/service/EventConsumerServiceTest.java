package io.sytac.dataharvester.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.sytac.dataharvester.client.IEventStreamClient;
import io.sytac.dataharvester.config.PlatformConfig;
import io.sytac.dataharvester.dto.AggregateData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.hc.client5.http.classic.methods.HttpHead;
import wiremock.org.apache.hc.core5.http.ContentType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventConsumerServiceTest {

    @Autowired
    private EventConsumeService underTest;

    @Autowired
    @Value("${sytac.platforms}")
    List<String> platforms;

    /*@MockBean
    private IEventStreamClient client;*/

    @Autowired
    private WireMockServer server;

    @BeforeEach
    void beforeEach() {
        platforms.forEach(p -> {
            var stream = loadEventStreamByPlatform(p.replace("/", "") + "-events.stream").trim();
            server.stubFor(get(urlPathEqualTo(p)).willReturn(okForContentType(MediaType.TEXT_EVENT_STREAM_VALUE, stream)));
        });
    }

    private String loadEventStreamByPlatform(String resourceName) {
        ClassLoader classLoader = EventConsumerServiceTest.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void test_consume_events_with_less_than_configured_identifier() throws IOException {
        var result = underTest.consumeEvents().block();
        var expectedResult = expectedResult();
        Assertions.assertEquals(expectedResult.usersData().stream().mapToLong(userData -> userData.events().size()).sum(), result.usersData().stream().mapToLong(userData -> (long) userData.events().size()).sum());
        Assertions.assertEquals(expectedResult.usersData().size(), result.usersData().size());
        Assertions.assertEquals(expectedResult.countOfReleases(), result.countOfReleases());

    }

    private AggregateData expectedResult() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String resourceName = "aggregate-result.json";
        ClassLoader classLoader = EventConsumerServiceTest.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(file, AggregateData.class);
    }
}
