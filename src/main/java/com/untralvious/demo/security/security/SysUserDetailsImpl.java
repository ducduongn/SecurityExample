package com.untralvious.demo.security.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untralvious.demo.security.domain.SysUser;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SysUserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String email;

    private String salt;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public SysUserDetailsImpl(
        String id,
        String username,
        String email,
        String password,
        String salt,
        Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities;
    }

    public static SysUserDetailsImpl build(SysUser user) {
        List<GrantedAuthority> authorities = user
            .getAuthorities()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
            .collect(Collectors.toList());

        return new SysUserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getSalt(), authorities);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserDetailsImpl user = (SysUserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
