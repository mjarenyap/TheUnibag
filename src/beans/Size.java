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

@Entity(name="size")
public class Size {
	
	@Id
	@Column(name="sizeID")
	private long sizeID;
	@Column
	private long bagID;
	@Column
	private String width;
	@Column
	private String height;
	@Column
	private String length;
	@Column
	private String volume; 
	
	/*Setters and Getters*/
	
	public long getSizeID() {
		return sizeID;
	}
	
	public void setSizeID(long sizeID) {
		this.sizeID = sizeID;
	}
	
	public long getBagID() {
		return bagID;
	}
	
	public void setBagID(int bagID) {
		this.bagID = bagID;
	}
	
	public String getWidth() {
		return width; 
	}
	
	public void setWidth(String width) {
		this.width = width; 
	}
	
	public String getHeight() {
		return height; 
	}
	
	public void setHeight(String height) {
		this.height = height; 
	}
	
	public String getLength() {
		return length; 
	}
	
	public void setLength(String length) {
		this.length = length; 
	}
	
	public String getVolume() {
		return volume; 
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "sizes [id=" + sizeID + ", bagID=" + bagID + ", witdh=" + width + ", height=" + height + ", length=" + length + ", volume=" + volume + "]";
	}

}
