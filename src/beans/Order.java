package beans;
import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */

@Entity(name="order")
public class Order {
	@Id
	@Column(name="orderID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderID;
	@Column
	private long userID;
	@Column
	private long bagID;
	@Column
	private long sizeID;
	@Column
	private LocalDateTime orderDate;
	@Column
	private String city; 
	@Column
	private int postcode;
	@Column
	private String province;
	@Column
	private String location;
	
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
	
	/*Setters and Getters*/
	
	public long getOrderID() {
		return orderID;
	}
	
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	
	public long getUserID() {
		return userID;	
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public long getBagID() {
		return bagID;
	}
	
	public void setBagID(long bagID) {
		this.bagID = bagID;
	}
	
	public long getSizeID() {
		return sizeID;
	}
	
	public void setSizeID(long sizeID) {
		this.sizeID = sizeID;
	}
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDateTime date) {
		this.orderDate = date;
	}
	
	@Override
	public String toString() {
		return "Orders [id=" + orderID + ", userID=" + userID + ", bagID" + bagID + ", sizeID=" + sizeID  + orderDate.toString() +"]";
	}
}
