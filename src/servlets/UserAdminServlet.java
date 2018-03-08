package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/adduser", "/admin/allusers", "/admin/viewuser", "/admin"})
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

			case "/admin": adminHome(request, response);
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

		// create a User object and set the necessary attributes
		// store the new user in the database
		// dispatch to admin-users.jsp
	}

	protected void allUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieve all Users via UserService
		// put all users in an arraylist
		// set the arraylist as request parameter named "userlist"
		// dispatch to admin-users.jsp
	}

	protected void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void adminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
