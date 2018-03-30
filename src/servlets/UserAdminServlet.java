package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.User;
import services.UserService;

/**
 * Servlet implementation class UserAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/adduser", "/admin/allusers", "/admin/viewuser"})
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
			case "/admin/adduser": addUser(request, response);
			break;

			case "/admin/allusers": allUsers(request, response);
			break;

			case "/admin/viewuser": viewUser(request, response);
			break;
		}
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
		request.getRequestDispatcher("admin-users.jsp").forward(request, response);
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
		*/

		// decrypt the ID using the Encryption class provided.
		// fetch the product via the decrypted ID using the UserService. store it in a User object

		/*
		Set the User object as an attribute of the request. Name it as "featuredUser"
		EX. request.setAttribute(Obejct, "name");
		*/

		// dispatch to admin-user.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
