package ru.bell.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.bell.security.Role;
import ru.bell.security.UserSec;
import ru.bell.security.UserService;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String passwordHash = DatatypeConverter
                .printHexBinary(digest).toLowerCase(Locale.ROOT);

        UserSec userSec = userService.getByName(name);

        List<GrantedAuthority> authorities =  Role.fromValue(userSec.getGroupName())
                .getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.toString()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(name, passwordHash, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}