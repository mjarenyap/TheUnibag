package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import beans.User;
import beans.Bag;
import services.UserService;
import services.BagService;
import security.FieldChecker;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet(urlPatterns = {"/login", "/home", "/signup", "/logout"})
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getServletPath();
		switch(path){
			case "/home": home(request, response);
			break;

			case "/login": login(request, response);
			break;

			case "/signup": signup(request, response);
			break;

			default: home(request, response);
			break;
		}
	}

	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare needed variables
		boolean pruposeFlag = false;
		boolean loggedFlag = false;

		String purpose = null;
 
		// get the prupose parameter // check if they are trying to logout
		if(request.getParameter("purpose") != null && request.getParameter("purpose").equals("logout")){
			purpose = request.getParameter("purpose");
			pruposeFlag = true;
		}

		// check for logged user
		if(request.getSession().getAttribute("Account") != null)
			loggedFlag = true;

		// invalidate the session
		if(loggedFlag){
			request.getSession().invalidate();

			// remove the Account cookies
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(int i = 0; i < cookies.length; i++)
				{	
					Cookie currentCookie = cookies[i];
					if(currentCookie.getName().equals("Account"))
					{
						currentCookie.setMaxAge(0);
						response.addCookie(currentCookie);
					}
				}
			}
		}

		// REQUEST ATTRIBUTES HERE

		// dispatch to the homepage
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is a logged user
		if(request.getSession().getAttribute("Account") == null && request.getCookies() == null){
			// get the email, password and redirect parameters
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String redirect = request.getParameter("redirect");

			// declare flag variables
			boolean invalidFlag = true;

			// check for invalid or empty fields
			invalidFlag = FieldChecker.checkLogin(email, password);
			// check if the redirect parameter is part of the whitelisted pages (array of URLs)
			// find a matched account credentials from the database
			// create a User object then set the necessary attributes
			// set a session attribute "Account"
			// create a cookie for the logged user
			// dispatch to the specified redirect page
		}

		else home(request, response);
	}

	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is a logged user
		// get the firstname, lastname, email, and phone parameters
		// check for invalid or empty fields
		// get the redirect parameter
		// check for duplicate account
		// create a User object then set the necessary attributes
		// add the User to the database
		// set a session attribute "Account"
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
