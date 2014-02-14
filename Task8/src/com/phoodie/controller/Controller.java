package com.phoodie.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;




import javax.servlet.http.HttpSession;





//import com.cfs.databean.Model;
import com.phoodie.controller.Action;
import com.phoodie.databean.Model;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3816380241319936080L;

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		Action.add(new HomePageAction());
		Action.add(new AllPhotosAction(model));
		Action.add(new LoginAction());
		Action.add(new AuthorizeAction());
		Action.add(new TwitterLoginAction());
		Action.add(new TwitterOAuthAction());
		Action.add(new DiscoverAction());
		Action.add(new LogoutAction());
		Action.add(new PostCommentAction());
		Action.add(new AnalyticsAction());
		Action.add(new AnalyticsAction2());
		Action.add(new AnalyticsAction3());
		Action.add(new MustTryAction(model));
		Action.add(new GetUserPhotosAction());
		Action.add(new ViewPhoodListAction(model));
		
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
		HttpSession session = request.getSession();
		Enumeration dataList = session.getAttributeNames();
		while(dataList.hasMoreElements()){
			System.out.println(dataList.nextElement()); 
		}
		
		if (session.getAttribute("oauth_token_secret") == null) {
			if(request.getParameter("login") == null || request.getParameter("login").toString().equals("")) {
				System.out.println("hello"+request.getParameter("login"));
				return "login.jsp";
				
			} else {
				System.out.println("login.do");
				return Action.perform("login.do", request);
				
			}
		}
		
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
