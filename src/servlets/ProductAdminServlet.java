package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bag;
import services.BagService;
import security.Encryption;

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
		
		String name = request.getParameter("name");
		String brand= request.getParameter("brand");
		String description = request.getParameter("description");
		String color = request.getParameter("color");
		String type = request.getParameter("type");
		String collection = request.getParameter("collection");
		String rating = request.getParameter("rating");
		float price = Float.parseFloat(request.getParameter("price"));

		// create a newProduct object
		Bag newBag = new Bag();
		// set the necessary attributes
		// store it in the database
		newBag.setName(name);
		newBag.setBrand(brand);
		newBag.setDescription(description);
		newBag.setColor(color);
		newBag.setType(type);
		newBag.setCollection(collection);
		newBag.setRating(Integer.parseInt(rating));
		newBag.setPrice(price);
		
		// dispatch to admin-products.jsp
		request.getRequestDispatcher("admin-products.jsp").forward(request, response);
	}

	protected void allProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// put all bags in an arraylist
		List<Bag> bags = BagService.getAllBags();
		
		// set the arraylist as a request attribute named "baglist"
		request.setAttribute("baglist", bags);
		
		//TODO do I set the request Dispatcher din here?
		request.getRequestDispatcher("admin-products.jsp").forward(request, response);
	}

	protected void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get the product ID as the request parameter.
		Make sure to parse it as a float.
		*/

		// decrypt the ID using the Encryption class provided.
		// fetch the product via the decrypted ID using the BagService. store it in a Bag object

		/*
		Set the Bag object as an attribute of the request. Name it as "featuredBag"
		EX. request.setAttribute(Obejct, "name");
		*/

		// dispatch to admin-product.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
