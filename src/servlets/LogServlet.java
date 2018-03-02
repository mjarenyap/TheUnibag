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
		// get the prupose parameter

		// check if they are trying to logout
		// check for logged user
		// invalidate the session
		// remove the Accout cookies
		// dispatch to the homepage

		// dispatch to the homepage
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is a logged user
		// get the email, password and redirect parameters
		// check for invalid or empty fields
		// check if the redirect parameter is part of the whitelisted pages (array of URLs)
		// find a matched account credentials from the database
		// create a User object then set the necessary attributes
		// set a session attribute "Account"
		// create a cookie for the logged user
		// dispatch to the specified redirect page
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
