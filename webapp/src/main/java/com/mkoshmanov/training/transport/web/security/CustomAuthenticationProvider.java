package com.mkoshmanov.training.transport.web.security;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.services.IUserService;
import com.mkoshmanov.training.transport.userdetails.AppUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private IUserService userService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal().toString();
		final String password = authentication.getCredentials().toString();
		final AppUser user = userService.loadUserByUsername(username);
		if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("Username not found.");
		}
		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}
		final Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return true;
	}

}
