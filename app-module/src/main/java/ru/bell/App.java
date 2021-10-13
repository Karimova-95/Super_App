package ru.bell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "ru.bell")
@EnableAspectJAutoProxy
//@EnableConfigurationProperties(PersonInfo.class)
@ConfigurationPropertiesScan("ru.bell")
public class App {

    @Autowired
    private DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate parameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}