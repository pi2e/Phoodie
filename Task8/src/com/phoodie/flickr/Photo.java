package com.phoodie.flickr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.phoodie.utility.OAuthUtility;

public class Photo {

	// PHOODIE groupId
	// private static String groupId = "2492917@N24";
	// private static String apiKey = "2eafd8e1dc8f99a4e6063c2c86acc6bb";

	public static PhotoBean getPhoto(HttpServletRequest request, String photoId)
			throws InvalidKeyException, NoSuchAlgorithmException {

		PhotoBean photo = new PhotoBean();

		try {
			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.photos.getInfo&oauth_consumer_key="
							+ OAuthUtility.key
							+ "&photo_id="
							+ photoId
							+ "&oauth_token="
							+ request.getSession().getAttribute("oauth_token"));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			photo.setId(list.item(0).getAttributes().getNamedItem("id")
					.getNodeValue());
			photo.setSecret(list.item(0).getAttributes().getNamedItem("secret")
					.getNodeValue());
			photo.setServer(list.item(0).getAttributes().getNamedItem("server")
					.getNodeValue());
			photo.setFarm(list.item(0).getAttributes().getNamedItem("farm")
					.getNodeValue());

			Boolean fav = false;
			if (list.item(0).getAttributes().getNamedItem("isfavorite")
					.getNodeValue().equals("1")) {
				fav = true;
			}

			photo.setFavorite(fav);

			String expression2 = "/rsp/photo/owner";
			NodeList list2 = (NodeList) xPath.compile(expression2).evaluate(
					document, XPathConstants.NODESET);

			photo.setOwnerName(list2.item(0).getAttributes()
					.getNamedItem("username").getNodeValue());

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

		return photo;

	}

	public static String getPhotoURL(HttpServletRequest request, String photoId)
			throws InvalidKeyException, NoSuchAlgorithmException {

		String photoURL = "";

		try {
			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.photos.getInfo&oauth_consumer_key="
							+ OAuthUtility.key
							+ "&photo_id="
							+ photoId
							+ "&oauth_token="
							+ request.getSession().getAttribute("oauth_token"));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photo/urls/url";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			photoURL = list.item(0).getFirstChild().getNodeValue().toString();
			System.out.println(photoURL);

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

		return photoURL;

	}

	public static List<PhotoBean> getGroupPhotos(HttpServletRequest request,
			String page) throws InvalidKeyException, NoSuchAlgorithmException {

		List<PhotoBean> photoList = new ArrayList<PhotoBean>();

		try {
			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos&oauth_consumer_key="
							+ OAuthUtility.key
							+ "&group_id="
							+ OAuthUtility.groupId
							+ "&per_page=10"
							+ "&page="
							+ page
							+ "&oauth_token="
							+ request.getSession().getAttribute("oauth_token"));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photos/photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {

				PhotoBean photo = new PhotoBean();
				photo.setId(list.item(i).getAttributes().getNamedItem("id")
						.getNodeValue());

				String photoId = list.item(i).getAttributes()
						.getNamedItem("id").getNodeValue();
				photo = getPhoto(request, photoId);
				photoList.add(photo);

				/*
				 * photo.setSecret(list.item(i).getAttributes()
				 * .getNamedItem("secret").getNodeValue());
				 * photo.setServer(list.item(i).getAttributes()
				 * .getNamedItem("server").getNodeValue());
				 * photo.setFarm(list.item
				 * (i).getAttributes().getNamedItem("farm") .getNodeValue());
				 * photo.setTitle(list.item(i).getAttributes()
				 * .getNamedItem("title").getNodeValue());
				 * photo.setOwnerName(list.item(i).getAttributes()
				 * .getNamedItem("ownername").getNodeValue());
				 * 
				 * photo.setComments(getComments(request,
				 * list.item(i).getAttributes().getNamedItem("id")
				 * .getNodeValue()));
				 */
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

	public static List<PhotoBean> searchPhotos(HttpServletRequest request,
			String search, String type) throws InvalidKeyException,
			NoSuchAlgorithmException {

		List<PhotoBean> photoList = new ArrayList<PhotoBean>();

		String[] strings = search.split("\\s");

		try {

			String searchParameter = "";

			if (type.equalsIgnoreCase("restaurant")) {

				for (int i = 0; i < strings.length; i++) {
					searchParameter += "&machine_tags=restaurant:" + strings[i]
							+ "=";
				}

			} else if (type.equalsIgnoreCase("dish")) {

				for (int i = 0; i < strings.length; i++) {
					searchParameter = "&machine_tags=dish:" + strings[i] + "=";
				}

			} else if (type.equalsIgnoreCase("cuisine")) {

				for (int i = 0; i < strings.length; i++) {
					searchParameter = "&machine_tags=cuisine:" + search + "=";
				}
			}

			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.photos.search&oauth_consumer_key="
							+ OAuthUtility.key
							+ "&group_id="
							+ OAuthUtility.groupId
							+ searchParameter
							+ "&oauth_token="
							+ request.getSession().getAttribute("oauth_token"));

			System.out.println(url.toString());

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/photos/photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {

				PhotoBean photo = new PhotoBean();
				String photoId = list.item(i).getAttributes()
						.getNamedItem("id").getNodeValue();
				photo = getPhoto(request, photoId);

				/*
				 * photo.setOwner(list.item(i).getAttributes()
				 * .getNamedItem("owner").getNodeValue());
				 * photo.setSecret(list.item(i).getAttributes()
				 * .getNamedItem("secret").getNodeValue());
				 * photo.setServer(list.item(i).getAttributes()
				 * .getNamedItem("server").getNodeValue());
				 * photo.setFarm(list.item
				 * (i).getAttributes().getNamedItem("farm") .getNodeValue());
				 * photo.setTitle(list.item(i).getAttributes()
				 * .getNamedItem("title").getNodeValue());
				 */

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
			HttpServletRequest httprequest) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method",
				"flickr.photos.comments.addComment");
		request.addQuerystringParameter("comment_text", comment);
		request.addQuerystringParameter("photo_id", photoId);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		request.send();
	}

	public static void addfav(String photoId, HttpServletRequest httprequest) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.favorites.add");
		request.addQuerystringParameter("photo_id", photoId);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);

		request.send();

	}

	public static List<CommentBean> getComments(HttpServletRequest request,
			String photoId) throws InvalidKeyException,
			NoSuchAlgorithmException {

		List<CommentBean> commentList = new ArrayList<CommentBean>();

		try {

			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.photos.comments.getList&oauth_consumer_key="
							+ OAuthUtility.key
							+ "&photo_id="
							+ photoId
							+ "&oauth_token="
							+ request.getSession().getAttribute("oauth_token"));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/comments/comment";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {

				CommentBean comment = new CommentBean();
				comment.setUsername(list.item(i).getAttributes()
						.getNamedItem("authorname").getNodeValue());
				comment.setComment(list.item(i).getFirstChild().getNodeValue());
				commentList.add(comment);
			}

			Collections.reverse(commentList);

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

		return commentList;
	}

	public static List<PhotoBean> getUserPhotos(HttpServletRequest httprequest)
			throws SAXException, IOException, ParserConfigurationException,
			XPathExpressionException {
		List<PhotoBean> photoList = new ArrayList<PhotoBean>();
		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.people.getPhotos");
		System.out.println("userid"
				+ (String) httprequest.getSession().getAttribute("user_nsid"));
		request.addQuerystringParameter("user_id", (String) httprequest
				.getSession().getAttribute("user_nsid"));

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		Response res = request.send();

		BufferedReader input = new BufferedReader(new InputStreamReader(
				res.getStream()));
		StringBuilder sb = new StringBuilder();
		String output = "";

		while ((output = input.readLine()) != null) {
			sb.append(output);
		}

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(sb
				.toString().getBytes()));

		// parse xml with Xpath
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "/rsp/photos/photo";
		NodeList list = (NodeList) xPath.compile(expression).evaluate(document,
				XPathConstants.NODESET);

		for (int i = 0; i < list.getLength(); i++) {

			PhotoBean photo = new PhotoBean();
			photo.setId(list.item(i).getAttributes().getNamedItem("id")
					.getNodeValue());
			photo.setOwner(list.item(i).getAttributes().getNamedItem("owner")
					.getNodeValue());
			photo.setSecret(list.item(i).getAttributes().getNamedItem("secret")
					.getNodeValue());
			photo.setServer(list.item(i).getAttributes().getNamedItem("server")
					.getNodeValue());
			photo.setFarm(list.item(i).getAttributes().getNamedItem("farm")
					.getNodeValue());
			photo.setTitle(list.item(i).getAttributes().getNamedItem("title")
					.getNodeValue());

			photoList.add(photo);
		}

		Collections.reverse(photoList);

		// con.disconnect();
		// System.out.println(res.getBody());
		return photoList;

	}

	public static void postPhotoToGroup(String photoId,
			HttpServletRequest httprequest) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.groups.pools.add");
		request.addQuerystringParameter("photo_id", photoId);
		request.addQuerystringParameter("group_id", OAuthUtility.groupId);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		Response resp = request.send();
		System.out.println(resp.getBody());

	}

	public static void addTagToPhoto(String photoId, String tag,
			HttpServletRequest httprequest) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.photos.addTags");
		request.addQuerystringParameter("photo_id", photoId);
		request.addQuerystringParameter("tags", tag);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		Response resp = request.send();
		System.out.println(resp.getBody());
	}

	public static void addRestaurantCuisine(String photoId,
			HttpServletRequest httprequest, PhotoBean photo)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		// TODO Auto-generated method stub
		String restName = "";
		String cuisineName = "";
		String yelpId = "";
		
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.photos.getInfo");
		request.addQuerystringParameter("photo_id", photoId);
		// request.addQuerystringParameter("tags", tag);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		Response resp = request.send();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				resp.getStream()));
		StringBuilder sb = new StringBuilder();
		String output = "";

		while ((output = input.readLine()) != null) {
			sb.append(output);

		}
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(sb
				.toString().getBytes()));

		// parse xml with Xpath
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "/rsp/photo/tags/tag";
		NodeList list = (NodeList) xPath.compile(expression).evaluate(document,
				XPathConstants.NODESET);
		System.out.println("item" + list.item(0));

		for (int i = 0; i < list.getLength(); i++) {
			String rest = list.item(i).getAttributes().getNamedItem("raw")
					.getNodeValue();

			if (rest.contains("restaurantName")) {
				String[] name = rest.split("=");
				if (name.length == 2) {
					restName = name[1];
					photo.setRestaurantName(restName);
				}
			}

			if (rest.contains("cuisineName")) {
				String[] name = rest.split("=");
				if (name.length == 2) {
					cuisineName = name[1];
					photo.setCuisineName(cuisineName);
				}
			}
			if (rest.contains("yelpId")) {
				String[] name = rest.split("=");
				if (name.length == 2) {
					yelpId = name[1];
					photo.setYelpId(yelpId);
				}
			}
		}
		System.out.println("tag" + restName);
	}

}
