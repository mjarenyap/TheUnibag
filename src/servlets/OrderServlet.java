package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import beans.Bag;
import beans.Order;
import beans.User;
import beans.Address;
import beans.Size;
import services.OrderService;
import services.UserService;
import services.AddressService;
import services.SizeService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = {"/cart", "/checkout", "/completeOrder"})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();

		switch(path){
			case "/cart": shoppingCart(request, response);
			break;

			case "/checkout": checkout(request, response);
			break;

			case "/completeOrder": completeOrder(request, response);
			break;
		}
	}

	protected void shoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// List of ready bags are here request.getSession().getAttribute("cartlist");
		boolean empty = true;

		if(request.getSession().getAttribute("cartlist") != null)
			empty = false;

		request.setAttribute("empty", empty);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// check if user is logged in
		if(request.getSession().getAttribute("user") != null){
			User theUser = (User) request.getSession().getAttribute("user");
			request.setAttribute("firstname", theUser.getFirstName());
			request.setAttribute("lastname", theUser.getLastName());
			request.setAttribute("email", theUser.getEmail());
			request.setAttribute("phone", theUser.getPhone());

			//find the address of the user
			List<Address> addresslist = AddressService.getAllAddress();
			for(int i = 0; i < addresslist.size(); i++)
				if(addresslist.get(i).getUserID() == theUser.getUserID()){
					request.setAttribute("address", addresslist.get(i));
					break;
				}
		}

		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	protected void completeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		// credentials for temp users
		String city = request.getParameter("city");
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		String province = request.getParameter("province");
		String location = request.getParameter("location");
		User newTempUser = null;

		// check for existing user
		boolean loggedUser = false;
		if(request.getSession().getAttribute("user") != null)
			loggedUser = true;

		else{
			List<User> userlist = UserService.getAllUsers();
			newTempUser = new User();
			String firstname = request.getParameter("fname");
			String lastname = request.getParameter("lname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			newTempUser.setUserID(userlist.get(userlist.size() - 1).getUserID() + 1);
			newTempUser.setFirstName(firstname);
			newTempUser.setLastName(lastname);
			newTempUser.setEmail(email);
			newTempUser.setPhone(phone);
			newTempUser.setUserType("temp");
			UserService.addUser(newTempUser);
		}

		// record everything as new order entries
		@SuppressWarnings("unchecked")
		ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("cartlist");
		List<Size> sizelist = SizeService.getAllSizes();
		for(int i = 0; i < cartlist.size(); i++){
			List<Order> orderlist = OrderService.getAllOrders();
			Order newOrder = new Order();
			newOrder.setOrderID(orderlist.get(orderlist.size() - 1).getOrderID() + 1);

			if(loggedUser == true){
				User theUser = (User) request.getSession().getAttribute("user");
				newOrder.setUserID(theUser.getUserID());
			}

			else newOrder.setUserID(newTempUser.getUserID());

			for(int j = 0; j < sizelist.size(); j++)
				if(sizelist.get(j).getBagID() == cartlist.get(i).getBagID()){
					newOrder.setSizeID(sizelist.get(i).getSizeID());
					break;
				}

			newOrder.setCity(city);
			newOrder.setPostcode(postcode);
			newOrder.setProvince(province);
			newOrder.setLocation(location);

			OrderService.addOrder(newOrder);
		}

		request.getSession().setAttribute("cartlist", null);
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
