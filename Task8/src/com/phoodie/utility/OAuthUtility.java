package com.phoodie.utility;

import java.io.File;
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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * 
 * @author nveeturi
 * 
 */
public class OAuthUtility {
	public static String key = "2eafd8e1dc8f99a4e6063c2c86acc6bb";
	public static String secret = "24b63a1dad901454";
	public static String requestTokenSecret;
	private static final String HMAC_SHA1 = "HmacSHA1";
	private static final String ENC = "UTF-8";
	private static Base64 base64 = new Base64();
	public static String groupId = "2492917@N24";

	public static OAuthService service = new ServiceBuilder()
			.provider(FlickrApi.class).apiKey(OAuthUtility.key)
			.apiSecret(OAuthUtility.secret).build();

	public static URL requestToken() throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			URISyntaxException, MalformedURLException {
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
		String signature = getSignature("GET", "request", URLEncoder.encode(
				"http://www.flickr.com/services/oauth/request_token", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));

		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI("http", "www.flickr.com", -1,
				"/services/oauth/request_token",
				URLEncodedUtils.format(qparams, ENC), null);
		return uri.toURL();
	}

	public static String getSignature(String actionType, String action,
			String url, String params) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeyException {
		/**
		 * base has three parts, they are connected by "&": 1) protocol 2) URL
		 * (need to be URLEncoded) 3) Parameter List (need to be URLEncoded).
		 */
		StringBuilder base = new StringBuilder();

		if (actionType.equals("GET")) {
			base.append("GET&");
		} else {
			base.append("POST&");
		}
		base.append(url);
		base.append("&");
		base.append(params);
		System.out.println(base + "base");
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
		String signature = getSignature("GET", "access", URLEncoder.encode(
				"http://www.flickr.com/services/oauth/access_token", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));
		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI("http", "www.flickr.com", -1,
				"/services/oauth/access_token",
				URLEncodedUtils.format(qparams, ENC), null);
		return uri.toURL();
	}

	public static MultipartEntity uploadPhoto(String description,
			String photoPath, String title, String token) throws Exception {

		FileBody bin = new FileBody(new File(
				"/Users/nveeturi/Desktop/pasta.JPG"), "pasta.JPG");
		MultipartEntity reqEntity = new MultipartEntity();
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		int nounce = (int) (Math.random() * 100000000);
		qparams.add(new BasicNameValuePair("oauth_nonce", "" + nounce));
		qparams.add(new BasicNameValuePair("oauth_signature_method",
				"HMAC-SHA1"));
		Long tmp = (System.currentTimeMillis() / 1000);
		qparams.add(new BasicNameValuePair("oauth_timestamp", "" + tmp));
		System.out.println(token);
		qparams.add(new BasicNameValuePair("oauth_token", token));
		
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		String signature = getSignature("POST", "request",
				URLEncoder.encode("http://up.flickr.com/services/upload", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));
		// qparams.add(new BasicNameValuePair("photo", photoPath));
		// qparams.add(new BasicNameValuePair("oauth_signature", signature));

		// URI uri;

		reqEntity.addPart("oauth_consumer_key", new StringBody(key));
		reqEntity.addPart("oauth_token", new StringBody(token));
		// reqEntity.addPart("oauth_signature_method", new
		// StringBody("HMAC-SHA1"));
		reqEntity.addPart("oauth_token_secret", new StringBody(
				"cec280d758cdd7a4"));
		reqEntity.addPart("oauth_nonce", new StringBody("" + nounce));
		reqEntity.addPart("oauth_version", new StringBody("1.0"));
		reqEntity.addPart("oauth_timestamp", new StringBody("" + tmp));
		// reqEntity.addPart("description", new StringBody(description));
		// reqEntity.addPart("title", new StringBody(title));
		reqEntity.addPart("oauth_signature", new StringBody(signature));
		reqEntity.addPart("photo", bin);
		/*
		 * uri = URIUtils.createURI("http", "up.flickr.com", -1,
		 * "/services/upload", URLEncodedUtils.format(qparams,
		 * OAuthUtility.ENC), null); return uri.toURL();
		 */

		return reqEntity;

	}

	public static String getSignatureForComment(String actionType,
			String action, String url, String params) throws Exception {
		/**
		 * base has three parts, they are connected by "&": 1) protocol 2) URL
		 * (need to be URLEncoded) 3) Parameter List (need to be URLEncoded).
		 */
		StringBuilder base = new StringBuilder();

		if (actionType.equals("GET")) {
			base.append("GET&");
		}
		base.append(url);
		base.append("&");
		base.append(params);
		System.out.println(base);
		byte[] keyBytes = null;

		if (action.equals("request")) {
			keyBytes = (secret + "&").getBytes();
		} else if (action.equals("access")) {
			keyBytes = (secret + "&" + requestTokenSecret).getBytes();
			System.out.println("requestTokenSecret " + requestTokenSecret);
		}

		SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);

		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(key);

		// encode it, base64 it, change it to string and return.
		return new String(base64.encode(mac.doFinal(base.toString().getBytes(
				ENC))), ENC).trim();
	}

	
	public static Token getAccessToken(HttpServletRequest request){
		String secretToken = (String) request.getSession().getAttribute(
				"oauth_token_secret");
		String token = (String)request.getSession().getAttribute("oauth_token");
		Token accessToken = new Token(token, secretToken);
		return accessToken;
	}
}
