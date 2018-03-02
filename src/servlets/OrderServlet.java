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
@WebServlet(urlPatterns = {"/shoppingcart", "/checkout", "/success"})
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

			case "/success": success(request, response);
			break;
		}
	}

	protected void shoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
