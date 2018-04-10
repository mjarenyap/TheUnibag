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
import beans.User;
import beans.Address;
import security.Encryption;
import security.FieldChecker;
import security.DuplicateChecker;
import security.Expiration;
import services.UserService;
import services.AddressService;

/**
 * Servlet implementation class UserAdminServlet
 */
@WebServlet(urlPatterns = {"/admin/adduser", "/admin/allusers", "/admin/viewuser", "/admin/addeduser", "/admin/editeduser", "/admin/deleteuser", "/admin/deleteusers", "/admin/editaccount"})
public class UserAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		switch(path){
			case "/admin/adduser": addUserPage(request, response);
			break;

			case "/admin/allusers": allUsers(request, response);
			break;

			case "/admin/viewuser": viewUser(request, response);
			break;

			case "/admin/addeduser": addUser(request, response);
			break;

			case "/admin/editeduser": editUser(request, response);
			break;

			case "/admin/deleteuser": deleteUser(request, response);
			break;

			case "/admin/deleteusers": deleteUsers(request, response);
			break;

			case "/admin/editaccount": editAccount(request, response);
			break;
		}
	}

	protected void editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				Encryption e = new Encryption();

				User currentUser = (User)request.getSession().getAttribute("Account");
				List<Address> addresslist = AddressService.getAllAddress();
				Address currentAddress = null;
				long decryptedID = e.decryptID(currentUser.getUserID());
				for(int i = 0; i < addresslist.size(); i++){
					if(decryptedID == addresslist.get(i).getUserID()){
						currentAddress = addresslist.get(i);
						break;
					}
				}

				request.setAttribute("adminAddress", currentAddress);
				request.getRequestDispatcher("edit-account.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void addUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				request.getRequestDispatcher("add-user.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get request parameter values of the ff.
		[ex. request.getParameter("name")]:
		- firstname - lastname - email - password - phone - usertype
		*/
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validCredentialFlag = false;
				boolean duplicateFlag = false;
				Encryption e = new Encryption();
				FieldChecker fc = new FieldChecker();
				DuplicateChecker dc = new DuplicateChecker();

				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String confirmPass = request.getParameter("confirmpassword");
				String phone = request.getParameter("phone");
				String userType = request.getParameter("userType").toLowerCase();
				String location = request.getParameter("location");
				String city = request.getParameter("city");
				String province = request.getParameter("province");
				int postcode = 1000;

				try{
					postcode = Integer.parseInt(request.getParameter("postcode"));
				} catch(Exception er){
					postcode = 1000;
				}

				// create a User object and set the necessary attributes
				User newUser = new User();

				// store the new user in the database
				newUser.setFirstName(firstname);
				newUser.setLastName(lastname);
				newUser.setEmail(email);
				newUser.setPassword(password);
				newUser.setPhone(phone);
				newUser.setUserType(userType);

				validCredentialFlag = fc.checkSignup(newUser);
				duplicateFlag = dc.checkUser(newUser, UserService.getAllUsers());
				
				if(validCredentialFlag && password.equals(confirmPass) && !duplicateFlag){
					List<User> userlist = UserService.getAllUsers();
					newUser.setPassword(e.encryptPassword(password));
					newUser.setUserID(userlist.get(userlist.size() - 1).getUserID() + 1);
					UserService.addUser(newUser);

					Address newAddress = new Address();
					newAddress.setUserID(newUser.getUserID());
					newAddress.setLocation(location);
					newAddress.setCity(city);
					newAddress.setPostcode(postcode);
					newAddress.setProvince(province);
					AddressService.addAddress(newAddress);

					// dispatch to admin-users.jsp
					allUsers(request, response);
				}

				else request.getRequestDispatcher("add-user.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void allUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				Encryption e = new Encryption();

				//to do put all users in an arraylist
				List<User> users = UserService.getAllUsers();
				ArrayList<String> usernames = new ArrayList<>();
				for(int i = 0; i < users.size(); i++){
					long encryptedID = e.encryptID(users.get(i).getUserID());
					String ename = users.get(i).getEmail();
					ename = encryptedID + "#" + ename;
					usernames.add(ename);
				}

				// dispatch to admin-users.jsp
				request.setAttribute("userlist", users);
				request.setAttribute("usernames", usernames);
				request.getRequestDispatcher("admin-user.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Get the product ID as the request parameter.
		Make sure to parse it as a float.
		Get the user ID as the request parameter.
		Make sure to parse it as a long.
		*/

		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validUserPath = true;
				Encryption e = new Encryption();

				// get user path
				String userPath = request.getParameter("username");
				String[] splitParts = userPath.split("#");
				long encryptedID = -1;

				try{
					encryptedID = Long.parseLong(splitParts[0]);
				} catch(Exception er){
					validUserPath = false;
				}

				if(splitParts.length != 2)
					validUserPath = false;

				if(validUserPath){
					// declare second layer flag variables
					boolean foundFlag = false;

					// decrypt the id and email of the user
					long decryptedID = e.decryptID(encryptedID);
					String email = splitParts[1];

					// check for existing selectedUser
					User selectedUser = UserService.getUser(decryptedID);
					if(selectedUser != null)
						if(selectedUser.getEmail().equalsIgnoreCase(email))
							foundFlag = true;

					if(foundFlag){
						Address selectedAddress = AddressService.getAddress(selectedUser.getUserID());
						
						// set selected user and address as request attribute
						request.setAttribute("featuredUser", selectedUser);
						request.setAttribute("featuredAddress", selectedAddress);
						request.setAttribute("userPath", userPath);
						request.getRequestDispatcher("view-user.jsp").forward(request, response);
					}

					else request.getRequestDispatcher("page-403.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-403.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validUserPath = true;
				Encryption e = new Encryption();
				DuplicateChecker dc = new DuplicateChecker();
				FieldChecker fc = new FieldChecker();

				// get user path
				String userPath = request.getParameter("username");
				String[] splitParts = userPath.split("#");
				long encryptedID = -1;

				try{
					encryptedID = Long.parseLong(splitParts[0]);
				} catch(Exception er){
					validUserPath = false;
				}

				if(splitParts.length != 2)
					validUserPath = false;

				if(validUserPath){
					// declare second layer flag variables
					boolean foundFlag = false;
					boolean duplicateFlag = false;
					boolean validCredentialFlag = false;

					// decrypt the id and email of the user
					long decryptedID = e.decryptID(encryptedID);
					String emailpath = splitParts[1];

					// check for existing selectedUser
					User selectedUser = UserService.getUser(decryptedID);
					if(selectedUser != null)
						if(selectedUser.getEmail().equalsIgnoreCase(emailpath))
							foundFlag = true;

					if(foundFlag){
						String firstname = request.getParameter("firstname");
						String lastname = request.getParameter("lastname");
						String email = request.getParameter("email");
						String phone = request.getParameter("phone");
						String userType = request.getParameter("userType");
						String location = request.getParameter("location");
						String city = request.getParameter("city");
						String province = request.getParameter("province");
						int postcode = 1000;

						try{
							postcode = Integer.parseInt(request.getParameter("postcode"));
						} catch(Exception er){
							postcode = 1000;
						}

						selectedUser.setFirstName(firstname);
						selectedUser.setLastName(lastname);
						selectedUser.setEmail(email);
						selectedUser.setPhone(phone);
						selectedUser.setUserType(userType);

						validCredentialFlag = fc.checkSignup(selectedUser);
						duplicateFlag = dc.checkUser(selectedUser, UserService.getAllUsers());
						if(selectedUser.getEmail().equals(emailpath))
							duplicateFlag = false;

						Address selectedAddress = AddressService.getAddress(selectedUser.getUserID());
						if(!duplicateFlag && validCredentialFlag){
							selectedAddress.setLocation(location);
							selectedAddress.setCity(city);
							selectedAddress.setProvince(province);
							selectedAddress.setPostcode(postcode);

							UserService.updateUser(selectedUser.getUserID(), selectedUser);
							AddressService.updateAddress(selectedAddress.getUserID(), selectedAddress);
							allUsers(request, response);
						}

						else{
							request.setAttribute("error", true);
							viewUser(request, response);
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

	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adminAccount") != null && request.getSession().getAttribute("Account") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// declare flag variables
				boolean validUserPath = true;
				Encryption e = new Encryption();

				// get user path
				String userPath = request.getParameter("username");
				String[] splitParts = userPath.split("#");
				long encryptedID = -1;

				try{
					encryptedID = Long.parseLong(splitParts[0]);
				} catch(Exception er){
					validUserPath = false;
				}

				if(splitParts.length != 2)
					validUserPath = false;

				if(validUserPath){
					String email = splitParts[1];
					long decryptedID = e.decryptID(encryptedID);
					User selectedUser = UserService.getUser(decryptedID);

					if(selectedUser != null){
						if(selectedUser.getEmail().equals(email)){
							UserService.deleteUser(decryptedID);
							allUsers(request, response);
						}
						
						else request.getRequestDispatcher("page-403.jsp").forward(request, response);
					}

					else request.getRequestDispatcher("page-403.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-403.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				ArrayList<User> archivelist = new ArrayList<>();
				if(toDelete != null){
					for(int i = 0; i < toDelete.length; i++){
						if(toDelete[i] != null){
							String userPath = toDelete[i];
							String[] splitParts = userPath.split("#");
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
								String username = splitParts[1];

								// search for a matched result
								User selectedUser = UserService.getUser(decryptedID);
								if(selectedUser != null){
									if(username.equalsIgnoreCase(selectedUser.getEmail()))
										archivelist.add(selectedUser);
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
							UserService.deleteUser(archivelist.get(i).getUserID());
				}

				request.setAttribute("errorPath", !validPaths);
				request.setAttribute("errorFound", !foundFlag);
				allUsers(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request,response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
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
