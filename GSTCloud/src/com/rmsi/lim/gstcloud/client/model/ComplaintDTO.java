package com.rmsi.lim.gstcloud.client.model;

import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable; 



public class ComplaintDTO  implements IsSerializable{
	
	boolean currentlySelected;
	
	private String category;
	
    private String circle;

    
  
    private String subtype;
    
    private String subSubtype;
    
    private String problemsummary;
  
 //Stores the coverage of a tower in wkt form 
    private String coverage;
  
  //Stores the height of a tower in meters 
    private Double height;
 
    private Double latitude;
    
    private Double longitude;
    
    public ComplaintDTO()
    {
    	
    }
    
    
    public ComplaintDTO(String category,
    		            String circle,
    		            String subtype,
    		            String subSubtype, 
    		            String problemsummary,
    		            Double latitude, 
    		            Double longitude, 
    		            boolean selected) {
    	this.category=category;
    	this.circle = circle; 
    	
    	this.subtype= subtype;
    	this.subSubtype= subSubtype;
    	this.problemsummary= problemsummary;
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
   
	
   
    public String getCircle() {
        return circle ;
    }
   
    public String getproblemsummary() {
        return problemsummary ;
    }
   
    public String getSubtype() {
        return subtype ;
    }
   
    public String getSubSubtype() {
        return subSubtype ;
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

    public void setCircle(String circle) {
        this.circle = circle;
    }
  
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    public void setSubSubtype(String subSubtype) {
        this.subSubtype = subSubtype;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setproblemsummary(String problemsummary) {
        this.problemsummary= problemsummary;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
