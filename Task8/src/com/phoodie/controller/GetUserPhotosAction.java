package com.phoodie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;

public class GetUserPhotosAction extends Action {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "userPhotos.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<PhotoBean> list = null;
			try {
				list = Photo.getUserPhotos(request);
				
				
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("photoList", list);
		
		
		return "userPhotos.jsp";
	}

}
