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
import com.rmsi.lim.gstcloud.client.model.RetailerDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class Retailer extends Geohashed implements LocationCapable,IsSerializable
{
  @Persistent
    private String name;

  @Persistent
  private String category;

  @Persistent
    private String address;  
  
  @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;    
      
    public Retailer(String category,String name, String address, Double latitude, Double longitude, List<String> geoCells) {
        this.category=category;
    	this.name = name;
        this.address = address;        
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
    
    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

	public Retailer(RetailerDTO Retailer) {
		this.setCategory(Retailer.getCategory());
		this.setName(Retailer.getName());
		this.setAddress(Retailer.getAddress());	
		this.setLatitude(Retailer.getLatitude());
		this.setLongitude(Retailer.getLongitude());
	    this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(Retailer.getLatitude(),Retailer.getLongitude())));		
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
