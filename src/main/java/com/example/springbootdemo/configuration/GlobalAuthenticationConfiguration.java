package com.example.springbootdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import com.example.springbootdemo.service.SecureService;

@Configuration
@EnableGlobalAuthentication
public class GlobalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	SecureService secureService;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(secureService);
	}
}
