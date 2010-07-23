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
import com.rmsi.lim.gstcloud.client.model.TowerDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class Tower extends Geohashed implements LocationCapable,IsSerializable
{
  @Persistent
    private String name;

  @Persistent
  private String category;

  @Persistent
    private String owner;
  
  @Persistent
  private String status;
  
 //Stores the coverage of a tower in wkt form
  @Persistent 
  private String coverage;
  
  //Stores the height of a tower in meters
  @Persistent
  private Double height;
    
  @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;    
      
    public Tower(String category,String name, String owner, String status, String coverage, Double height, Double latitude, Double longitude, List<String> geoCells) {
        this.category=category;
    	this.name = name;
        this.owner = owner;
        this.status = status;
        this.coverage = coverage;
        this.height = height;
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

	public Tower(TowerDTO tower) {
		this.setCategory(tower.getCategory());
		this.setName(tower.getName());
		this.setOwner(tower.getOwner());
		this.setStatus(tower.getStatus());
		this.setCoverage(tower.getCoverage());
		this.setHeight(tower.getHeight());	
		this.setLatitude(tower.getLatitude());
		this.setLongitude(tower.getLongitude());
	    this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(tower.getLatitude(),tower.getLongitude())));		
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
