package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/addproducts", "/admin/allproducts", "/admin/viewproduct"})
public class ProductAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAdminServlet() {
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
			case "/admin/addproducts": addProducts(request, response);
			break;

			case "/admin/allproducts": allProducts(request, response);
			break;

			case "/admin/viewproduct": viewProduct(request, response);
			break;
		}
	}

	protected void addProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get request parameter values of the ff.
		[ex. request.getParameter("name")]:
		- name
		- brand
		- description
		- color
		- type
		- collection
		- rating
		- price
		*/

		// create a newProduct object
		// set the necessary attributes
		// store it in the database
		// dispatch to admin-products.jsp
	}

	protected void allProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieve all products via BagService
		// put all bags in an arraylist
		// set the arraylist as a request attribute named "baglist"
	}

	protected void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
