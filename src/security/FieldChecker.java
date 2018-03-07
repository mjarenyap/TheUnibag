package security;

import beans.User;

public class FieldChecker {
	public boolean checkLogin(String email, String password){
		// check if empty
		if(email.length() == 0 || password.length() == 0)
			return false;
		
		if(email == null || password == null)
			return false;
		
		if(!email.contains("@"))
			return false;
		
		return true;
	}
	
	public boolean checkSignup(User newUser){
		
		if(newUser.getFirstName().length() == 0 || newUser.getLastName().length() == 0 ||
		newUser.getEmail().length() == 0 || newUser.getPassword().length() == 0)
			return false;
		
		if(newUser.getFirstName() == null || newUser.getLastName() == null ||
		newUser.getEmail() == null || newUser.getPassword() == null)
			return false;
		
		if(!newUser.getEmail().contains("@"))
			return false;

		// atleast 8 characters
		String password = newUser.getPassword();
		if(password.length() < 8)
			return false;

		// atleast 1 capital letter
		boolean capitalLetter = false;
		for(int i = 0; i < password.length(); i++){
			if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'){
				capitalLetter = true;
				break;
			}
		}

		// atleast 1 symbol
		boolean symbolFlag = false;
		for(int i = 0; i < password.length(); i++){
			if(password.charAt(i) < 'A' || password.charAt(i) > 'z'){
				symbolFlag = true;
				break;
			}
		}

		// atleast 1 number
		boolean numberFlag = false;
		for(int i = 0; i < password.length(); i++){
			if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){
				numberFlag = true;
				break;
			}
		}

		if(!capitalLetter || !symbolFlag || !numberFlag)
			return false;
		
		return true;
	}
}
