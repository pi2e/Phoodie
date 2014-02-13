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
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
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
	
	public static void postComment(String photoId, String comment,
			HttpServletRequest request) {
		String secretToken = (String) request.getSession().getAttribute(
				"oauth_token_secret");
		commentFlickr(request.getSession().getAttribute("oauth_token")
				.toString(), photoId, comment, secretToken);

	}

	public static void commentFlickr(String token, String photoId,
			String comment, String secret) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method",
				"flickr.photos.comments.addComment");
		request.addQuerystringParameter("comment_text", comment);
		request.addQuerystringParameter("photo_id", photoId);
		Token accessToken = new Token(token, secret);
		OAuthUtility.service.signRequest(accessToken, request);
		Response resp = request.send();
		resp.getBody();
	}

}


