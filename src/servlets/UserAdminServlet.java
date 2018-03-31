package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.User;
import beans.Address;
import security.Encryption;
import services.UserService;
import services.AddressService;

/**
 * Servlet implementation class UserAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/adduser", "/admin/allusers", "/admin/viewuser", "/admin/addeduser", "/admin/editeduser", "/admin/deleteusers"})
public class UserAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		switch(path){
			case "/admin/adduser": addUserPage(request, response);
			break;

			case "/admin/allusers": allUsers(request, response);
			break;

			case "/admin/viewuser": viewUser(request, response);
			break;

			case "/admin/addeduser": addUser(request, response);
			break;

			case "/admin/editeduser": editUser(request, response);
			break;

			case "/admin/deleteusers": deleteUsers(request, response);
			break;
		}
	}

	protected void addUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getCookies() != null)
			request.getRequestDispatcher("add-user.jsp").forward(request, response);

		else request.getRequestDispatcher("admin-page-403.jsp").forward(request, response);
	}

	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get request parameter values of the ff.
		[ex. request.getParameter("name")]:
		- firstname
		- lastname
		- email
		- password
		- phone
		- usertype
		*/
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String userType = request.getParameter("userType");

		// create a User object and set the necessary attributes
		
		User newUser = new User();
		// store the new user in the database
		
		newUser.setFirstName(firstname);
		newUser.setLastName(lastname);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setPhone(phone);
		newUser.setUserType(userType);
		
		// dispatch to admin-users.jsp
		allUsers(request, response);
	}

	protected void allUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieve all Users via UserService
		UserService us = new UserService();

		//to do put all users in an arraylist
		List<User> users = us.getAllUsers();
		// set the arraylist as request parameter named "userlist"
		request.setAttribute("userlist", users);
		// dispatch to admin-users.jsp
		request.getRequestDispatcher("admin-user.jsp");
	}

	protected void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get the product ID as the request parameter.
		Make sure to parse it as a float.
		Get the user ID as the request parameter.
		Make sure to parse it as a long.
		*/

		String uID = request.getParameter("userID");
		long userID = Long.parseLong(uID);
		/*long userIDecrypt;*/
	
		// decrypt the ID using the Encryption class provided.
		Encryption e = new Encryption();
		userID = e.decryptID(userID);
		/*userIDecrypt = e.decryptID(userID);*/
		
		// fetch the product via the decrypted ID using the UserService. store it in a User object
		User user = UserService.getUser(userID);
		Address address = AddressService.getAddress(user.getUserID());
		/*User user = UserService.getUser(userIDecrypt);*/

		/*
		Set the User object as an attribute of the request. Name it as "featuredUser"
		EX. request.setAttribute(Obejct, "name");
		*/

		request.setAttribute("featuredAddress", address);
		request.setAttribute("featuredUser", user);
		// dispatch to admin-user.jsp
		request.getRequestDispatcher("view-user.jsp").forward(request, response);
	}

	protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* DO SOME BUSINESS LOGIC HERE */
	}

	protected void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* DO SOME BUSINESS LOGIC HERE */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Auto-generated method stub
		doGet(request, response);
	}

}
