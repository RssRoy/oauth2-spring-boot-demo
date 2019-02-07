package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
    private DataSource dataSource;
	
	@Autowired
//    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
	protected void configure(AuthenticationManagerBuilder auth) {
		
		
	    try {
		auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
                "SELECT username, password, enabled " +
                        "FROM users " +
                        "WHERE username = ?"
        ).authoritiesByUsernameQuery(
        				"SELECT x.username, y.roles " +
        				"FROM users x, user_entity_roles y " +
        				"WHERE x.username = ? and y.user_entity_username = x.username "
        		);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
        
       /* auth.inMemoryAuthentication()
        	.withUser("user").password("user").roles("ROLE");*/
    }
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated().and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
	
}
