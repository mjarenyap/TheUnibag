package security;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class PasswordGenerator{
	//private static final String OUT_FILENAME = "/Users/../Desktop/your_new_password.txt";
	public PasswordGenerator(){}

	public static String generate(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		String newPassword = "";

		try{
			File desktop = new File(System.getProperty("user.home") + "/Desktop", "unibag_your_new_password.txt");
			fw = new FileWriter(desktop);
			bw = new BufferedWriter(fw);
			Random r = new Random();

			for(int i = 0; i < 10; i++){
				char newChar = (char)(r.nextInt(95) + 33);
				newPassword = newPassword + newChar;
			}

			bw.write("Your new password. Try not to forget this one ;)\n" + "---\n" + newPassword);
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch(Exception e){
			newPassword = null;
		}
		
		return newPassword;
	}
}