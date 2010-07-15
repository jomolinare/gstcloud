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
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class Landmark extends Geohashed implements LocationCapable,IsSerializable
{
  @Persistent
    private String category;

    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private String placeName;

    
    
    public Landmark(String category,Double latitude, Double longitude, String placeName,List<String> geoCells) {
        this.category = category;
        this.latitude=latitude;
        this.longitude=longitude;
        this.placeName = placeName;
        this.setGeoCells(geoCells);
    }

//    public long getid() {
//        return id;
//    }

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

	public Landmark(LandmarkDTO station) {
		this.setCategory(station.getPlaceName());
		this.setLatitude(station.getLatitude());
		this.setLongitude(station.getLongitude());
		 this.setPlaceName(station.getPlaceName());
	     this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(station.getLatitude(),station.getLongitude())));
		// TODO Auto-generated constructor stub
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
