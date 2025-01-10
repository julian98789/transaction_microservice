package com.transaction_microservice.transaction.security.adapter;

import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticationSecurityPort {
    @Override
    public Long getAuthenticatedUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf( userDetails.getUsername());
    }
}
