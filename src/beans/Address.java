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

@Entity(name = "address")

public class Address {
	
	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userID;
	@Column
	private String city; 
	@Column
	private int postcode;
	@Column 
	private String province;
	@Column
	private String location;

	/*Setters and Getters*/
	
	public long getUserID() {
		return userID; 
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public String getCity() {
		return city; 
	}
	
	public void setCity(String city) {
		this.city = city; 
	}
	
	public int getPostcode() {
		return postcode;
	}
	
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province; 
	}
	
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + userID + ", city=" + city + ", postcode=" + postcode + ", province=" + province + ", location=" + location  + "]";
	}
}
