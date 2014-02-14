package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.databean.MustTry;
import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;


public class ViewPhoodListAction extends Action {

	private MustTryDAO mustTryDAO;	
	
	public ViewPhoodListAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();	
	}

	@Override
	public String getName() {

		return "viewPhoodList.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<PhotoBean> list = new ArrayList<PhotoBean>();
		try {
			
			MustTry[] mustTryList = mustTryDAO.getUserFavorite(request.getSession().getAttribute("user_nsid").toString());
			
			//list = Photo.getGroupPhotos(request);
			if(mustTryList != null || mustTryList.length > 0) {
			
				for(int i=0; i<mustTryList.length; i++) {
					PhotoBean photo = Photo.getPhoto(request, mustTryList[i].getPhotoId());
					photo.setMustTry(true);
					list.add(photo);
				}
			
			}
			request.setAttribute("photos", list);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "../jsp/home.jsp";
	}

}
