package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.databean.MustTry;


public class MustTryAction extends Action {

private MustTryDAO mustTryDAO;	
	
	public MustTryAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();
	}

	@Override
	public String getName() {
		
		return "mustTry.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		String userId = request.getSession().getAttribute("user_nsid").toString();
		
		MustTry mustTry = new MustTry();
		mustTry.setPhotoId(request.getParameter("photoId").toString());
		mustTry.setUserId(request.getSession().getAttribute("user_nsid").toString());
		try {
			mustTryDAO.create(mustTry);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "ajax";
	}

}
