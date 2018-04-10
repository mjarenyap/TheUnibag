package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import beans.Bag;
import beans.Size;
import services.BagService;
import services.SizeService;
import security.Encryption;
import security.Expiration;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = {"/products", "/featured", "/search"})
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
			break;

			case "/search": search(request, response);
			break;
		}
	}

	protected void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
			if(request.getSession().getAttribute("lastLogged") != null)
				request.getSession().setAttribute("lastLogged", LocalDateTime.now());
			// declare filter variables // retrieve from filter controls
			String typeFilter = request.getParameter("typeFilter");
			String[] sortNames = {"Names A-Z", "Names Z-A", "Price Ascending", "Price Descending", "Type of bag", "Brand"};
			int sortingMode = 0;
			try{
				sortingMode = Integer.parseInt(request.getParameter("sortingMode"));
				if(sortingMode < 0 || sortingMode > 5)
					sortingMode = 0;
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
			ArrayList<String> priceFilter = new ArrayList<>();
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

					// for price range filters
					if(filteredBags.get(filteredBags.size() - 1).getPrice() < (float)40.00)
						priceFilter.add("prange1");

					else if(filteredBags.get(filteredBags.size() - 1).getPrice() >= (float)40.00 && filteredBags.get(filteredBags.size() - 1).getPrice() <= (float)99.99)
						priceFilter.add("prange2");

					else if(filteredBags.get(filteredBags.size() - 1).getPrice() >= (float)100.00 && filteredBags.get(filteredBags.size() - 1).getPrice() <= (float)149.99)
						priceFilter.add("prange3");

					else if(filteredBags.get(filteredBags.size() - 1).getPrice() >= (float)150.00 && filteredBags.get(filteredBags.size() - 1).getPrice() <= (float)199.99)
						priceFilter.add("prange4");

					else if(filteredBags.get(filteredBags.size() - 1).getPrice() > (float)199.99)
						priceFilter.add("prange5");
				}
			}
			
			request.setAttribute("typeFilter", typeFilter);
			request.setAttribute("bagType", bagType);
			request.setAttribute("baglist", filteredBags);
			request.setAttribute("productnames", productNames);
			request.setAttribute("pfilter", priceFilter);
			request.setAttribute("sortingMode", sortingMode);
			request.setAttribute("sortName", sortNames[sortingMode]);
			request.getRequestDispatcher("products.jsp").forward(request, response);
			
			// preview 10 products according to pagination number
			// set pagination numbers based on the total number of products
				// products.size() / 10 [+1 if products.size() % 10 > 0]
		}
		
		else request.getRequestDispatcher("page-401.jsp").forward(request, response);
	}

	protected void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
			if(request.getSession().getAttribute("lastLogged") != null)
				request.getSession().setAttribute("lastLogged", LocalDateTime.now());
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
					request.setAttribute("productPath", productPath);
					request.getRequestDispatcher("single.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-404.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-404.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-401.jsp").forward(request, response);
	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
			if(request.getSession().getAttribute("lastLogged") != null)
				request.getSession().setAttribute("lastLogged", LocalDateTime.now());
			// get search keyword
			String keyword = request.getParameter("keyword");
			ArrayList<Bag> searchlist = new ArrayList<>();
			ArrayList<String> productPaths = new ArrayList<>();

			Encryption e = new Encryption();

			// get all bags
			List<Bag> baglist = BagService.getAllBags();
			for(int i = 0; i < baglist.size(); i++){
				if(baglist.get(i).getName().toLowerCase().contains(keyword))
					searchlist.add(baglist.get(i));

				else if(baglist.get(i).getBrand().toLowerCase().contains(keyword))
					searchlist.add(baglist.get(i));

				else if(baglist.get(i).getColor().toLowerCase().contains(keyword))
					searchlist.add(baglist.get(i));

				else if(baglist.get(i).getType().toLowerCase().contains(keyword))
					searchlist.add(baglist.get(i));

				else if(baglist.get(i).getCollection() != null)
					if(baglist.get(i).getCollection().toLowerCase().contains(keyword))
						searchlist.add(baglist.get(i));
			}

			for(int i = 0; i < searchlist.size(); i++){
				String encryptedID = String.valueOf(e.encryptID(searchlist.get(i).getBagID()));
				String pname = searchlist.get(i).getName().replace(' ', '+');
				String path = encryptedID + "#" + pname;
				productPaths.add(path);
			}

			request.setAttribute("keyword", keyword);
			request.setAttribute("baglist", searchlist);
			request.setAttribute("productnames", productPaths);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-401.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
