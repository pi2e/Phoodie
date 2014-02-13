package com.phoodie.flickr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.phoodie.utility.OAuthUtility;

public class Photo {

	// PHOODIE groupId
	//private static String groupId = "2492917@N24";
	//private static String apiKey = "2eafd8e1dc8f99a4e6063c2c86acc6bb";
	private static final String ENC = "UTF-8";

	public static List<PhotoBean> getGroupPhotos(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException {

		List<PhotoBean> photoList = new ArrayList<PhotoBean>();

		try {
			URL url = new
			URL("http://api.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos&oauth_consumer_key="
			+ OAuthUtility.key + "&group_id=" + OAuthUtility.groupId + "&oauth_token=" + request.getSession().getAttribute("oauth_token"));
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";
			
			while ((output = input.readLine()) != null) {
				sb.append(output);
			}
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb.toString().getBytes()));
			
			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photos/photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {
				
				PhotoBean photo = new PhotoBean();
				photo.setId(list.item(i).getAttributes().getNamedItem("id").getNodeValue());
				photo.setOwner(list.item(i).getAttributes().getNamedItem("owner").getNodeValue());
				photo.setSecret(list.item(i).getAttributes().getNamedItem("secret").getNodeValue());
				photo.setServer(list.item(i).getAttributes().getNamedItem("server").getNodeValue());
				photo.setFarm(list.item(i).getAttributes().getNamedItem("farm").getNodeValue());
				photo.setTitle(list.item(i).getAttributes().getNamedItem("title").getNodeValue());
				photo.setOwnerName(list.item(i).getAttributes().getNamedItem("ownername").getNodeValue());
				
				photoList.add(photo);
			}
			
			con.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return photoList;
	}
	
	public static List<PhotoBean> searchPhotos(HttpServletRequest request, String search, String type) throws InvalidKeyException, NoSuchAlgorithmException {

		List<PhotoBean> photoList = new ArrayList<PhotoBean>();

		try {
			
			String searchParameter = "";
			
			if(type.equalsIgnoreCase("restaurant")) {
				
				searchParameter = "restaurant:name=" + search;
				
			} else if(type.equalsIgnoreCase("dish")) {
				
				searchParameter = "dish:name=" + search;
				
			} else if(type.equalsIgnoreCase("cuisine")) {
				
				searchParameter = "cuisine:id=" + search;
				
			}
			
			
			URL url = new		
			URL("http://api.flickr.com/services/rest/?method=flickr.photos.search&oauth_consumer_key="
			+ OAuthUtility.key + "&group_id=" + OAuthUtility.groupId + "&machine_tags=" + searchParameter + "&oauth_token=" + request.getSession().getAttribute("oauth_token"));
			
			System.out.println(url.toString());
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";
			
			while ((output = input.readLine()) != null) {
				sb.append(output);
			}
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb.toString().getBytes()));
			
			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photos/photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {
				
				PhotoBean photo = new PhotoBean();
				photo.setId(list.item(i).getAttributes().getNamedItem("id").getNodeValue());
				photo.setOwner(list.item(i).getAttributes().getNamedItem("owner").getNodeValue());
				photo.setSecret(list.item(i).getAttributes().getNamedItem("secret").getNodeValue());
				photo.setServer(list.item(i).getAttributes().getNamedItem("server").getNodeValue());
				photo.setFarm(list.item(i).getAttributes().getNamedItem("farm").getNodeValue());
				photo.setTitle(list.item(i).getAttributes().getNamedItem("title").getNodeValue());
				
				photoList.add(photo);
			}
			
			con.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return photoList;
	}
	
	public static void postComment(String photoId, String comment, HttpServletRequest request) {
		URL url;
		try {
			url = accessToken(request.getSession().getAttribute("oauth_token").toString(), photoId, comment);
			
			System.out.println(url.toString());
			
			//url = new
			//		URL("http://api.flickr.com/services/rest/?method=flickr.photos.comments.addComment&oauth_consumer_key="
			//		+ OAuthUtility.key + "&photo_id=" + photoId + "&comment_text=" + comment /* + "&oauth_token=" + request.getSession().getAttribute("oauth_token")*/);
		
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";
			
			while ((output = input.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}
			
			con.disconnect();
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static URL accessToken(String token, String photoId, String comment)
			throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, URISyntaxException, MalformedURLException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		System.out.println("In access token---");
		
		qparams.add(new BasicNameValuePair("comment_text", comment));
		qparams.add(new BasicNameValuePair("oauth_consumer_key", OAuthUtility.key));
		qparams.add(new BasicNameValuePair("oauth_nonce", ""
				+ (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method",
				"HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", ""
				+ (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_token", token));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));
		qparams.add(new BasicNameValuePair("photo_id", photoId));

		// generate the oauth_signature
		String signature = OAuthUtility.getSignature("request", URLEncoder.encode(
				"services/rest", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));
		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		qparams.add(new BasicNameValuePair("method", "flickr.photos.comments.addComment"));
		// generate URI which lead to access_token and token_secret.
		URI uri = URIUtils.createURI("http", "flickr.com", -1,
				"services/rest",
				URLEncodedUtils.format(qparams, ENC), null);
		return uri.toURL();
	}
}
