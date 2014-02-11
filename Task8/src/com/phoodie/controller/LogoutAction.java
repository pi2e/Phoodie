package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Action {

	public LogoutAction() {
	}

	public String getName() {
		return "logout.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("oauth_token_secret", null);
		session.invalidate();
		return "home.do";
	}
}
