package ru.bell;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = "ru.bell")
@EnableAspectJAutoProxy
@ConfigurationPropertiesScan("ru.bell")
@EnableWebFlux
public class App {

    @Bean
    public ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
//        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/migration/V1__structure.sql")));
//        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/migration/V2__security.sql")));
//        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/migration/V3__sec_data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

