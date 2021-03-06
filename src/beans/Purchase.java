package beans;

import javax.persistence.Entity;
import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */

@Entity(name = "purchase")
public class Purchase {
	@Id
	@Column(name = "orderID")
	private long orderID;
	@Column
	private long userID;
	@Column
	private long bagID;
	@Column
	private String orderDate;
	@Column
	private String orderTime;
	@Column
	private String city; 
	@Column
	private int postcode;
	@Column
	private String province;
	@Column
	private String location;
	@Column
	private int status;
	
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [id=" + orderID + ", userID=" + userID + ", bagID" + bagID  + orderDate.toString() +"]";
	}
}
