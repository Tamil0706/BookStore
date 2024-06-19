package com.book.store.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDto implements UserDetails {

    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDto(String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can customize this based on your business logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can customize this based on your business logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can customize this based on your business logic
    }

    @Override
    public boolean isEnabled() {
        return true; // You can customize this based on your business logic
    }
}

