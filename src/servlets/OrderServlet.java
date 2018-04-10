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
import beans.Purchase;
import beans.User;
import beans.Address;
import services.PurchaseService;
import services.UserService;
import services.BagService;
import services.AddressService;
import security.FieldChecker;
import security.Encryption;
import security.Expiration;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = {"/shoppingcart", "/checkout", "/success", "/addtocart", "/clear", "/removeitem"})
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
			case "/shoppingcart": shoppingCart(request, response);
			break;

			case "/checkout": checkout(request, response);
			break;

			case "/success": success(request, response);
			break;

			case "/addtocart": addToCart(request, response);
			break;

			case "/clear": clearCart(request, response);
			break;

			case "/removeitem": removeFromCart(request, response);
		}
	}

	protected void shoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
        	if(request.getSession().getAttribute("lastLogged") != null)
				request.getSession().setAttribute("lastLogged", LocalDateTime.now());
        	// declare flag variables
			boolean emptyFlag = true;

			// check cart session for items
			@SuppressWarnings("unchecked")
			ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("ShoppingCart");
			ArrayList<String> productNames = new ArrayList<>();
			float subtotal = 0;
			// check if there are items in the shopping cart
			if(cartlist.size() > 0 && cartlist != null){
				emptyFlag = false;

				// compute for subtotal
				for(int i = 0; i < cartlist.size(); i++){
					String pname = cartlist.get(i).getName().replace(' ', '+');
					String productPath = cartlist.get(i).getBagID() + "#" + pname;
					productNames.add(productPath);
					subtotal += cartlist.get(i).getPrice();
				}

				// set computed total as request attribute "subtotal"
				request.setAttribute("subtotal", subtotal);
			}

			request.setAttribute("empty", emptyFlag);
			request.setAttribute("subtotal", subtotal);
			request.setAttribute("productPaths", productNames);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        else request.getRequestDispatcher("page-401.jsp").forward(request, response);
	}

	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
			if(request.getSession().getAttribute("lastLogged") != null)
				request.getSession().setAttribute("lastLogged", LocalDateTime.now());
			// declare flag variables
			boolean purposeFlag = false;
			boolean emptyFlag = true;
			boolean authenticFlag = false;

			Encryption e = new Encryption();

			// check purpose if it contains "cart"
			String purpose = request.getParameter("purpose");
			if(purpose.equals("cart"))
				purposeFlag = true;

			@SuppressWarnings("unchecked")
			ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("ShoppingCart");
			if(cartlist.size() > 0 && cartlist != null)
				emptyFlag = false;
			
			if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
				User currentUser = (User)request.getSession().getAttribute("Account");
				if(UserService.getUser(e.decryptID(currentUser.getUserID())) != null)
					authenticFlag = true;

				// autofill the fields for logged and authentic users
				if(authenticFlag){
					request.setAttribute("autofill", true);
					Address currentAddress = AddressService.getAddress(e.decryptID(currentUser.getUserID()));
					request.setAttribute("address", currentAddress);
				}

				else{
					request.setAttribute("address", null);
					request.setAttribute("autofill", false);
				}
			}

			// check to validate the visiting of the checkout page
			if(purposeFlag && !emptyFlag){
				// compute for subtotal
				float subtotal = 0;
				for(int i = 0; i < cartlist.size(); i++)
					subtotal += cartlist.get(i).getPrice();

				// set the subtotal as attribute
				request.setAttribute("subtotal", subtotal);

				// set error message as FALSE by default
				request.setAttribute("error", false);

				// dispatch to the checkout page
				request.getRequestDispatcher("checkout.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-403.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-401.jsp").forward(request, response);
	}

	protected void success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare flag variables
		boolean purposeFlag = false;
		boolean emptyFlag = true;
		boolean validFieldFlag = false;

		Encryption e = new Encryption();

		// check for purpose
		String purpose = request.getParameter("purpose");
		purposeFlag = purpose.equals("checkout");

		// check for empty cartlist
		@SuppressWarnings("unchecked")
		ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("ShoppingCart");
		if(cartlist.size() > 0)
			emptyFlag = false;

		// check for user type
		User currentUser = (User)request.getSession().getAttribute("Account");

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String location = request.getParameter("location");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		int postcode = 1000;
		try{
			postcode = Integer.parseInt(request.getParameter("postcode"));
		} catch(Exception er){
			postcode = 1000;
		}

		FieldChecker fc = new FieldChecker();
		User tempUser = new User();
		tempUser.setFirstName(firstname);
		tempUser.setLastName(lastname);
		tempUser.setEmail(email);
		tempUser.setPhone(phone);
		
		Address tempAddress = new Address();
		tempAddress.setLocation(location);
		tempAddress.setCity(city);
		tempAddress.setPostcode(postcode);
		tempAddress.setProvince(province);
		
		validFieldFlag = fc.checkOrderFields(tempUser, tempAddress);

		if(purposeFlag && !emptyFlag && validFieldFlag){
			long userid = -1;

			// check the type of user ordering
			if(currentUser != null)
				userid = e.decryptID(currentUser.getUserID());

			else{
				// create new temporary user
				List<User> userlist = UserService.getAllUsers();
				userid = userlist.get(userlist.size() - 1).getUserID() + 1;

				tempUser.setUserID(userid);
				tempUser.setPassword("");
				tempUser.setUserType("temp");
				UserService.addUser(tempUser);
			}

			// create orders for items in the shopping cart
			long orderOffset = 1;
			List<Purchase> orderlist = PurchaseService.getAllOrders();
			if(orderlist != null)
				orderOffset = orderlist.get(orderlist.size() - 1).getOrderID() + 1;
			
			for(int i = 0; i < cartlist.size(); i++){
				Purchase newOrder = new Purchase();
				newOrder.setOrderID(orderOffset + (long)i);
				newOrder.setUserID(userid);
				newOrder.setBagID(e.decryptID(cartlist.get(i).getBagID()));
				newOrder.setCity(city);
				newOrder.setLocation(location);
				newOrder.setPostcode(postcode);
				newOrder.setProvince(province);
				LocalDateTime now = LocalDateTime.now();
				newOrder.setOrderDate(now.getMonthValue() + "/" + now.getDayOfMonth() + "/" + now.getYear());
				newOrder.setOrderTime(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
				newOrder.setStatus(0);
				PurchaseService.addOrder(newOrder);
			}

			//invalidate shopping cart session
			ArrayList<Bag> emptyList = new ArrayList<>();
			request.getSession().setAttribute("ShoppingCart", emptyList);

			// dispatch to success page
			request.setAttribute("error", validFieldFlag);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}

		else if(purposeFlag && !emptyFlag && !validFieldFlag){
			request.setAttribute("error", !validFieldFlag);
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-404.jsp").forward(request, response);
	}

	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declare flag variables
		boolean validProductPath = true;

		@SuppressWarnings("unchecked")
		ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("ShoppingCart");
		if(cartlist.size() == 0 || cartlist == null)
			cartlist = new ArrayList<>();

		Encryption e = new Encryption();
		String productPath = request.getParameter("productPath");
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

			long decryptedID = e.decryptID(encryptedID);
			String productName = splitParts[1].replace('+', ' ');

			Bag selectedBag = BagService.getBag(decryptedID);
			if(selectedBag != null && productName.equalsIgnoreCase(selectedBag.getName()))
				foundFlag = true;

			request.setAttribute("error", foundFlag);

			if(foundFlag){
				selectedBag.setBagID(e.encryptID(selectedBag.getBagID()));
				cartlist.add(selectedBag);
				request.getSession().setAttribute("ShoppingCart", cartlist);
			}
		}

		shoppingCart(request, response);
	}

	protected void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productPath = request.getParameter("item");
		Encryption e = new Encryption();

		// declare boolean variables
		boolean validProductPath = true;

		@SuppressWarnings("unchecked")
		ArrayList<Bag> cartlist = (ArrayList<Bag>) request.getSession().getAttribute("ShoppingCart");
		if(cartlist.size() > 0 && cartlist != null){
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

				long decryptedID = e.decryptID(encryptedID);
				String productName = splitParts[1].replace('+', ' ');

				Bag selectedBag = BagService.getBag(decryptedID);
				if(selectedBag != null && productName.equalsIgnoreCase(selectedBag.getName()))
					foundFlag = true;

				request.setAttribute("error", foundFlag);

				if(foundFlag){
					for(int i = 0; i < cartlist.size(); i++)
						if(encryptedID == cartlist.get(i).getBagID()){
							cartlist.remove(i);
							break;
						}

					request.getSession().setAttribute("ShoppingCart", cartlist);
				}
			}

			shoppingCart(request, response);
		}

		else shoppingCart(request, response);
	}

	protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Bag> emptylist = new ArrayList<>();
		request.getSession().setAttribute("ShoppingCart", emptylist);
		shoppingCart(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
