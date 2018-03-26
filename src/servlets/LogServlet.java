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
import security.PurposeChecker;
import security.DuplicateChecker;
import security.Encryption;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet(urlPatterns = {"/login", "/home", "/signup", "/account"})
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

			case "/login": loginPage(request, response);
			break;

			case "/signup": signupPage(request, response);
			break;

			case "/account": processAccount(request, response);

			/*
			default: home(request, response);
			break;
			*/
		}
	}

	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is no existing cart session
		if(request.getSession().getAttribute("ShoppingCart") == null){
			ArrayList<Bag> shoppingcart = new ArrayList<>();
			request.getSession().setAttribute("ShoppingCart", shoppingcart);
		}

		// declare needed variables
		boolean purposeFlag = false;
		boolean loggedFlag = false;

		String purpose = null;
 
		// get the prupose parameter // check if they are trying to logout
		if(request.getParameter("purpose") != null && request.getParameter("purpose").equals("logout")){
			purpose = request.getParameter("purpose");
			purposeFlag = true;
		}

		// check for logged user
		if(request.getSession().getAttribute("Account") != null)
			loggedFlag = true;

		// invalidate the session
		if(loggedFlag && purpose.equals("logout") && purposeFlag){
			request.getSession().setAttribute("Account", null);

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

		// IMPORTANT: GET ALL PROMOTIONS
		List<Bag> bags = BagService.getAllBags();
		ArrayList<Bag> baglist = new ArrayList<>();
		ArrayList<String> productNames = new ArrayList<>();
		Encryption e = new Encryption();

		// IMPORTANT: SET THEM TO REQUEST ATTRIBUTES
		if(bags.size() > 0)
			for(int i = 0; i < 3; i++){
				long encryptedID = e.encryptID(bags.get(i).getBagID());
				bags.get(i).setBagID(encryptedID);

				String pname = bags.get(i).getName().replace(' ', '+');
				productNames.add(pname);
				baglist.add(bags.get(i));
			}

		request.setAttribute("baglist", baglist);
		request.setAttribute("productnames", productNames);

		// dispatch to the homepage
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void processAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getCookies() != null)
			home(request, response);

		else {
			String purpose = request.getParameter("purpose");
			if(purpose.equals("login"))
				login(request, response);

			else if(purpose.equals("signup"))
				signup(request, response);

			else home(request, response);
		}
	}

	protected void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getCookies() != null)
			home(request, response);

		else request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is a logged user
		if(request.getSession().getAttribute("Account") == null && request.getCookies() == null){
			//security calsses
			FieldChecker fc = new FieldChecker();
			Encryption e = new Encryption();
			
			// get the email, password and redirect parameters
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String redirect = request.getParameter("redirect");

			// declare flag variables
			boolean validCredentialFlag = false;
			boolean validRedirectFlag = false;
			boolean errorFlag = false;

			// check for invalid or empty fields
			validCredentialFlag = fc.checkLogin(email, password);

			// check if the redirect parameter is part of the whitelisted pages (array of URLs)
			PurposeChecker checker = new PurposeChecker();
			if(checker.checkRedirect(redirect))
				validRedirectFlag = true;


			if(validCredentialFlag && validRedirectFlag){
				// find a matched account credentials from the database
				List<User> userlist = UserService.getAllUsers();
				User correctUser = null;

				if(userlist != null){
					for(int i = 0; i < userlist.size(); i++){
						String decryptedPassword = e.decryptPassword(userlist.get(i).getPassword());
						if(email.equals(userlist.get(i).getEmail()) && password.equals(decryptedPassword) &&
							userlist.get(i).getUserType().equals("normal")){
							correctUser = userlist.get(i);
							correctUser.setPassword("");
							break;
						}
					}
				}

				// set a session attribute "Account"
				if(correctUser != null){
					request.getSession().setAttribute("Account", correctUser);
					
					// create a cookie for the logged user
					Cookie userCookie = new Cookie("Username", correctUser.getEmail());
					response.addCookie(userCookie);

					if(!redirect.equals("index"))
						request.getRequestDispatcher(redirect + ".jsp").forward(request, response);

					else home(request, response);
				}

				else{
					errorFlag = true;
					request.setAttribute("error", errorFlag);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}

			else if(validCredentialFlag && !validRedirectFlag)
				home(request, response);

			else if(!validCredentialFlag && validRedirectFlag){
				errorFlag = true;
				request.setAttribute("error", errorFlag);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

			else home(request, response);
		}

		else home(request, response);
	}

	protected void signupPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("Account") != null && request.getCookies() != null)
			home(request, response);

		else request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if there is a logged user
		if(request.getSession().getAttribute("Account") == null && request.getCookies() == null){
			//security variables
			FieldChecker fc = new FieldChecker();
			DuplicateChecker dc = new DuplicateChecker();
			Encryption e = new Encryption();
			
			// get the firstname, lastname, email, and phone parameters
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			String password = request.getParameter("password");
			String confirmPass = request.getParameter("confirmpass");
			// get the redirect parameter
			String redirect = request.getParameter("redirect");

			// declare flags
			boolean validCredentialFlag = false;
			boolean validRedirectFlag = false;
			boolean duplicateFlag = true;
			boolean errorFlag = false;

			if(password.equals(confirmPass)){
				// create a User object then set the necessary attributes
				User newUser = new User();
				newUser.setFirstName(firstname);
				newUser.setLastName(lastname);
				newUser.setEmail(email);
				newUser.setPhone(phone);
				newUser.setUserType("normal");
				newUser.setPassword(password);

				// check for invalid or empty fields
				validCredentialFlag = fc.checkSignup(newUser);

				// check if the redirect parameter is part of the whitelisted pages (array of URLs)
				PurposeChecker checker = new PurposeChecker();
				if(checker.checkRedirect(redirect))
					validRedirectFlag = true;

				// check for duplicate account
				List<User> userlist = UserService.getAllUsers();
				duplicateFlag = dc.checkUser(newUser, userlist);

				if(validCredentialFlag && validRedirectFlag && !duplicateFlag){
					// add the User to the database
					long newUserID = (long)userlist.size() + 1;
					newUser.setUserID(newUserID);

					String encryptedPass = e.encryptPassword(password);
					newUser.setPassword(encryptedPass);

					UserService.addUser(newUser);

					// set a session attribute "Account"
					request.getSession().setAttribute("Account", newUser);
						
					// create a cookie for the logged user
					Cookie userCookie = new Cookie("Username", newUser.getEmail());
					response.addCookie(userCookie);

					if(redirect.equals("cart") || redirect.equals("checkout"))
						request.getRequestDispatcher(redirect + ".jsp").forward(request, response);

					else home(request, response);
				}

				else if((!validCredentialFlag && validRedirectFlag) || (duplicateFlag && validRedirectFlag)){
					errorFlag = true;
					request.setAttribute("error", errorFlag);
					request.setAttribute("duplicate", duplicateFlag);
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}

				else home(request, response);
			}

			else{
				errorFlag = true;
				duplicateFlag = false;
				request.setAttribute("error", errorFlag);
				request.setAttribute("duplicate", duplicateFlag);
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
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
