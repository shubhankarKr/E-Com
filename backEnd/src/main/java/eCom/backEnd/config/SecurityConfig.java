package eCom.backEnd.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import eCom.backEnd.constant.Constants;
import eCom.backEnd.exception.UserAuthenticationEntryPoint;
import eCom.backEnd.filter.JWTTokenGenerator;
import eCom.backEnd.filter.JWTTokenValidator;
import eCom.backEnd.filter.interceptor.LogInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

	@Autowired
	UserAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	JWTTokenValidator jwtTokenValidator;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	Cache cache() {
		return new ConcurrentMapCache("myCache");
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor(cache()));
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:5173",
								"https://ecommerce-react-three-wheat.vercel.app"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setExposedHeaders(Arrays.asList(Constants.JWT_HEADER));
						config.setMaxAge(3600L);
						return config;
					}
				})).csrf((csrf) -> csrf.disable())
				.addFilterAfter(new JWTTokenGenerator(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JWTTokenValidator(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers(getAuthenticationIgnoredApis()
								.toArray(new String[getAuthenticationIgnoredApis().size()]))
						.permitAll().requestMatchers("/**").hasAnyRole("USER").requestMatchers("/**").authenticated())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	public ArrayList<String> getAuthenticationIgnoredApis() {
		return this.jwtTokenValidator.getAuthenticationIgnoredApis();
	}

}