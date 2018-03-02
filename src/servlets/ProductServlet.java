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
@WebServlet(urlPatterns = {"/products", "/collection", "/brand"})
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

			case "/collection": collection(request, response);
			break;

			case "/brand": brand(request, response);
			break;

			default: product(request, response);
			break;
		}
	}

	protected void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check for logged users
		// get pagination number parameter
		// get all products
		// preview 10 products according to pagination number
		// set pagination numbers based on the total number of products
			// products.size() / 10 [+1 if products.size() % 10 > 0]
	}

	protected void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check for logged users
	}

	protected void collection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void brand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
