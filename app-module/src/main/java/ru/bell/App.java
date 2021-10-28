package ru.bell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = "ru.bell")
@EnableAspectJAutoProxy
@ConfigurationPropertiesScan("ru.bell")
@EnableWebFlux
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

