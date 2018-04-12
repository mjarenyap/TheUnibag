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
		int[] charOffset = {10, 17};

		for(int i = 0; i < password.length(); i++){
			int shifted = password.charAt(i) + charOffset[i % 2];
			int bounded = shifted % 126;
			if(bounded >= 0 && bounded <= 32)
				bounded += 33;
			encrypted += Character.toString((char)bounded);
		}

		return encrypted;
	}

	public String decryptPassword(String password){
		String decrypted = "";
		int[] charOffset = {10, 17};

		for(int i = 0; i < password.length(); i++){
			int bounded = password.charAt(i) - charOffset[i % 2];
			if(bounded >= 0 && bounded <= 32){
				bounded = 33 - bounded;
				bounded = 126 - bounded;
			}

			int shifted = bounded;
			decrypted += Character.toString((char)shifted);
		}
		return decrypted;
	}

	public String encryptAnswer(String answer){
		String encrypted = "";
		int[] charOffset = {10, 17};

		for(int i = 0; i < answer.length(); i++){
			int shifted = answer.charAt(i) + charOffset[i % 2];
			int bounded = shifted % 126;
			if(bounded >= 0 && bounded <= 31)
				bounded += 32;
			encrypted += Character.toString((char)bounded);
		}

		return encrypted;
	}

	public String decryptAnswer(String answer){
		String decrypted = "";
		int[] charOffset = {10, 17};

		for(int i = 0; i < answer.length(); i++){
			int bounded = answer.charAt(i) - charOffset[i % 2];
			if(bounded >= 0 && bounded <= 31){
				bounded = 32 - bounded;
				bounded = 126 - bounded;
			}

			int shifted = bounded;
			decrypted += Character.toString((char)shifted);
		}
		return decrypted;
	}
}