package eCom.backEnd.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import eCom.backEnd.constant.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(Constants.JWT_HEADER);
		if (jwt != null) {
			SecretKey key= Keys.hmacShaKeyFor(Constants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			Claims claims=Jwts.parserBuilder().
					setSigningKey(key).
					build().
					parseClaimsJws(jwt).
					getBody();
			String userName=String.valueOf(claims.get("userName"));
			String authorities=(String) claims.get("authorities");
			Authentication auth= new UsernamePasswordAuthenticationToken(userName, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return getIgnoredAPIsListValidators(request.getServletPath());
	}
	
	private boolean getIgnoredAPIsListValidators(String path) {
		ArrayList<String> arrayList= new ArrayList<>();
		arrayList.add("/ecom/user/authenticate");
		arrayList.add("/ecom/metadata/getAllCategory");
		arrayList.add("/ecom/products/getAllProducts");
		return arrayList.stream().anyMatch(e->e.contains(path));
	}

}
