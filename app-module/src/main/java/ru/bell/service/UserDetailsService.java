package ru.bell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.bell.repository.UserSecRepository;
import ru.bell.security.Role;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

    private final UserSecRepository repository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.getUserSecByName(username)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new UsernameNotFoundException("User Not Found"))))
                .map(userSec -> User.withUsername(userSec.getUsername())
                        .password("{MD5}" + userSec.getPassword())
                        .roles(Role.fromValue(userSec.getGroup_name()).name()).build());
    }
}
