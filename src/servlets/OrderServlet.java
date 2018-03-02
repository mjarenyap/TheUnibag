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
		// check for logged user
		// check cart session for items
		// put each item in an arraylist
		// compute for subtotal
		// set computed total as request attribute "subtotal"
		// set arraylist to request attribute
		// dispatch to the shopping cart page
	}

	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check for logged user
		// check purpose if it contains "cart"
		// autofill the fields for logged users
		// compute for subtotal
		// put each item in an arraylist
		// set computed total as request attribute "subtotal"
		// set arraylist to request attribute
		// dispatch to the checkout page
	}

	protected void success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		// check if there items in the cart
		// check purpose if it contains "checkout"
		// create orders for items in the shopping cart
		// invalidate the shopping cart session
		// dispatch to the success page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
