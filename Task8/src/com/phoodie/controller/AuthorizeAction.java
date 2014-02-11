package com.phoodie.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.utility.OAuthUtility;
/**
 * 
 * @author nveeturi
 *
 */
public class AuthorizeAction extends Action {

	private static String key = "2eafd8e1dc8f99a4e6063c2c86acc6bb";
	private static final String ENC = "UTF-8";
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "authorize.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		System.out.println("here-->");
		String token = request.getParameter("oauth_token");
		String secretToken = (String) request.getSession().getAttribute("oauth_token_secret");
		String verifier = request.getParameter("oauth_verifier");
		System.out.println("secret "+secretToken);
		OAuthUtility.requestTokenSecret = secretToken;
		
		//String accessTokenUrl = "https://www.flickr.com/services/oauth/access_token";
		try {
			URL url = OAuthUtility.accessToken(verifier,token);
			//Test the output - Start
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));
			String redirectUrl = "";
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				redirectUrl = output;
			}////Test the output - End
			return "allPhotos.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "home.jsp";
	}

}
