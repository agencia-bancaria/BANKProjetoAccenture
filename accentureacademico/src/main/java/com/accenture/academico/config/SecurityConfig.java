package com.accenture.academico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.PUT, "/agencias/**").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.DELETE, "/agencias/**").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.GET, "/agencias/**").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.POST, "/agencias/").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.GET, "/swagger-ui/*").authenticated()
                  
                .and()
                    .csrf()
                        .disable();
        http
        .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/clientes/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("user")
                .antMatchers(HttpMethod.GET, "/clientes/**").hasRole("ANALISTA")
                .antMatchers(HttpMethod.POST, "/clientes/").hasRole("USER")
              
            .and()
                .csrf()
                    .disable();
        
        http
        .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/contaDigital/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/contaDigital/**").hasRole("user")
                .antMatchers(HttpMethod.GET, "/contaDigital/**").hasRole("ANALISTA")
                .antMatchers(HttpMethod.POST, "/contaDigital/").hasRole("USER")
              
            .and()
                .csrf()
                    .disable();
        http
        .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/analista/**").hasRole("ANALISTA")
                .antMatchers(HttpMethod.DELETE, "/analista/**").hasRole("ANALISTA")
                .antMatchers(HttpMethod.GET, "/analista/**").hasRole("ANALISTA")
                .antMatchers(HttpMethod.POST, "/analista/").hasRole("ANALISTA")
              
            .and()
                .csrf()
                    .disable();
   
	}
     


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser("Mayara").password(passwordEncoder.encode("accenture")).roles("USER")
		.and()
		.withUser("Julio Cesar").password(passwordEncoder.encode("accenture")).roles("ANALISTA", "USER");

	}
	
}