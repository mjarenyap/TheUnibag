package security;

public class Attempt{
	private String email;
	private int count;

	public Attempt(){}

	public Attempt(String email, int count){
		this.email = email;
		this.count = count;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}
}