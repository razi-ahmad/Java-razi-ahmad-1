package io.sytac.dataharvester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class PlatformConfig {
    private final String username;
    private final String password;
    private final String identifier;
    private final Long maxWaitTime;

    public PlatformConfig(@Value("${sytac.username}") String username,
                          @Value("${sytac.password}") String password,
                          @Value("${sytac.identifier}") String identifier,
                          @Value("${sytac.wait-time}") Long maxWaitTime) {
        this.username = username;
        this.password = password;
        this.identifier = identifier;
        this.maxWaitTime = maxWaitTime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getMaxWaitTime() {
        return maxWaitTime;
    }
}
