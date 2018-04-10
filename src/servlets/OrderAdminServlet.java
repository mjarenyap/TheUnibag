package servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Purchase;
import beans.User;
import beans.Bag;
import services.PurchaseService;
import services.UserService;
import services.BagService;
import security.Encryption;
import security.FieldChecker;

/**
 * Servlet implementation class OrderAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/allorders", "/admin", "/admin/archiveorders", "/admin/user"})
public class OrderAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAdminServlet() {
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
			case "/admin/allorders": allOrders(request, response);
			break;

			case "/admin/archiveorders": archiveOrders(request, response);
			break;

			case "/admin": adminLoginPage(request, response);
			break;

			case "/admin/user": adminLogin(request, response);
			break;
		}
	}

	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LEAVE THIS BLANK */
	}

	protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				// Retrieve all of the orders through OrderService
				Encryption e = new Encryption();

				// Store all the orders in an ArrayList
				List<Purchase> orderList = PurchaseService.getAllOrders();
				ArrayList<Purchase> filteredList = new ArrayList<>();
				if(orderList != null){
					for(int i = 0; i < orderList.size(); i++)
						if(orderList.get(i).getStatus() == 0){
							filteredList.add(orderList.get(i));
						}
				}
				
				ArrayList<String> productNames = new ArrayList<>();
				ArrayList<String> orderProducts = new ArrayList<>();
				for(int i = 0; i < filteredList.size(); i++){
					long encryptedID = e.encryptID(filteredList.get(i).getOrderID());
					Bag associatedBag = BagService.getBag(filteredList.get(i).getBagID());
					String pname = associatedBag.getName().replace(' ', '+');
					pname = encryptedID + "#" + pname;
					productNames.add(pname);
					orderProducts.add(associatedBag.getName());
				}
				
				// Set the ArrayList as request attribute named "orderlist"
				request.setAttribute("orderlist", filteredList);
				request.setAttribute("productNames", productNames);
				request.setAttribute("orderProducts", orderProducts);
				// Dispatch to admin-orders.jsp
				request.getRequestDispatcher("admin-index.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	/* NOTE: THIS MIGHT NOT BE USED IN THE APPLICATION */
	protected void viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get the product ID as the request parameter.
		Make sure to parse it as a float.
		Get the order ID as the request parameter.
		Make sure to parse it as a long.
		*/
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			String orderID = request.getParameter("orderID");
			long orID = Long.parseLong(orderID);
			/*long orIDecrypt;*/
			
			// decrypt the ID using the Encryption class provided.
			Encryption decryptor = new Encryption();
			
			orID = decryptor.decryptID(orID);
			/*orIDecrypt = decryptor.decryptID(orID);*/
			
			// fetch the product via the decrypted ID using the OrderService. store it in a Order object
			Purchase obj = PurchaseService.getOrder(orID);
			
			/*Order obj = OrderService.getOrder(orIDecrypt);*/

			/*
			Set the Order object as an attribute of the request. Name it as "featuredOrder"
			EX. request.setAttribute(Obejct, "name");
			EX. request.setAttribute("name", Obejct);
			*/
			request.setAttribute("featuredOrder", obj);

			// dispatch to admin-user.jsp
			
			request.getRequestDispatcher("view-order.jsp").forward(request, response);
		}
	}

	protected void archiveOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				// declare flag variables
				boolean validPaths = true;
				boolean foundFlag = true;

				// declare security variables
				Encryption e = new Encryption();

				// fetch parameter values to archive orders
				String[] toDelete = request.getParameterValues("deletelist");
				ArrayList<Purchase> archivelist = new ArrayList<>();
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
								// decrype the id and name of the product
								long decryptedID = e.decryptID(encryptedID);
								String productName = splitParts[1].replace('+', ' ');

								// search for a matched result
								Purchase selectedOrder = PurchaseService.getOrder(decryptedID);
								Bag associatedBag = null;

								if(selectedOrder != null){
									associatedBag = BagService.getBag(selectedOrder.getBagID());

									// check matching product name
									if(associatedBag != null)
										if(productName.equalsIgnoreCase(associatedBag.getName())){
											selectedOrder.setStatus(1);
											archivelist.add(selectedOrder);
										}

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

							else{
								validPaths = false;
								foundFlag = false;
								break;
							}
						}
					}

					// update selected archived orders in the database
					if(validPaths && foundFlag)
						for(int i = 0; i < archivelist.size(); i++)
							PurchaseService.updateOrder(archivelist.get(i).getOrderID(), archivelist.get(i));
				}

				request.setAttribute("errorPath", !validPaths);
				request.setAttribute("errorFound", !foundFlag);
				allOrders(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void adminLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("adminAccount") == null && request.getSession().getAttribute("Account") == null)
			request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);

		else if(request.getSession().getAttribute("adminAccount") == null && request.getSession().getAttribute("Account") != null)
			request.getRequestDispatcher("page-403.jsp").forward(request, response);

		else allOrders(request, response);
	}

	protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute("adminAccount") == null && request.getSession().getAttribute("Account") == null){
			// fetch input from jsp
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// declare security variables
			FieldChecker fc= new FieldChecker();
			Encryption e = new Encryption();

			// declare flag variables
			boolean validFlag = false;

			// check for empty fields
			if(fc.checkLogin(email, password))
				validFlag = true;

			if(validFlag){
				List<User> userlist = UserService.getAllUsers();
				User correctUser = null;
				for(int i = 0; i < userlist.size(); i++){
					String decryptedPassword = e.decryptPassword(userlist.get(i).getPassword());
					String userType = userlist.get(i).getUserType();
					if(email.equals(userlist.get(i).getEmail()) && decryptedPassword.equals(password) &&
						userType.equalsIgnoreCase("admin")){
						correctUser = userlist.get(i);
						correctUser.setPassword("");
						break;
					}
				}

				// set a session attribute "adminAccount"
				if(correctUser != null){
					request.getSession().setAttribute("adminAccount", correctUser);
					
					// create a cookie for the logged user
					Cookie userCookie = new Cookie("adminUsername", correctUser.getEmail());
					response.addCookie(userCookie);
					allOrders(request, response);
				}

				else{
					validFlag = false;
					request.setAttribute("error", !validFlag);
					request.getRequestDispatcher("admin-login.jsp").forward(request, response);
				}
			}

			else {
				// set validFlag as attribute
				request.setAttribute("error", !validFlag);
				request.getRequestDispatcher("admin-login.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Auto-generated method stub
		doGet(request, response);
	}

}
