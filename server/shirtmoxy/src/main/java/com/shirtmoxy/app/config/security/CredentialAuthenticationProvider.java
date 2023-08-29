package com.shirtmoxy.app.config.security;

import com.shirtmoxy.app.entity.Authority;
import com.shirtmoxy.app.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.repository.CustomerRepository;

//@Component
//public class CredentialAuthenticationProvider implements AuthenticationProvider {
//
//	@Autowired
//	private CustomerRepository customerRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String email = authentication.getName();
//		String pwd = authentication.getCredentials().toString();
//		List<Customer> customer = customerRepository.findByEmail(email);
//		if (customer.size() > 0) {
//			if (passwordEncoder.matches(pwd, customer.get(0).getPassword())) {
//				return new UsernamePasswordAuthenticationToken(email, pwd,
//						getGrantedAuthorities(customer.get(0).getAuthorities()));
//			} else {
//				throw new BadCredentialsException("Invalid password!");
//			}
//		} else {
//			throw new BadCredentialsException("No user registered with this details!");
//		}
//	}
//
//	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//		for (Authority authority : authorities) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//		}
//		return grantedAuthorities;
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//	}
//}
