package com.rmsi.lim.gstcloud.server.model;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.LocationCapable;
import com.beoui.geocell.model.Point;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class CSC extends Geohashed implements LocationCapable,IsSerializable
{
  @Persistent
  private String category;
  
  @Persistent
  private String name;

  @Persistent
    private String contact_person;
  
  @Persistent
  private String address;  
 
  @Persistent
  private String tower_name;
    
  @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;    
      
    public CSC(String category,String name, String contact_person, String address, String tower_name, Double latitude, Double longitude, List<String> geoCells) {
        this.category=category;
    	this.name = name;
        this.contact_person = contact_person;
        this.address = address;
        this.tower_name = tower_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.setGeoCells(geoCells);
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

    public void setCategory(String category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setContact_person(String contact_person) {
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

	public CSC(CSCDTO csc) {
		this.setCategory(csc.getCategory());
		this.setName(csc.getName());
		this.setContact_person(csc.getContact_person());
		this.setAddress(csc.getAddress());
		this.setTower_name(csc.getTower_name());	
		this.setLatitude(csc.getLatitude());
		this.setLongitude(csc.getLongitude());
	    this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(csc.getLatitude(),csc.getLongitude())));		
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
