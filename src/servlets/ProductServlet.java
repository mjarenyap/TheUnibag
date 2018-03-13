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
import beans.Color;
import beans.Size;
import services.BagService;
import services.ColorService;
import services.SizeService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = {"/products", "/products/collections", "/products/types", "/product"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
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
			case "/products": products(request, response);
			break;

			case "/products/collections": collections(request, response);
			break;

			case "/products/brands": brands(request, response);
			break;

			case "/product": product(request, response);
			break;

			default: request.getRequestDispatcher("page-404.jsp").forward(request, response);
			break;
		}
	}

	protected void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check for logged users
		boolean loggedFlag = false;
		
		// check for logged user
				if(request.getSession().getAttribute("Account") != null)
					loggedFlag = true;

				// invalidate the session
				if(!loggedFlag){
					request.getSession().invalidate();
		
		
		// get pagination number parameter
		// get all products
		// preview 10 products according to pagination number
		// set pagination numbers based on the total number of products
			// products.size() / 10 [+1 if products.size() % 10 > 0]
	}

	protected void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loggedFlag = false;
		
		// check for logged user
				if(request.getSession().getAttribute("Account") != null)
					loggedFlag = true;

				// invalidate the session
				if(!loggedFlag){
					request.getSession().invalidate();
		
		// check filter status
				if(request.getSession(),getAttribute("Filter") != null)
		// get contextualized url parametr of the product
		// split the id and the actual name of the product
		// convert the id String to Integer
		// search the product by id in the database
		// validate the match result by product name
		// create a Bag object and set the necessary attributes
		// fetch the the size associated with the product
		// fetch the colors associated with the product
	}

	protected void collections(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loggedFlag = false;
		
		// check for logged user
				if(request.getSession().getAttribute("Account") != null)
					loggedFlag = true;

				// invalidate the session
				if(!loggedFlag){
					request.getSession().invalidate();
		// check filter status
		// get contextualized url parameter of the product
		// 
	}

	protected void brands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void types(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
