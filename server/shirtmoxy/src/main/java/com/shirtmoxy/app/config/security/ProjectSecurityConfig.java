package com.shirtmoxy.app.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.shirtmoxy.app.config.security.filters.AuthoritiesLoggingAfterFilter;
import com.shirtmoxy.app.config.security.filters.AuthoritiesLoggingAtFilter;
import com.shirtmoxy.app.config.security.filters.CsrfCookieFilter;
import com.shirtmoxy.app.config.security.filters.JWTTokenGeneratorFilter;
import com.shirtmoxy.app.config.security.filters.JWTTokenValidatorFilter;
import com.shirtmoxy.app.config.security.filters.RequestValidationBeforeFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setExposedHeaders(Arrays.asList("Authorization"));
						config.setMaxAge(3600L);
						return config;
					}
				}))
				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/contact", "/sign-in", "/sign-up", "/subscribe", "/checkout/**") // @TODO
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
						.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
						.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
						.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
						.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
						.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
						.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
						.authorizeHttpRequests((requests) -> requests
							.requestMatchers("/user/projects").hasRole("REGISTERED_USER")
							.requestMatchers("/user/profile").hasRole("REGISTERED_USER")
							.requestMatchers("/user/password-reset").hasRole("REGISTERED_USER")
							.requestMatchers("/user/manage-account").hasRole("REGISTERED_USER")
//							.requestMatchers("/user/orders/**").hasRole("REGISTERED_USER")
							.requestMatchers("/user").authenticated()
							.requestMatchers("/home", "/product/**", "/about", "/contact", "/faq", "/shipping-and-returns",
									"/sign-in", "/sign-up", "/sign-out", "/recover-password", "/track", "/cart",
									"/checkout/**", "/subscribe", "/terms-condition", "/privacy-policy", "/coming-soon",
									"/storeform/**", "/user/orders/**")
							// @TODO - some of these links needs to have roles & must not be in permit all
							.permitAll())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}