package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.time.LocalDateTime;

import beans.User;
import beans.Address;
import services.UserService;
import services.AddressService;
import security.Encryption;
import security.FieldChecker;
import security.Expiration;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = {"/profile-general", "/profile-address", "/profile-password", "/profile"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet(){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		switch(path){
			case "/profile": profileRequest(request, response);
			break;

			case "/profile-general": profileGeneral(request, response);
			break;

			case "/profile-address": profileAddress(request, response);
			break;

			case "/profile-password": profilePassword(request, response);
			break;
		}
	}

	protected void profileRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				String purpose = request.getParameter("purpose");
				if(purpose != null){
					if(purpose.equals("edit-pa"))
						editAddress(request, response);

					else if(purpose.equals("edit-pg"))
						editGeneral(request, response);

					else if (purpose.equals("edit-pp"))
						editPassword(request, response);

					else profileGeneral(request, response);
				}

				else profileGeneral(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void editGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check for logged users
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// security variables
				Encryption e = new Encryption();
				FieldChecker fc = new FieldChecker();
				
				// get parameter values of firstname, lastname, email, phone
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String answer = request.getParameter("securityAnswer");
				// password as security answer
				String securityPassword = request.getParameter("securityPassword");

				// declare flags
				boolean validPasswordFlag = false;
				boolean validCredentialsFlag = false;
				boolean errorFlag = false;
				boolean successFlag = false;

				User currentUser = (User)request.getSession().getAttribute("Account");
				long decryptedID = e.decryptID(currentUser.getUserID());
				if(UserService.getUser(decryptedID) != null){
					currentUser = UserService.getUser(decryptedID);
					// decrypt the current user's password
					String decryptedPassword = e.decryptPassword(currentUser.getPassword());

					// compare it with the security password
					if(decryptedPassword.equals(securityPassword))
						validPasswordFlag = true;

					else errorFlag = true;

					User temp = new User();
					temp.setFirstName(firstname);
					temp.setLastName(lastname);
					temp.setEmail(email);
					temp.setPhone(phone);
					temp.setAnswer(answer);
					validCredentialsFlag = fc.checkProfileGeneral(temp);

					// modify the currentUser's credentials
					if(validCredentialsFlag && validPasswordFlag && !errorFlag){
						currentUser.setFirstName(firstname);
						currentUser.setLastName(lastname);
						currentUser.setEmail(email);
						currentUser.setPhone(phone);
						currentUser.setAnswer(e.encryptAnswer(answer));

						UserService.updateUser(decryptedID, currentUser);
						request.getSession().setAttribute("Account", currentUser);

						successFlag = true;
						errorFlag = false;
						request.setAttribute("success", successFlag);
						request.setAttribute("error", errorFlag);
					}

					else if(!validPasswordFlag || errorFlag){
						successFlag = false;
						errorFlag = true;
						request.setAttribute("success", successFlag);
						request.setAttribute("error", errorFlag);
					}

					request.getRequestDispatcher("profile-general.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-404.jsp").forward(request, response);
			}
			
			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void profileGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());

				request.getRequestDispatcher("profile-general.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void editAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check for logged users
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				// security variables
				Encryption e = new Encryption();
				FieldChecker fc = new FieldChecker();
				
				// get parameter values of firstname, lastname, email, phone
				String location = request.getParameter("location");
				String city = request.getParameter("city");
				int postcode = Integer.parseInt(request.getParameter("postcode"));
				String province = request.getParameter("province");
				// password as security answer
				String securityPassword = request.getParameter("securityPassword");

				// declare flags
				boolean validPasswordFlag = false;
				boolean validCredentialsFlag = false;
				boolean errorFlag = false;
				boolean successFlag = false;

				User currentUser = (User)request.getSession().getAttribute("Account");
				long decryptedID = e.decryptID(currentUser.getUserID());
				if(UserService.getUser(decryptedID) != null){
					currentUser = UserService.getUser(decryptedID);
					
					// decrypt the current user's password
					String decryptedPassword = e.decryptPassword(currentUser.getPassword());

					// compare it with the security password
					if(decryptedPassword.equals(securityPassword))
						validPasswordFlag = true;

					else errorFlag = true;

					Address temp = new Address();
					temp.setLocation(location);
					temp.setCity(city);
					temp.setPostcode(postcode);
					temp.setProvince(province);
					validCredentialsFlag = fc.checkProfileAddress(temp);

					// modify the currentAddress credentials
					if(validCredentialsFlag && validPasswordFlag && !errorFlag){
						Address currentAddress = AddressService.getAddress(currentUser.getUserID());
						if(currentAddress == null){
							currentAddress = new Address();
							currentAddress.setUserID(decryptedID);
							currentAddress.setLocation(location);
							currentAddress.setCity(city);
							currentAddress.setPostcode(postcode);
							currentAddress.setProvince(province);
							AddressService.addAddress(currentAddress);
						}

						else{
							currentAddress = new Address();
							currentAddress.setUserID(decryptedID);
							currentAddress.setLocation(location);
							currentAddress.setCity(city);
							currentAddress.setPostcode(postcode);
							currentAddress.setProvince(province);
							AddressService.updateAddress(currentAddress.getUserID(), currentAddress);
						}

						successFlag = true;
						errorFlag = false;
					}

					else if(!validPasswordFlag || errorFlag){
						successFlag = false;
						errorFlag = true;
					}

					request.setAttribute("success", successFlag);
					request.setAttribute("error", errorFlag);
					request.setAttribute("address", AddressService.getAddress(e.decryptID(currentUser.getUserID())));
					request.getRequestDispatcher("profile-address.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-404.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void profileAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());

				List<Address> addresslist = AddressService.getAllAddress();
				User currentUser = (User) request.getSession().getAttribute("Account");
				Address currentAddress = null;
				Encryption e = new Encryption();
				for(int i = 0; i < addresslist.size(); i++)
					if(e.decryptID(currentUser.getUserID()) == addresslist.get(i).getUserID()){
						currentAddress = addresslist.get(i);
						break;
					}

				request.setAttribute("address", currentAddress);
				request.getRequestDispatcher("profile-address.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request,response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void editPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check for logged users
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());

				// security variables
				Encryption e = new Encryption();
				FieldChecker fc = new FieldChecker();
				
				// get parameter values of firstname, lastname, email, phone
				String oldPassword = request.getParameter("oldpassword");
				String newPassword = request.getParameter("newpassword");
				String confirmNewPass = request.getParameter("confirmpass");

				// declare flags
				boolean validPasswordFlag = false;
				boolean validCredentialsFlag = false;
				boolean errorFlag = false;
				boolean successFlag = false;

				User currentUser = (User)request.getSession().getAttribute("Account");
				long decryptedID = e.decryptID(currentUser.getUserID());
				if(UserService.getUser(decryptedID) != null){
					currentUser = UserService.getUser(decryptedID);
					// decrypt the current user's password
					String decryptedPassword = e.decryptPassword(currentUser.getPassword());

					// compare it with the security password
					if(decryptedPassword.equals(oldPassword))
						validPasswordFlag = true;

					else errorFlag = true;

					validCredentialsFlag = fc.checkProfilePassword(oldPassword, newPassword, confirmNewPass, currentUser);

					// modify the currentUser's credentials
					if(validCredentialsFlag && validPasswordFlag && !errorFlag){
						currentUser.setPassword(e.encryptPassword(newPassword));
						UserService.updateUser(decryptedID, currentUser);

						successFlag = true;
						errorFlag = false;
						request.setAttribute("success", successFlag);
						request.setAttribute("error", errorFlag);
					}

					else if(!validPasswordFlag || errorFlag){
						successFlag = false;
						errorFlag = true;
						request.setAttribute("success", successFlag);
						request.setAttribute("error", errorFlag);
					}

					request.getRequestDispatcher("profile-password.jsp").forward(request, response);
				}

				else request.getRequestDispatcher("page-404.jsp").forward(request, response);
			}

			else request.getRequestDispatcher("page-401.jsp").forward(request, response);
		}

		else request.getRequestDispatcher("page-403.jsp").forward(request, response);
	}

	protected void profilePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("Account") != null && request.getSession().getAttribute("adminAccount") == null){
			if(!Expiration.isExpired((LocalDateTime)request.getSession().getAttribute("lastLogged"))){
				if(request.getSession().getAttribute("lastLogged") != null)
					request.getSession().setAttribute("lastLogged", LocalDateTime.now());
				
				request.getRequestDispatcher("profile-password.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
