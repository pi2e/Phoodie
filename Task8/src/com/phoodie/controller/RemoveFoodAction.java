package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.databean.MustTry;


public class RemoveFoodAction extends Action {

private MustTryDAO mustTryDAO;	
	
	public RemoveFoodAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();
	}

	@Override
	public String getName() {
		
		return "removeFood.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		String userId = request.getSession().getAttribute("user_nsid").toString();
		
		MustTry mustTry = new MustTry();
		mustTry.setPhotoId(request.getParameter("photoId").toString());
		mustTry.setUserId(request.getSession().getAttribute("user_nsid").toString());
		try {
			MustTry[] list = mustTryDAO.match(MatchArg.equals("userId", mustTry.getUserId()), MatchArg.equals("photoId", mustTry.getPhotoId()));
			mustTryDAO.delete(list[0].getId());		
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "viewPhoodList.do";
	}

}
