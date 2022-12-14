package com.untralvious.demo.security.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = authentication.getCredentials() + "";

        if (StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("invalid login details");
        }
        // get user details using Spring security user details service
        SysUserDetailsImpl user;
        try {
            user = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }

        if (!encoder.matches(encoder.encode(user.getSalt() + password), user.getPassword())) {
            throw new BadCredentialsException("Password not matched");
        }

        return createSuccessfulAuthentication(authentication, user);
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            authentication.getCredentials(),
            user.getAuthorities()
        );
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
