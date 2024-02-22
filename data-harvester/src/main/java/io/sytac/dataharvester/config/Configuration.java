package io.sytac.dataharvester.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${sytac.base-url}")
    private String baseUrl;

    @Bean
    public WebClient getWebClient() {
        return webClient(WebClient.builder().baseUrl(baseUrl));
    }

    private WebClient webClient(final WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(500 * 1024 * 1024))
                        .build())
                .build();
    }

}
