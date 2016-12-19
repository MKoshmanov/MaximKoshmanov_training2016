package com.mkoshmanov.training.transport.userdetails;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private Long id;
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
