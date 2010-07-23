package com.rmsi.lim.gstcloud.client.model;

import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

public class TowerDTO  implements IsSerializable{
	
	boolean currentlySelected;
	
	private String category;
	
    private String name;

    private String owner;
  
    private String status;
  
 //Stores the coverage of a tower in wkt form 
    private String coverage;
  
  //Stores the height of a tower in meters 
    private Double height;
 
    private Double latitude;
    
    private Double longitude;
    
    public TowerDTO()
    {
    	
    }
    
    public TowerDTO(String category,String name, String owner, String status, String coverage, Double height, Double latitude, Double longitude, boolean selected) {
    	this.category=category;
    	this.name = name;
        this.owner = owner;
        this.status = status;
        this.coverage = coverage;
        this.height = height;
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
    
    public String getOwner() {
        return owner;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getCoverage() {
        return coverage;
    }
    
    public Double getHeight() {
        return height;
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

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
