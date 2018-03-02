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
			case "/login": login(request, response);
			break;

			case "/home": home(request, response);
			break;

			case "/signup": signup(request, response);
			break;

			case "/logout": logout(request, response);
			break;
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = UserService.getAllUsers();
		boolean found = false;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)){
				request.getSession().setAttribute("user", users.get(i));
				
				Cookie userCookie = new Cookie("Username", users.get(i).getEmail());
				if(request.getParameterValues("remember") != null)
					userCookie.setMaxAge(60*60*24*21);
				
				response.addCookie(userCookie);
				found = true;
				home(request, response);
				break;
			}
		}
		
		if(!found){
			request.setAttribute("invalid", !found);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		home(request, response);
	}

	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get all bags
		List<Bag> baglist = BagService.getAllBags();

		//get new arrival products
		ArrayList<Bag> featuredBagList = new ArrayList<>();
		for(int i = 0; i < 3; i++)
			featuredBagList.add(baglist.get(i));

		request.setAttribute("featuredBagList", featuredBagList);

		//categorize the bags according to brands
		ArrayList<Bag> lesportsacList = new ArrayList<>();
		ArrayList<Bag> kiplingList = new ArrayList<>();
		ArrayList<Bag> cathList = new ArrayList<>();

		int lCount = 0, kCount = 0, cCount = 0;
		for(int i = 0; i < baglist.size(); i++){
			if(baglist.get(i).getBrand().equalsIgnoreCase("LeSportSac") && lCount != 3){
				lesportsacList.add(baglist.get(i));
				lCount++;
			}

			else if(baglist.get(i).getBrand().equalsIgnoreCase("Kipling") && kCount != 3){
				kiplingList.add(baglist.get(i));
				kCount++;
			}

			else if(baglist.get(i).getBrand().equalsIgnoreCase("Cath Kidston") && cCount != 3){
				cathList.add(baglist.get(i));
				cCount++;
			}
		}

		//set attributes
		request.setAttribute("lesportsacList", lesportsacList);
		request.setAttribute("kiplingList", kiplingList);
		request.setAttribute("cathList", cathList);


		//dispatch to JSP
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User();
		List<User> users = UserService.getAllUsers();
		boolean duplicate = false;
		boolean invalid = false;
		
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpass = request.getParameter("firmpassword");
		String phone = request.getParameter("phone");

		//to check if fields are empty and/or invalid
		if(firstname.length() == 0 || lastname.length() == 0 ||
			email.length() == 0 || confirmpass.length() == 0 ||
			password.length() == 0 || !password.equals(confirmpass)){
			invalid = true;
			request.setAttribute("invalid", invalid);
		}

		//to check for duplicate users
		if(!invalid){
			for(int i = 0; i < users.size(); i++){
				if(email.equalsIgnoreCase(users.get(i).getEmail())){
					duplicate = true;
					request.setAttribute("duplicate", duplicate);
				}
			}

			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}

		else if(!duplicate && !invalid){
			newUser.setFirstName(firstname);
			newUser.setLastName(lastname);
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setPhone(phone);
			
			UserService.addUser(newUser);
			request.getSession().setAttribute("user", newUser);
			home(request, response);
		}

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++)
			{	
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("Username"))
				{
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
			}
		}
		
		home(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
