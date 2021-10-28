package ru.bell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.bell.dao.UserSecDAO;
import ru.bell.security.Role;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

    private final UserSecDAO dao;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return dao.findByUsername(username)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new UsernameNotFoundException("User Not Found"))))
                .map(userSec -> User.withUsername(userSec.getUsername())
                        .password("{MD5}" + userSec.getPassword())
                        .roles(Role.fromValue(userSec.getGroupName()).name()).build());
    }
}
