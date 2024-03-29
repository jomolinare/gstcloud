package com.rmsi.lim.gstcloud.client.model;

import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

public class CSCDTO  implements IsSerializable{
	
	boolean currentlySelected;
	
	private String category;
	
    private String name;

    private String contact_person;
  
    private String address;
  
    private String tower_name;
 
    private Double latitude;
    
    private Double longitude;
    
    public CSCDTO()
    {
    	
    }
    
    public CSCDTO(String category,String name, String contact_person, String address, String tower_name, Double latitude, Double longitude, boolean selected) {
    	this.category=category;
    	this.name = name;
        this.contact_person = contact_person;
        this.address = address;
        this.tower_name = tower_name;
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
    
    public String getContact_person() {
        return contact_person;
    }
    
    public String getAddress() {
        return address;
    }    
    
    public String getTower_name() {
        return tower_name;
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

    public void setcontact_person(String contact_person) {
        this.contact_person = contact_person;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setTower_name(String tower_name) {
        this.tower_name = tower_name;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
