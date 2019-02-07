package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClassName;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Bean
	public DataSource dataSource() {
	    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(dbDriverClassName);
	    dataSource.setUrl(datasourceUrl);
	    dataSource.setUsername(dbUsername);
	    dataSource.setPassword(dbPassword);
	    return dataSource;
	}

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
    private DataSource dataSources;
	
	@Autowired
	private TokenStore tokenStore;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore);
	}
	
	/*@Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(new AppConfig().tokenStore())
			.authenticationManager(authenticationManager);
    }*/
	
	@Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource());
		/*clients.inMemory() 
	        .withClient("client") 
	        .secret("clientpassword")
	        .authorizedGrantTypes("client_credentials", "password","refresh_token")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
            .resourceIds("oauth2-resource")
            .accessTokenValiditySeconds(5000)
            .refreshTokenValiditySeconds(50000);*/
    }
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	    security.checkTokenAccess("isAuthenticated()");
	}
	/*@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}*/
	
	

}
