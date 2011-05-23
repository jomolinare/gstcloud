package com.rmsi.lim.gstcloud.client.model;
import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 

import de.micromata.opengis.kml.v_2_2_0.Coordinate;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

public class PlTO implements IsSerializable{

	private boolean currentlySelected;
	
    private Double latitude;
    
    private Double longitude;
    
    private String placeName;
    
    private String pcord[];
    
    private String intrctData; 
  
    
    public PlTO()
    {
    	
    }
    
    public PlTO(String placeName,Double latitude, Double longitude,String pcord[], boolean selected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
        this.pcord = pcord;
        this.currentlySelected = selected;
        
    }
    public PlTO(String intrctData)
    {
    	this.intrctData = intrctData;
    }
    public Boolean getCurrentlySelected(){
		return currentlySelected;
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
    public String[] getPolygonObject()
    {
    	return pcord;
    }
    public String getIntrctData()
    {
    	return intrctData;
    }
    public void setCurrentlySelected(boolean select){
    	this.currentlySelected=select;
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
    public void setPolygonObject(String pcord[])
    {
    	this.pcord = pcord;
    }
    
    public void setIntrctData(String intrctData)
    {
    	this.intrctData = intrctData;
    }
   
}

