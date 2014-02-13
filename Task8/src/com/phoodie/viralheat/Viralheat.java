package com.phoodie.viralheat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.phoodie.yelp.YelpBean;

public class Viralheat {
	
	private final String USER_AGENT = "Mozilla/5.0";

	private SentiBean sendGet(String key) throws Exception {
		key = key.replaceAll(" ", "%20");
		String url = "https://viralheat.com/api/sentiment/review.json?text=" + key +"&api_key=tgNwAde42TrbkpMKqzwk";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
	//	con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Gson gson = new Gson(); 
	    SentiBean sentiBean = gson.fromJson(response.toString(), SentiBean.class);
		//print result
		System.out.println(response.toString());
		
		
		return sentiBean;
 
	}
	
	
	public final static void main(String[] args) throws Exception {

		Viralheat http = new Viralheat();
		 
		System.out.println("Testing 1 - Send Http GET request");
		SentiBean sentiBean = http.sendGet("I love U");
		System.out.println(sentiBean.getMood());
		System.out.println(sentiBean.getProb());
		System.out.println(sentiBean.getText());
//		HttpClient httpclient = new DefaultHttpClient();
//
//		HttpGet httpget = new HttpGet("https://viralheat.com/api/sentiment/review.json?text=%22I%20hate%20you%22&api_key=tgNwAde42TrbkpMKqzwk");
//
//		System.out.println("executing request " + httpget.getURI());

		// Create a response handler
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		// Body contains your json stirng
//		httpclient.execute(httpget);
//		System.out.println("----------------------------------------");
//		System.out.println(responseBody);
//		System.out.println("----------------------------------------");
//		httpclient.getConnectionManager().shutdown();
	}
}
