package com.rmsi.lim.gstcloud.server.model;

import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import com.beoui.geocell.model.LocationCapable;
import com.google.gwt.user.client.rpc.IsSerializable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.Persistent;

import com.beoui.geocell.GeocellManager;

import com.beoui.geocell.model.Point;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;


@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class Complaint extends Geohashed implements LocationCapable,IsSerializable
{
  @Persistent
    private  String category;


  @Persistent
    private String circle;
  

  
 
  @Persistent 
  private String subtype;
  
  @Persistent
  private String problemsummary;

    
  @Persistent
    private String subSubtype;
    
    // @Persistent
   // private String Coverageissue-Timespecific/all;    
    
    @Persistent
    private  Double latitude;
    
    @Persistent
    private Double longitude;
      
    public Complaint(String category, String circle, String subtype,String subSubtype,String problemsummary,
    		Double latitude,Double longitude, List<String> geoCells) {
        this.category=category;
    	
        this.circle = circle;
       // this.BR= BR;
        this.subtype = subtype;
        //this.Coveragerelated = Coveragerelated;
      //  this.Coverageissue-Timespecific/all = Coverageissue-Timespecific/all;
        this.subSubtype = subSubtype;
        this.problemsummary = problemsummary;
        this.latitude = latitude;
        this.longitude = longitude;
        this.setGeoCells(geoCells);
    }
    public String getCategory() {
        return category;
    }
    
   
    
    public String getCircle() {
        return circle;
    }
    
    public String getProblemSummary() {
        return problemsummary;
    }
    
    public String getSubtype() {
        return subtype;
    }
    
     public  String getSubSubtype() {
      return subSubtype;
    }
public  Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    public void setCategory(String category) {
        this.category = category;
    }
 
    public void setProblemSummary(String problemsummary) {
        this.problemsummary = problemsummary;
    }

    public void setCircle(String Circle) {
        this.circle = circle;
        }
    
   
    
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    
     
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

	public Complaint(ComplaintDTO complaint) {
		this.setCategory(complaint.getCategory());
		this.setCircle(complaint.getCircle());
		this.setSubtype(complaint.getSubtype());
		this.setLatitude(complaint.getLatitude());
		this.setLongitude(complaint.getLongitude());
	    this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(complaint.getLatitude(),complaint.getLongitude())));		
	}

	public List<String> getGeocells() {
		
		return this.getGeoCells();
	}

	public String getKeyString() {
        return Long.valueOf(this.getObjectId()).toString();
    }

    public Point getLocation() {
        return new Point(this.getLatitude(),this.getLongitude());
    }
	
}
