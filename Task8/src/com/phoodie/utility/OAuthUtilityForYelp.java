package com.phoodie.utility;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class OAuthUtilityForYelp {
	private static String key = "a0HAcGbEJ70TVjfT6r9qZw";
	private static String secret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
	public static String requestTokenSecret;
	private static final String HMAC_SHA1 = "HmacSHA1";
	private static final String ENC = "UTF-8";
	private static Base64 base64 = new Base64();

	public static URL requestToken() throws InvalidKeyException,
	UnsupportedEncodingException, NoSuchAlgorithmException,
	URISyntaxException, MalformedURLException {
		System.out.println("In request token---");
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("oauth_callback",
				"http://localhost:8080/Task8/authorize.do"));
		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		qparams.add(new BasicNameValuePair("oauth_nonce", ""
				+ (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method",
				"HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", ""
				+ (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		// generate the oauth_signature
		String signature = getSignature("request", URLEncoder.encode(
				"http://www.flickr.com/services/oauth/request_token", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));

		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI("http", "www.flickr.com", -1,
				"/services/oauth/request_token",
				URLEncodedUtils.format(qparams, ENC), null);
		return uri.toURL();
		
	}
	private static String getSignature(String action, String url, String params)
			throws UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeyException {
		/**
		 * base has three parts, they are connected by "&": 1) protocol 2) URL
		 * (need to be URLEncoded) 3) Parameter List (need to be URLEncoded).
		 */
		StringBuilder base = new StringBuilder();
		base.append("GET&");
		base.append(url);
		base.append("&");
		base.append(params);

		byte[] keyBytes = null;
		
		if (action.equals("request")) {
			keyBytes = (secret + "&").getBytes(ENC);
		} else if (action.equals("access")) {
			keyBytes = (secret + "&" + requestTokenSecret).getBytes(ENC);
			System.out.println("requestTokenSecret " + requestTokenSecret);
		}

		SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);

		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(key);

		// encode it, base64 it, change it to string and return.
		return new String(base64.encode(mac.doFinal(base.toString().getBytes(
				ENC))), ENC).trim();
	}
	
	public static URL accessToken(String verifier, String token)
			throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, URISyntaxException, MalformedURLException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		System.out.println("In access token---");
		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		qparams.add(new BasicNameValuePair("oauth_nonce", ""
				+ (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method",
				"HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", ""
				+ (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_token", token));
		qparams.add(new BasicNameValuePair("oauth_verifier", verifier));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		// generate the oauth_signature
		String signature = getSignature("access", URLEncoder.encode(
				"http://www.flickr.com/services/oauth/access_token", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));
		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI("http", "www.flickr.com", -1,
				"/services/oauth/access_token",
				URLEncodedUtils.format(qparams, ENC), null);
		return uri.toURL();
	}
}
