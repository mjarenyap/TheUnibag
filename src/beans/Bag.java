package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */

@Entity(name = "bag")
public class Bag {
	@Id
	@Column(name = "bagID")
	private long bagID;
	@Column
	private String name;
	@Column
	private String brand;
	@Column
	private String description;
	@Column
	private String color;
	@Column
	private String type; 
	@Column
	private String collection;
	@Column
	private int rating;
	@Column
	private float price; 
	
	
	/*Setters and Getters*/
	
	@Override
	public String toString() {
		return "Bag [id=" + bagID + ", brand=" + brand + ", description=" + description + ", color=" + color + ", type=" + type + ", collection=" + collection + ", rating=" + rating  +" price=" + price  +"]";
	}


	public long getBagID() {
		return bagID;
	}


	public void setBagID(long bagID) {
		this.bagID = bagID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCollection() {
		return collection;
	}


	public void setCollection(String collection) {
		this.collection = collection;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
}
