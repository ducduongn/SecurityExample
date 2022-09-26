package com.untralvious.demo.security.security;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.repository.SysUserRepository;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    private final Logger log = LoggerFactory.getLogger(SysUserDetailsServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public SysUserDetailsImpl loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        //        if (new EmailValidator().isValid(login, null)) {
        //            return sysUserRepository
        //                .findOneWithAuthoritiesByEmailIgnoreCase(login)
        //                .map(user -> createSpringSecurityUser(login, user))
        //                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        //        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return sysUserRepository
            .findOneWithAuthoritiesByUsername(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private SysUserDetailsImpl createSpringSecurityUser(String lowercaseLogin, SysUser user) {
        //        if (!user.isActivated()) {
        //            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        //        }
        List<GrantedAuthority> grantedAuthorities = user
            .getAuthorities()
            .stream()
            .map(SysRole::getRoleName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        return new SysUserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getSalt(),
            grantedAuthorities
        );
    }
}
