package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import beans.User;
import beans.Bag;
import beans.Order;
import services.UserService;
import services.BagService;
import services.OrderService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/orders", "/users", "/bags", "/deleteBag"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
			case "/orders": viewOrders(request, response);
			break;
			
			case "/users": viewUsers(request, response);
			break;
			
			case "/bags": viewBags(request, response);
			break;
		}
	}
	
	protected void viewOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Order> orderlist = OrderService.getAllOrders();
		request.setAttribute("orderlist", orderlist);
		request.getRequestDispatcher("admin-orders.jsp").forward(request, response);
	}
	
	protected void viewBags(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Bag> baglist = BagService.getAllBags();
		request.setAttribute("baglist", baglist);
		request.getRequestDispatcher("admin-bags.jsp").forward(request, response);
	}
	
	protected void viewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userlist = UserService.getAllUsers();
		request.setAttribute("userlist", userlist);
		request.getRequestDispatcher("admin-users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
