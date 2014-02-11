package com.phoodie.flickr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.phoodie.utility.OAuthUtility;

public class Photo {

	// PHOODIE groupId
	//private static String groupId = "2492917@N24";
	//private static String apiKey = "2eafd8e1dc8f99a4e6063c2c86acc6bb";

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
}
