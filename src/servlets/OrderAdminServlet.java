package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/addorder", "/admin/viewallorders", "/admin/vieworder"})
public class OrderAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAdminServlet() {
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
			case "/admin/addorder": addOrder(request, response);
			break;

			case "/admin/allorders": allOrders(request, response);
			break;

			case "/admin/vieworder": viewOrder(request, response);
			break;
		}
	}

	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LEAVE THIS BLANK */
	}

	protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve all of the orders through OrderService
		// Store all the orders in an ArrayList
		// Set the ArrayList as request attribute named "orderlist"
		// Dispatch to admin-orders.jsp
	}

	protected void viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameter of the 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
