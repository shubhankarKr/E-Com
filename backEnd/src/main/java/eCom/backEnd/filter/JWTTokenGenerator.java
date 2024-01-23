package eCom.backEnd.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import eCom.backEnd.constant.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGenerator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			SecretKey key =Keys.hmacShaKeyFor(Constants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			String jwt=Jwts.builder()
					.setIssuer("e_come")
					.setSubject("JWT Token")
					.claim("userName", auth.getName())
					.claim("authorities", getAuthorities(auth.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+6000000))
					.signWith(key).compact();
			response.setHeader(Constants.JWT_HEADER, jwt);
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/ecom/user/authenticate");
	}

	private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> authoritiesSet=new HashSet<>();
		for (GrantedAuthority grantedAuthority : authorities) {
			authoritiesSet.add(grantedAuthority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}
	

}
