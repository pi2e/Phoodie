package com.phoodie.controller;


import javax.servlet.http.HttpServletRequest;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterLoginAction extends Action {

	@Override
	public String getName() {
		return "twitterLogin.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		OAuthService service;
		Token requestToken;
		
//		if(request.getSession().getAttribute("service") != null) {
//			requestToken = (Token) request.getSession().getAttribute("requestToken");
//			service = (OAuthService) request.getSession().getAttribute("service");
//			return service.getAuthorizationUrl(requestToken);
//		}
//		
		service = new ServiceBuilder()
				.provider(TwitterApi.SSL.class)
				.apiKey("lf8Q0AZ90OP8PS4qViZDog")
				.apiSecret("E3DOWSiiqyqbayGPCebv9WAwQlNQZp2Dxtm6xxE")
				.callback("http://localhost:8080/Task8/twitterOAuth.do")
				.build();

		requestToken = service.getRequestToken();
		
		request.getSession().setAttribute("service", service);
		request.getSession().setAttribute("requestToken", requestToken);

		String authUrl = service.getAuthorizationUrl(requestToken);

		return authUrl;
	}
	

}
