package com.phoodie.flickr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.phoodie.utility.OAuthUtility;

public class FlickrUtility {

	public static int isMember(String photoId, String comment,
			HttpServletRequest httprequest) {

		OAuthRequest request = new OAuthRequest(Verb.POST,
				"http://api.flickr.com/services/rest");
		request.addQuerystringParameter("method", "flickr.groups.getInfo");
		request.addQuerystringParameter("group_id", OAuthUtility.groupId);

		OAuthUtility.service.signRequest(
				OAuthUtility.getAccessToken(httprequest), request);
		
		int isMember = -1;
		
		try {

			InputStream stream = request.send().getStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(
					stream));
			StringBuilder sb = new StringBuilder();
			String output = "";

			while ((output = input.readLine()) != null) {
				sb.append(output);
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(sb
					.toString().getBytes()));

			// parse xml with Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/rsp/group";
			NodeList list = (NodeList) xPath.compile(expression).evaluate(
					document, XPathConstants.NODESET);
			
			isMember = Integer.parseInt(list.item(0).getAttributes().getNamedItem("is_member").getNodeValue().toString());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isMember;

	}

}
