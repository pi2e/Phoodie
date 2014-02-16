package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Group;

public class JoinGroupAction extends Action {

	@Override
	public String getName() {
		return "joinGroup.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		Group.joinGroup(request);
		if(Group.isMember(request)) {
			return "allPhotos.do";
		}
		return "joinGroup.jsp";
	}

}
