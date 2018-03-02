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
@WebServlet(urlPatterns = {"/products", "/product", "/deleteProduct", "/addProduct"})
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
			case "/products": selectAllProducts(request, response);
			break;

			case "/product": selectOneProduct(request, response);
			break;
		}
	}

	protected void selectAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get all products and store to list
		List<Bag> baglist = BagService.getAllBags();

		//filtration status from JSP
		

		//set to an attribute
		request.setAttribute("productlist", baglist);
		request.getRequestDispatcher("products.jsp").forward(request, response);
	}

	protected void selectOneProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get all products and store to list
		List<Bag> baglist = BagService.getAllBags();
		List<Color> colorlist = ColorService.getAllColors();
		List<Size> sizelist = SizeService.getAllSizes();

		//find matched product
		long bagID = Long.parseLong(request.getParameter("chosen"));
		Bag selectedBag = null;
		ArrayList<Color> selectedColors = new ArrayList<>();
		ArrayList<Size> selectedSizes = new ArrayList<>();

		for(int i = 0; i < baglist.size(); i++)
			if(bagID == baglist.get(i).getBagID()){
				selectedBag = baglist.get(i);
			}

		//check if seletedBag exists
		if(selectedBag != null){
			//find appropriate colors
			for(int i = 0; i < colorlist.size(); i++)
				if(selectedBag.getBagID() == colorlist.get(i).getBagID())
					selectedColors.add(colorlist.get(i));

			for(int i = 0; i < sizelist.size(); i++)
				if(selectedBag.getBagID() == sizelist.get(i).getBagID())
					selectedSizes.add(sizelist.get(i));

			request.setAttribute("selectedColors", selectedColors);
			request.setAttribute("selectedSizes", selectedSizes);
		}

		//set to an attribute
		request.setAttribute("selectedBag", selectedBag);
		request.getRequestDispatcher("single.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
