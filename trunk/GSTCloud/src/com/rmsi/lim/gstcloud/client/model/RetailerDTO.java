package com.rmsi.lim.gstcloud.client.model;

import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

public class RetailerDTO  implements IsSerializable{
	
	boolean currentlySelected;
	
	private String category;
	
    private String name;

    private String address;
    
    private Double latitude;
    
    private Double longitude;
    
    public RetailerDTO()
    {
    	
    }
    
    public RetailerDTO(String category,String name, String address, Double latitude, Double longitude, boolean selected) {
    	this.category=category;
    	this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentlySelected=selected;
    }

	public Boolean getCurrentlySelected(){
		return currentlySelected;
	}
   

    public String getCategory() {
        return category;
    }
   
	
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    public void setCurrentlySelected(boolean select){
    	this.currentlySelected=select;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
