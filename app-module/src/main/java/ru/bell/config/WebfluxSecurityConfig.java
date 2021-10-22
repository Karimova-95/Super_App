package ru.bell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ru.bell.security.Role;

@EnableWebFluxSecurity
@Configuration
public class WebfluxSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange()
//                .pathMatchers("/income").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .anyExchange()
                .authenticated()
                .and()
                .formLogin();
        return http.build();
    }
}
