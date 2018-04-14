package security;
import beans.User;
import security.Encryption;
import java.util.List;

public class DuplicateChecker{
	public DuplicateChecker(){}

	public boolean checkUser(User newUser, List<User> userlist){
		Encryption e = new Encryption();
		long decryptedID = e.decryptID(newUser.getUserID());
		for(int i = 0; i < userlist.size(); i++)
			if(newUser.getEmail().equalsIgnoreCase(userlist.get(i).getEmail()) && decryptedID != userlist.get(i).getUserID())
				return true;

		return false;
	}
}