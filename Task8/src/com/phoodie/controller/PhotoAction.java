package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;
import com.phoodie.twitter.Statuse;
import com.phoodie.twitter.TwitterAPI;


public class PhotoAction extends Action {

	private MustTryDAO mustTryDAO;	
	
	public PhotoAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();	
	}

	@Override
	public String getName() {

		return "photo.do";
	}

	@Override
	public String perform(HttpServletRequest request) {


		try {
			String photoId = request.getParameter("photoId").toString();
			PhotoBean photo = Photo.getPhoto(request, photoId);
			
			request.setAttribute("photo", photo.getURL());
			
			List<PhotoBean> list = new ArrayList<PhotoBean>();
			list.add(photo);
			request.setAttribute("photos", list);
			
			boolean twitterlogin = false;
			
			TwitterAPI t = new TwitterAPI(request);
			if(t.islogin()) {
				twitterlogin = true;
					List<Statuse> s = t.search(photo.getId());
					for(Statuse p : s) {
						p.setText(p.getText().split("http")[0]);
					}
					photo.setStatuses(s);
				
			}
			
			request.setAttribute("twitterlogin", twitterlogin);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "../jsp/home.jsp";
	}

}
