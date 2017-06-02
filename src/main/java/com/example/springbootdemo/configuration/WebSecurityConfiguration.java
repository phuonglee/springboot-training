package com.example.springbootdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//        .csrf().disable()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .httpBasic().and()
            .authorizeRequests()
//            	.antMatchers("/api/**", "/css/**", "/js/**", "/webjars/**", "/partials/**").permitAll()
            	.antMatchers("/", "/api/secure/**", "/partials/login*", "/partials/home*").permitAll()
	            .antMatchers("/api/user/**", "/partials/users/**").hasRole("ADMIN")
//	            .antMatchers("/api/product/**").hasRole("USER")
	            .anyRequest().authenticated()
	            .and()
            .formLogin()
                .loginPage("/")	
//                .successHandler(successHandler)
                .permitAll()
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/", true)
//                .failureUrl("/error")
                .and()
            .logout()
//            	.clearAuthentication(true)
//            	.logoutUrl("/")
//            	.logoutUrl("/logout")
            	.permitAll();
//	            .logoutUrl("/perform_logout");
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/css/**", "/js/**", "/webjars/**");
    }
}
