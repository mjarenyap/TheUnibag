/**
 * 
 */
package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */




@Entity(name = "user")
public class User {
	@Id
	@Column(name="userID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userID;
	
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String email;
	@Column
	private String password; 
	@Column
	private String phone;
	@Column
	private String userType; 
	
	
	/*Setters and Getters*/
	
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	public void setFirstName(String fname) {
		this.fname = fname;
	}
	
	public String getLastName() {
		return lname;
	}
	
	public void setLastName (String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone; 
	}
	
	public String getUserType() {
		return userType; 
	}
	
	public void setUserType(String type) {
		this.userType = type; 
	}
	
	
	@Override
	public String toString() {
		return "Users [id=" + userID + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + ", phone=" + phone + ", userType=" + userType  + "]";
	}
	

}
