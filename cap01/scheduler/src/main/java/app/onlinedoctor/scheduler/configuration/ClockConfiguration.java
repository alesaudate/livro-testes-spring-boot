package app.onlinedoctor.scheduler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfiguration {

    @Bean
    public Clock makeClock() {
        return Clock.systemDefaultZone();
    }

}
