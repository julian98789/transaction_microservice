package com.transaction_microservice.transaction.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtDetailsService implements UserDetailsService {

    private final JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {

        String username = jwtService.extractUsername(jwt);
        String role = jwtService.extractRole(jwt);
        Collection<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(role)
        );

        return new User(username, "", authorities);
    }
}