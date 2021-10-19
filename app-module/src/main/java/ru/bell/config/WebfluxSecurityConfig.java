//package ru.bell.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class WebfluxSecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        return httpSecurity
//                .csrf().disable()
//                .formLogin()
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeExchange()
//                .anyExchange()
//                .permitAll()
//                .and()
//                .build();
//    }
//}
