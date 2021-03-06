package servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bag;
import beans.Size;
import services.BagService;
import services.SizeService;
import security.Encryption;
import security.Expiration;
import security.FieldChecker;

/**
 * Servlet implementation class ProductAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/addproduct", "/admin/allproducts", "/admin/viewproduct", "/admin/addedproduct", "/admin/editedproduct", "/admin/deleteproduct", "/admin/deleteproducts"})
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
			case "/admin/addproduct": addProductPage(request, response);
			break;

			case "/admin/allproducts": allProducts(request, response);
			break;

			case "/admin/viewproduct": viewProduct(request, response);
			break;

			case "/admin/addedproduct": addProduct(request, response);
			break;

			case "/admin/editedproduct": editProduct(request, response);
			break;

			case "/admin/deleteproduct": deletePoduct(request, response);
			break;

			case "/admin/deleteproducts": deleteProducts(request, response);
			break;
		}
	}

	protected void addProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				request.getRequestDispatcher("add-product.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request,response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get request parameter values of the ff.
		[ex. request.getParameter("name")]:
		- name - brand - description - color - type - collection - rating - price
		*/
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());

				boolean errorFlag = true;
				FieldChecker fc = new FieldChecker();

				String name = request.getParameter("name");
				String brand= request.getParameter("brand");
				String description = request.getParameter("description");
				String color = request.getParameter("color");
				String type = request.getParameter("type");
				String collection = request.getParameter("collection");
				String rating = request.getParameter("rating");
				float price = (float)50.00;
				try{
					price = Float.parseFloat(request.getParameter("price"));
				} catch(Exception ex){
					price = (float)50.00;
				}
				String width = request.getParameter("width");
				String height = request.getParameter("height");
				String length = request.getParameter("length");

				Size newSize = new Size();
				newSize.setWidth(width);
				newSize.setHeight(height);
				newSize.setLength(length);

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

				errorFlag = !fc.checkProduct(newBag, newSize);

				if(!errorFlag){
					// for the new bag ID
					List<Bag> baglist = BagService.getAllBags();
					newBag.setBagID(baglist.get(baglist.size() - 1).getBagID() + 1);
					newSize.setSizeID(newBag.getBagID());
					newSize.setBagID(newBag.getBagID());
					BagService.addBag(newBag);
					SizeService.addSize(newSize);
					// dispatch to admin-products.jsp
					allProducts(request, response);
				}

				else{
					request.setAttribute("error", errorFlag);
					request.getRequestDispatcher("add-product.jsp").forward(request, response);
				}
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void allProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// put all bags in an arraylist
				List<Bag> bags = BagService.getAllBags();
				ArrayList<String> productNames = new ArrayList<>();
				Encryption e = new Encryption();

				for(int i = 0; i < bags.size(); i++){
					long encryptedID = e.encryptID(bags.get(i).getBagID());
					String pname = bags.get(i).getName().replace(' ', '+');
					pname = encryptedID + "#" + pname;
					productNames.add(pname);
				}

				// set the arraylist as a request attribute named "baglist"
				request.setAttribute("baglist", bags);
				request.setAttribute("productNames", productNames);
				request.getRequestDispatcher("admin-product.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validProductPath = true;
				
				Encryption e = new Encryption();
				// get contextualized url parametr of the product
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

				request.setAttribute("error", !validProductPath);

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

						selectedBag.setBagID(encryptedID);
						request.setAttribute("featuredSize", selectedSize);
						request.setAttribute("featuredBag", selectedBag);
						request.setAttribute("productPath", productPath);
						request.setAttribute("optionValue", selectedBag.getType());
						request.getRequestDispatcher("view-product.jsp").forward(request, response);
					}

					else request.getRequestDispatcher("page-403.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-403.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validProductPath = true;
				
				Encryption e = new Encryption();
				FieldChecker fc = new FieldChecker();
				// get contextualized url parametr of the product
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

					// proceed to editing the product if found
					if(foundFlag){
						// get the input
						String name = request.getParameter("name");
						String brand = request.getParameter("brand");
						String type = request.getParameter("type");
						String color = request.getParameter("color");
						String description = request.getParameter("description");
						String width = request.getParameter("width");
						String height = request.getParameter("height");
						String length = request.getParameter("length");
						float price = (float)50.00;
						int rating = 0;

						try{
							price = Float.parseFloat(request.getParameter("price"));
						} catch(Exception er){
							price = (float)50.00;
						}

						try{
							rating = Integer.parseInt(request.getParameter("rating"));
						} catch(Exception er){
							rating = 0;
						}

						Size selectedSize = null;
						List<Size> sizelist = SizeService.getAllSizes();
						for(int i = 0; i < sizelist.size(); i++)
							if(selectedBag.getBagID() == sizelist.get(i).getBagID()){
								selectedSize = sizelist.get(i);
								break;
							}

						// set the new values into selectedBag
						selectedBag.setName(name);
						selectedBag.setBrand(brand);
						selectedBag.setType(type);
						selectedBag.setColor(color);
						selectedBag.setDescription(description);
						selectedBag.setPrice(price);
						selectedBag.setRating(rating);

						// set the new values into seletedSize
						selectedSize.setWidth(width);
						selectedSize.setHeight(height);
						selectedSize.setLength(length);

						boolean errorFlag = fc.checkProduct(selectedBag, selectedSize);
						if(!errorFlag){
							// update in the database
							BagService.updateBag(selectedBag.getBagID(), selectedBag);
							SizeService.updateSize(selectedSize.getSizeID(), selectedSize);

							allProducts(request, response);
						}

						else{
							selectedBag = BagService.getBag(decryptedID);
							request.setAttribute("error", true);
							request.setAttribute("featuredBag", selectedBag);
							request.setAttribute("featuredSize", SizeService.getSize(selectedBag.getBagID()));
							request.setAttribute("productPath", productPath);
							request.setAttribute("optionValue", selectedBag.getType());
							request.getRequestDispatcher("view-product.jsp").forward(request, response);
						}
					}

					else request.getRequestDispatcher("page-403.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-403.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void deletePoduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validProductPath = true;
				
				Encryption e = new Encryption();
				// get contextualized url parametr of the product
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

					// proceed to deleting the product if found
					if(foundFlag){
						Size selectedSize = null;
						List<Size> sizelist = SizeService.getAllSizes();
						for(int i = 0; i < sizelist.size(); i++)
							if(selectedBag.getBagID() == sizelist.get(i).getBagID()){
								selectedSize = sizelist.get(i);
								break;
							}

						// delete in the database
						BagService.deleteBag(selectedBag.getBagID());
						SizeService.deleteSize(selectedSize.getSizeID());

						allProducts(request, response);
					}

					else request.getRequestDispatcher("page-403.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-403.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void deleteProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validPaths = true;
				boolean foundFlag = true;

				// declare security variables
				Encryption e = new Encryption();

				// fetch parameter values to archive orders
				String[] toDelete = request.getParameterValues("deletelist");
				ArrayList<Bag> archivelist = new ArrayList<>();
				if(toDelete != null){
					for(int i = 0; i < toDelete.length; i++){
						if(toDelete[i] != null){
							String productPath = toDelete[i];
							String[] splitParts = productPath.split("#");
							long encryptedID = -1;

							try{
								encryptedID = Long.parseLong(splitParts[0]);
							} catch(Exception er){
								validPaths = false;
								foundFlag = false;
							}

							if(splitParts.length == 2 && validPaths && foundFlag){
								// decrypt the id and name of the product
								long decryptedID = e.decryptID(encryptedID);
								String productName = splitParts[1].replace('+', ' ');

								// search for a matched result
								Bag selectedBag = BagService.getBag(decryptedID);
								if(selectedBag != null)
									if(productName.equalsIgnoreCase(selectedBag.getName()))
										archivelist.add(selectedBag);

								else{
									validPaths = false;
									foundFlag = false;
									break;
								}
							}

							else{
								validPaths = false;
								foundFlag = false;
								break;
							}
						}
					}

					// update selected archived orders in the database
					if(validPaths && foundFlag)
						for(int i = 0; i < archivelist.size(); i++){
							BagService.deleteBag(archivelist.get(i).getBagID());
							SizeService.deleteSize(archivelist.get(i).getBagID());
						}
				}

				request.setAttribute("errorPath", !validPaths);
				request.setAttribute("errorFound", !foundFlag);
				allProducts(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Auto-generated method stub
		doGet(request, response);
	}
}
