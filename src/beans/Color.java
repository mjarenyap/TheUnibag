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

@Entity (name = "color")
public class Color {

	@Id
	@Column(name="bagID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bagID;
	@Column
	private String color;
	
	/*Setters and Getters*/
	
	public long getBagID(){
		return bagID;
	}
	
	public void setBagID(long bagID) {
		this.bagID = bagID; 
	}
	
	public String getColor() {
		return color; 
	}
	
	public void setColor(String color) {
		this.color = color; 
	}
	
	@Override
	public String toString() {
		return "Color [id=" + bagID + ", color=" + color + "]";
	}
	
}
