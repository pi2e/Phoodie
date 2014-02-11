package com.phoodie.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.utility.OAuthUtility;
/**
 * 
 * @author nveeturi
 *
 */
public class LoginAction extends Action {



	@Override
	public String getName() {

		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String urlString = "";
		try {
			
			URL url = OAuthUtility.requestToken();

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));
			String redirectUrl = "";
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				redirectUrl = output;
			}
			String[] parameters = redirectUrl.split("&");
			
			Map<String, String> urlParam = new HashMap<String, String>();
			for (String string : parameters) {

				String[] paramValpair = string.split("=");
				urlParam.put(paramValpair[0], paramValpair[1]);
			}
			Iterator it = urlParam.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				request.setAttribute(pairs.getKey().toString(),
						pairs.getValue());
			}
			con.disconnect();
			if (urlParam.get("oauth_callback_confirmed").equalsIgnoreCase(
					"true")) {
				urlString = "https://www.flickr.com/services/oauth/authorize?oauth_token="
						+ urlParam.get("oauth_token");
				request.getSession().setAttribute("oauth_token_secret", urlParam.get("oauth_token_secret"));
				request.getSession().setAttribute("oauth_token", urlParam.get("oauth_token"));
				System.out.println("oauth"+request.getSession().getAttribute("oauth_token_secret"));
			}

			System.out.println("redirects from login"+urlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlString;

	}

}
