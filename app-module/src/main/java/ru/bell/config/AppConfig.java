package ru.bell.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class AppConfig {

    @Scheduled(fixedDelay = 10000)
    public void scheduledFixedDelayTask() {
        log.info("Hello!");
    }
}
