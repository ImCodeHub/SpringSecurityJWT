package com.Authentication.SpringSecurityJWT.Entity;

// Don't forget to import this permission class.
import static com.Authentication.SpringSecurityJWT.Entity.Permission.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
        MEMBER(
                        Set.of(
                                        ADMIN_READ,
                                        ADMIN_CREATE,
                                        MEMBER_READ,
                                        MEMBER_CREATE)),
        ADMIN(
                        Set.of(
                                        MEMBER_READ,
                                        MEMBER_CREATE));

        @Getter
        private final Set<Permission> permissions;

        public List<SimpleGrantedAuthority> getAuthorities() {
                var authorities = getPermissions()
                                .stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                                .collect(Collectors.toList());
                authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
                return authorities;
        }
}
