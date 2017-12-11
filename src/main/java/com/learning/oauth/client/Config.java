package com.learning.oauth.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
public class Config {

	@Bean
	public OAuth2RestTemplate getTempplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext ctx) {
		return new OAuth2RestTemplate(resource, ctx);
	}
	
}
