package security;
import beans.User;
import java.util.List;

public class DuplicateChecker{
	public DuplicateChecker(){}

	public boolean checkUser(User newUser, List<User> userlist){
		for(int i = 0; i < userlist.size(); i++)
			if(newUser.getEmail().equalsIgnoreCase(userlist.get(i).getEmail()))
				return true;

		return false;
	}
}