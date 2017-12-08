	package com.learning.oauth.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class HomeController extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private OAuth2ClientContext context;
	
	@Autowired
	private OAuth2RestTemplate template;

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		System.out.println("ACC TOken:" + context.getAccessToken().getValue());
		ResponseEntity<List<Data>> responseEntity = template.exchange("http://localhost:8083/services/reports", HttpMethod.GET, null, new ParameterizedTypeReference<List<Data>>(){});
		model.addAttribute("data", responseEntity.getBody());
		return "test";
	}
	
	public static class Data {
		
		public String name;
		
		public Data() {}

		public Data(String name) {
			super();
			this.name = name;
		}
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "login**").permitAll().anyRequest().authenticated();
	}
}
