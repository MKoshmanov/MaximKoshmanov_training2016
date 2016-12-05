package com.mkoshmanov.training.transport.web.servlets;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mkoshmanov.training.transport.services.IAuthenticationService;
import com.mkoshmanov.training.transport.services.components.UserDataStorage;

public class BasicAuthFilter implements Filter {
	private IAuthenticationService authService;

	private UserDataStorage userDataStorage;
	private ApplicationContext appContext;

	@Override
	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		authService = context.getBean(IAuthenticationService.class);
		appContext = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		userDataStorage = appContext.getBean(UserDataStorage.class);

		String[] credentials = resolveCredentials(req);

		boolean isCredentialsResolved = credentials != null && credentials.length == 2;
		if (!isCredentialsResolved) {
			res.sendError(401);
			return;
		}
		String username = credentials[0];
		String password = credentials[1];
		if (authService.validateUserPassword(username, password)) {
			userDataStorage.setLoggedIn(true);
			chain.doFilter(request, response);
		} else {
			res.sendError(401);
		}
	}

	private String[] resolveCredentials(HttpServletRequest req) {
		try {
			Enumeration<String> headers = req.getHeaders("Authorization");
			String nextElement = headers.nextElement();
			String base64Credentials = nextElement.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			return credentials.split(":", 2);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void destroy() {
	}

}