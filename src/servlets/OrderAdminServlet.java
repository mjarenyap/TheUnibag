package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Order;
import services.OrderService;
import security.Encryption;

/**
 * Servlet implementation class OrderAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/allorders", "/admin", "/admin/archiveorders"})
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
			case "/admin/allorders": allOrders(request, response);
			break;

			case "/admin/archiveorders": archiveOrders(request, response);
			break;

			case "/admin": adminLogin(request, response);
			break;
		}
	}

	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LEAVE THIS BLANK */
	}

	protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve all of the orders through OrderService
		OrderService os = new OrderService();
		Encryption e = new Encryption();

		// Store all the orders in an ArrayList
		List<Order> orderList = os.getAllOrders();
		for(int i = 0; i < orderList.size(); i++){
			long theID = orderList.get(i).getOrderID();
			orderList.get(i).setOrderID(e.encryptID(theID));
		}
		
		// Set the ArrayList as request attribute named "orderlist"
		request.setAttribute("orderlist", orderList);
		// Dispatch to admin-orders.jsp
		request.getRequestDispatcher("admin-index.jsp").forward(request, response);
	}

	protected void viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get the product ID as the request parameter.
		Make sure to parse it as a float.
		Get the order ID as the request parameter.
		Make sure to parse it as a long.
		*/

		
		String orderID = request.getParameter("orderID");
		long orID = Long.parseLong(orderID);
		/*long orIDecrypt;*/
		
		// decrypt the ID using the Encryption class provided.
		Encryption decryptor = new Encryption();
		
		orID = decryptor.decryptID(orID);
		/*orIDecrypt = decryptor.decryptID(orID);*/
		
		// fetch the product via the decrypted ID using the OrderService. store it in a Order object
		Order obj = OrderService.getOrder(orID);
		
		/*Order obj = OrderService.getOrder(orIDecrypt);*/

		/*
		Set the Order object as an attribute of the request. Name it as "featuredOrder"
		EX. request.setAttribute(Obejct, "name");
		EX. request.setAttribute("name", Obejct);
		*/
		request.setAttribute("featuredOrder", obj);

		// dispatch to admin-user.jsp
		
		request.getRequestDispatcher("view-order.jsp").forward(request, response);
	}

	protected void archiveOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/* DO SOME BUSINESS LOGIC HERE */
	}

	protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("adminAccount") != null && request.getCookies() != null)
			allOrders(request, response);

		else request.getRequestDispatcher("admin-login.jsp").forward(request, response);
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
