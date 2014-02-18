package com.phoodie.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.xml.sax.SAXException;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;
import com.phoodie.twitter.Statuse;
import com.phoodie.twitter.TwitterAPI;
import com.phoodie.yelp.Businesses;
import com.phoodie.yelp.YelpSearchService;

public class DiscoverAction extends Action {
	
	private MustTryDAO mustTryDAO;

	public DiscoverAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();
	}

	@Override
	public String getName() {
		
		return "discover.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		if (request.getParameter("search") == null || request.getParameter("search").toString().trim().equals("")) {
			return "discover.jsp";
		}
		
		List<PhotoBean> list;
		
		try {
			
			list = Photo.searchPhotos(request, request.getParameter("search"), request.getParameter("type"));
			
			for(int i=0; i<list.size(); i++) {
				PhotoBean photo = list.get(i);
				if(mustTryDAO.match(MatchArg.and(
					MatchArg.equals("userId", request.getSession().getAttribute("user_nsid")),
					MatchArg.equals("photoId", photo.getId()))) != null && mustTryDAO.match(MatchArg.and(
							MatchArg.equals("userId", request.getSession().getAttribute("user_nsid")),
							MatchArg.equals("photoId", photo.getId()))).length > 0) {
					photo.setMustTry(true);
				} else {
					photo.setMustTry(false);
				}
				// Get the restaurant name and cuisine
					Photo.addRestaurantCuisine(photo.getId(),request,photo);
					
					//Add the rating
					if(!(photo.getYelpId() == null|| photo.getYelpId().equals(""))){
						YelpSearchService yelp = new YelpSearchService();
						
						Businesses bus = yelp.searchFromYelpById(photo.getYelpId()); 
						int rating = (int) Double.parseDouble(bus.getRating());
						photo.setRating(rating);
					}

			}
			
			request.setAttribute("photos", list);
			
			TwitterAPI t = new TwitterAPI(request);
			if(t.islogin()) {
				for(int i=0; i<list.size(); i++) {
					PhotoBean photo = list.get(i);
					List<Statuse> s = t.search(photo.getId());
					for(Statuse p : s) {
						p.setText(p.getText().substring(0, p.getText().length() - 42));
					}
					photo.setStatuses(s);
				}
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "discover.jsp";
	}

}
