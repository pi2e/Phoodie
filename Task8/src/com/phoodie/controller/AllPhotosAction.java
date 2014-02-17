package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;
import com.phoodie.twitter.Statuse;
import com.phoodie.twitter.TwitterAPI;


public class AllPhotosAction extends Action {

	private MustTryDAO mustTryDAO;	
	
	public AllPhotosAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();	
	}

	@Override
	public String getName() {

		return "allPhotos.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		String page = "1";
		
		if(request.getParameter("page") == null || request.getParameter("page").length() == 0) {
			page = "1";
		} else {
			page = request.getParameter("page").toString();
		}
		
		int pageNumber = Integer.parseInt(page);
		int nextPage = pageNumber + 1;
		int previousPage = pageNumber;
		
		List<PhotoBean> list;
		try {
			list = Photo.getGroupPhotos(request, page);
			
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

			}
			request.setAttribute("photos", list);
			
			boolean twitterlogin = false;
			
			
			TwitterAPI t = new TwitterAPI(request);
			if(t.islogin()) {
				twitterlogin = true;
				for(int i=0; i<list.size(); i++) {
					PhotoBean photo = list.get(i);
					List<Statuse> s = t.search(photo.getId());
					for(Statuse p : s) {
						p.setText(p.getText().substring(0, p.getText().length() - 42));
					}
					photo.setStatuses(s);
				}
			}
			
			request.setAttribute("page", page);
			
			if(pageNumber > 1) {
				previousPage--;
				request.setAttribute("previousPage", previousPage);
			}
			
			request.setAttribute("nextPage", nextPage);
			
			request.setAttribute("twitterlogin", twitterlogin);
			System.out.println(twitterlogin);
				
			
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "../jsp/home.jsp";
	}

}
