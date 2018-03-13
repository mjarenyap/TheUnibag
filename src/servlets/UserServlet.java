package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = {"/profile"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet(){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		switch(path){
			case "/profile": profile(request, response);
			break;

			default: request.getRequestDispatcher("page-404.jsp").forward(request, response);
			break;
		}
	}

	protected void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check for logged user
		boolean loggedFlag = false;
		
		// check for logged user
				if(request.getSession().getAttribute("Account") != null)
					loggedFlag = true;

				// invalidate the session
				if(!loggedFlag){
					request.getSession().invalidate();
		
		// get purpose parameter
		String purpose = request.getParameter("purpose");
		// check if purpose parameter is in the whitelist
		
		// dispatch to the profile page
		request.getRequestDispatcher("profile.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
