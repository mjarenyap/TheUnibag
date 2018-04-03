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
import beans.Size;
import services.BagService;
import services.SizeService;
import security.Encryption;
import security.ProductFilter;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = {"/products", "/featured"})
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

			case "/featured": product(request, response);
		}
	}

	protected void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare filter variables // retrieve from filter controls
		String typeFilter = request.getParameter("typeFilter");
		int sortingMode = 0;
		try{
			sortingMode = Integer.parseInt(request.getParameter("sortingMode"));
		} catch(Exception e){
			sortingMode = 0;
		}

		Encryption e = new Encryption();

		// declare typeFilterFlag
		boolean typeFilterFlag = false;
		String bagType = "All";
		if(typeFilter != null){
			switch(typeFilter){
				case "backpack": bagType = "Backpack";
				typeFilterFlag = true;
				break;

				case "dufflebag": bagType = "Duffle Bag";
				typeFilterFlag = true;
				break;

				case "handbag": bagType = "Handbag";
				typeFilterFlag = true;
				break;

				case "messengerbag": bagType = "Messenger Bag";
				typeFilterFlag = true;
				break;

				case "shoulderbag": bagType = "Shoulder Bag";
				typeFilterFlag = true;
				break;

				case "tote": bagType = "Tote";
				typeFilterFlag = true;
				break;
				
				case "totebag": bagType = "Tote Bag";
				typeFilterFlag = true;
				break;

				default: bagType = "All";
			}
		} else typeFilter = "All";

		// get all products
		List<Bag> baglist = BagService.getAllBags(sortingMode);

		// apply filtration algorithm for the fetched bags
		ArrayList<Bag> filteredBags = new ArrayList<>();
		ArrayList<String> productNames = new ArrayList<>();
		for(int i = 0; i < baglist.size(); i++){
			boolean typeFlag = false;
			if(typeFilterFlag){
				if(baglist.get(i).getType().equalsIgnoreCase(bagType))
					typeFlag = true;
			}

			else if(bagType.equalsIgnoreCase("All"))
				typeFlag = true;

			if(typeFlag){
				filteredBags.add(baglist.get(i));
				productNames.add(e.encryptID(baglist.get(i).getBagID()) + "#" + baglist.get(i).getName().replace(' ', '+'));
			}
		}
		
		request.setAttribute("typeFilter", typeFilter);
		request.setAttribute("bagType", bagType);
		request.setAttribute("baglist", filteredBags);
		request.setAttribute("productnames", productNames);
		request.getRequestDispatcher("products.jsp").forward(request, response);
		
		// preview 10 products according to pagination number
		// set pagination numbers based on the total number of products
			// products.size() / 10 [+1 if products.size() % 10 > 0]
	}

	protected void filteredProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare filter variables // retrieve from filter controls
		String typeFilter = request.getParameter("typeFilter");
		int sortingMode = 0;
		try{
			sortingMode = Integer.parseInt(request.getParameter("sortingMode"));
		} catch(Exception e){
			sortingMode = 0;
		}

		// declare typeFilterFlag
		boolean typeFilterFlag = false;
		String bagType = "All";
		if(typeFilter != null){
			switch(typeFilter){
				case "backpack": bagType = "Backpack";
				typeFilterFlag = true;
				break;

				case "dufflebag": bagType = "Duffle Bag";
				typeFilterFlag = true;
				break;

				case "handbag": bagType = "Handbag";
				typeFilterFlag = true;
				break;

				case "messengerbag": bagType = "Messenger Bag";
				typeFilterFlag = true;
				break;

				case "shoulderbag": bagType = "Shoulder Bag";
				typeFilterFlag = true;
				break;

				case "tote": bagType = "Tote";
				typeFilterFlag = true;
				break;
				
				case "totebag": bagType = "Tote Bag";
				typeFilterFlag = true;
				break;

				default: bagType = "All";
			}
		} else typeFilter = "All";

		ProductFilter pf = new ProductFilter();
		boolean[] priceRanges = new boolean[5];
		for(int i = 0; i < priceRanges.length; i++)
			priceRanges[i] = pf.checkSideFilter(request.getParameterValues("price-range-" + (i+1)));

		boolean[] collections = new boolean[3];
		for(int i = 0; i < collections.length; i++)
			priceRanges[i] = pf.checkSideFilter(request.getParameterValues("collection-" + (i+1)));

		// get all products
		List<Bag> baglist = BagService.getAllBags(sortingMode);

		// apply filtration algorithm for the fetched bags
		ArrayList<Bag> filteredBags = new ArrayList<>();
		for(int i = 0; i < baglist.size(); i++){
			boolean priceFlag = false;
			boolean collectionFlag = false;
			boolean typeFlag = false;

			for(int j = 0; j < priceRanges.length; j++)
				if(pf.checkPriceRange(baglist.get(i), priceRanges[j], j)){
					priceFlag = true;
					break;
				}

			for(int j = 0; j < collections.length; j++)
				if(pf.checkCollection(baglist.get(i), collections[j], j)){
					collectionFlag = true;
					break;
				}
			
			if(typeFilterFlag){
				if(baglist.get(i).getType().equalsIgnoreCase(bagType))
					typeFlag = true;
			}

			else if(bagType.equalsIgnoreCase("All"))
				typeFlag = true;

			if(priceFlag && collectionFlag && typeFlag)
				filteredBags.add(baglist.get(i));
		}
		
		request.setAttribute("typeFilter", typeFilter);
		request.setAttribute("bagType", bagType);
		request.setAttribute("baglist", filteredBags);
		request.getRequestDispatcher("products.jsp").forward(request, response);
	}

	protected void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare flag variables
		boolean validProductPath = true;
		
		Encryption e = new Encryption();
		// get contextualized url parameter of the product
		String productPath = request.getParameter("path");
		String[] splitParts = productPath.split("#");
		long encryptedID = -1;

		try{
			encryptedID = Long.parseLong(splitParts[0]);
		} catch(Exception er){
			validProductPath = false;
		}

		if(splitParts.length != 2)
			validProductPath = false;

		request.setAttribute("error", validProductPath);

		if(validProductPath){
			// declare second layer flag variables
			boolean foundFlag = false;

			// decrype the id and name of the product
			long decryptedID = e.decryptID(encryptedID);
			String productName = splitParts[1].replace('+', ' ');

			// search for a matched result
			Bag selectedBag = BagService.getBag(decryptedID);
			if(selectedBag != null && productName.equalsIgnoreCase(selectedBag.getName()))
				foundFlag = true;

			// proceed to the single product page if found
			if(foundFlag){
				Size selectedSize = null;
				List<Size> sizelist = SizeService.getAllSizes();
				for(int i = 0; i < sizelist.size(); i++)
					if(selectedBag.getBagID() == sizelist.get(i).getBagID()){
						selectedSize = sizelist.get(i);
						break;
					}

				request.setAttribute("featuredSize", selectedSize);
				request.setAttribute("featuredBag", selectedBag);
				request.getRequestDispatcher("single.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-404.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-404.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
