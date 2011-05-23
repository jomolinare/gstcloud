package com.rmsi.lim.gstcloud.client.model;

import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

public class NLRTO implements IsSerializable{
//  @Persistent
	private boolean currentlySelected;
	
    private String category;

//    @Persistent
    private Double latitude;
    
//    @Persistent
    private Double longitude;
    
//    @Persistent
    private String placeName;
    
    public NLRTO()
    {
    	
    }
    
    public NLRTO(String category,Double latitude, Double longitude, String placeName,boolean selected) {
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
        this.currentlySelected = selected;
//        this.setGeoCells(geoCells);
        
    }
    
    public Boolean getCurrentlySelected(){
		return currentlySelected;
	}
    
    public String getCategory() {
        return category;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public void setCurrentlySelected(boolean select){
    	this.currentlySelected=select;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public void setPlaceName(String placsName) {
        this.placeName = placsName;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

