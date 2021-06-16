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
                    .antMatchers(HttpMethod.POST, "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.PUT, "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.DELETE, "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config").hasRole("ANALISTA")
                    .antMatchers(HttpMethod.GET, "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config").permitAll()
                .and()
                    .csrf()
                        .disable();
    }
     


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser("Mayara").password(passwordEncoder.encode("accenture")).roles("USER")
		.and()
		.withUser("Julio Cesar").password(passwordEncoder.encode("accenture")).roles("ANALISTA");

	}
	
}
