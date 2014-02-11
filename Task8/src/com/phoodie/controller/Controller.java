package com.phoodie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;




//import com.cfs.databean.Model;
import com.phoodie.controller.Action;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3816380241319936080L;

	public void init() throws ServletException {
		//Model model = new Model(getServletConfig());
		Action.add(new HomePageAction());
		Action.add(new AllPhotosAction());
		Action.add(new LoginAction());
		Action.add(new AuthorizeAction());
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		//HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);

		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			request.getRequestDispatcher(nextPage).forward(request, response);
			// response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("/jsp/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		else if (nextPage != null) {
			response.sendRedirect(nextPage);
			return;
		}

	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
