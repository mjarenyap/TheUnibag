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
		//Access user service
		UserService us = new UserService();
		List<User> users = us.getAllUsers();
		
		// check if there is a logged user
		if(request.getSession().getAttribute("Account") == null && request.getCookies() == null){
			// get the email, password and redirect parameters
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String redirect = request.getParameter("redirect");

			// declare flag variables
			boolean invalidFlag = true;

			// check for invalid or empty fields --
			/*Todo might have made checkLogin Static -- pls check*/
			invalidFlag = FieldChecker.checkLogin(email, password);
			// check if the redirect parameter is part of the whitelisted pages (array of URLs)
			// find a matched account credentials from the database
			// create a User object then set the necessary attributes
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setPassword(password);
			
			// set a session attribute "Account"
			request.getSession().setAttribute("Account", newUser);
			// TODO create a cookie for the logged user
			
			// dispatch to the specified redirect page
			// TODO which JSP page
			request.getRequestDispatcher("redirect.jsp");
			
			
			
	}

	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Please check this
		
		// check if there is a logged user
		if(request.getSession().getAttribute("Account") != null && request.getCookies() != null){
			
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			// declare flag variables
			boolean invalidFlag = false;
			
			// check for invalid or empty fields
				User u = new User();
				u.setFirstName(fname);
				u.setLastName(lname);
				u.setEmail(email);
				u.setPhone(phone);
				
				invalidFlag = FieldChecker.checkSignup(u);
			

			// get the redirect parameter
				String redirect = request.getParameter("redirect");
			// check for duplicate account
			UserService us = new UserService();
			for(int i = 0; i < us.getAllUsers().size(); i++) {
				if(us.getAllUsers().get(i).getEmail() == u.getEmail()) {
					/* TODO WHATEVER YOU DO HERE*/
				}
			}
			
			// create a User object then set the necessary attributes
			
			// add the User to the database
			us.addUser(u);
			
			// set a session attribute "Account"
			request.getSession().setAttribute("Account", u);
			
			
		}

		else home(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
