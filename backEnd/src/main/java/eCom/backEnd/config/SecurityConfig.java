package eCom.backEnd.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import eCom.backEnd.filter.interceptor.LogInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	Cache cache() {
		return new ConcurrentMapCache("myCache");
	}
	
	@Bean
	ModelMapper modelMapper(){
	  return new ModelMapper();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor(cache()));
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		/*
		 * CsrfTokenRequestAttributeHandler requestHandler = new
		 * CsrfTokenRequestAttributeHandler();
		 * requestHandler.setCsrfRequestAttributeName("_csrf");
		 * 
		 * http.securityContext((context) -> context.requireExplicitSave(false))
		 * .sessionManagement(session ->
		 * session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		 */
		http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:5173","https://ecommerce-react-three-wheat.vercel.app"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(3600L);
						return config;
					}
				}))
				.csrf((csrf) -> csrf.disable() /*csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/ecom/user/register", "/ecom/user/delete/**")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())*/)
//				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((requests) -> requests
//						.requestMatchers(getAuthenticationIgnoredApis()).permitAll()
						.requestMatchers("/**").permitAll())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}

	public String[] getAuthenticationIgnoredApis() {
		ArrayList<String> ignoreApis = new ArrayList<>();
		ignoreApis.add("/ecom/user/register");
		ignoreApis.add("/ecom/metadata/**");
		ignoreApis.add("/ecom/user/delete/**");
		return ignoreApis.toArray(new String[ignoreApis.size()]);
	}

}