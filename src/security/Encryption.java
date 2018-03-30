package security;

public class Encryption{
	public Encryption(){}

	public long encryptID(long id){
		long encrypted = id;

		encrypted *= 404;
		encrypted += 403;
		return encrypted;
	}

	public long decryptID(long id){
		long decrypt = id;

		decrypt -= 403;
		decrypt /= 404;
		return decrypt;
	}

	public String encryptPassword(String password){
		String encrypted = "";
		int[] charOffset = {10, -7};

		for(int i = 0; i < password.length(); i++){
			if(i % 2 == 0)
				encrypted += (password.charAt(i) + charOffset[0]);

			else encrypted += (password.charAt(i) + charOffset[1]);
		}

		return encrypted;
	}

	public String decryptPassword(String password){
		String decrypted = "";
		int[] charOffset = {10, -7};

		for(int i = 0; i < password.length(); i++){
			if(i % 2 == 0)
				decrypted += (password.charAt(i) - charOffset[0]);

			else decrypted += (password.charAt(i) - charOffset[1]);
		}

		return decrypted;
	}
}