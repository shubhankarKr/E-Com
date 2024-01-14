package eCom.backEnd.filter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfCookieFilter extends OncePerRequestFilter {

	private final Logger logger = LogManager.getLogger(CsrfCookieFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Method : {}, URL : {}, RemoteAddress : {}", request.getMethod(), request.getRequestURL(),
				request.getRemoteAddr());
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (csrfToken.getHeaderName() != null) {
			response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
		}
		filterChain.doFilter(request, response);
	}

}
