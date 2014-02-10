package com.phoodie.flickr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Photo {

	// PHOODIE groupId
	private static String groupId = "2492917@N24";
	private static String apiKey = "b6ae41f4edb2898bd748d85b4d5fe130";

	public static List<PhotoBean> getGroupPhotos() {

		List<PhotoBean> photoList = new ArrayList<PhotoBean>();

		try {
			// URL url = new
			// URL("http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="
			// + apiKey + "&group_id=" + groupId);
			// change to this link after authentication is done
			URL url = new URL(
					"http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=54e9dcd29f3b3169ff3c674bda07d531&group_id=2492917%40N24&format=rest&auth_token=72157640790185704-904e283029854dfb&api_sig=6dac44361a204ca6d56da0a9e247e19f");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String output = "";
			
			
			System.out.println("Output from Server .... \n");
			while ((output = input.readLine()) != null) {
				sb.append(output);
				//System.out.println(output);
			}
			
			System.out.println(sb.toString());
			
			String xml = sb.toString();
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
			System.out.println(document.toString());
			
			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "photo";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);

			for (int i = 0; i < list.getLength(); i++) {
				PhotoBean photo = new PhotoBean();
				photo.setId(list.item(i).getAttributes().getNamedItem("id").getNodeValue());
				System.out.println(photo.getId());
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
