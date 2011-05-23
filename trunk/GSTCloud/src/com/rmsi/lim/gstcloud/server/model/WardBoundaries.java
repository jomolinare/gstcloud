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
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;


@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")
public class WardBoundaries extends Geohashed implements LocationCapable, IsSerializable{
	
	@Persistent
    private String category;
	
	@Persistent
    private String placeName;
	
	@Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private String pcord[];
    
    @Persistent
    private String wardName;

    @Persistent
    private String wardNum;
    
    @Persistent
    private Double totalPop;
    
    @Persistent
    private String cityName;
    
    @Persistent
    private String streetName;
    
    public WardBoundaries()
    {
    	
    }
    public WardBoundaries(String category,String placeName,Double latitude,Double longitude,String pcord[],String wardName,
    		String wardNo,Double totalPop,String cityName,String streetName,List<String> geoCells)
    {
    	this.category = category;
    	this.placeName = placeName;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.pcord = pcord;
    	this.wardName = wardName;
    	this.wardNum = wardNo;
    	this.totalPop = totalPop;
    	this.cityName = cityName;
    	this.streetName = streetName;
    	this.setGeoCells(geoCells);
    }
    
    public WardBoundaries(WardBoundariesDTO wardBoundary) {
		this.setCategory(wardBoundary.getCategory());
		this.setLatitude(wardBoundary.getLatitude());
		this.setLongitude(wardBoundary.getLongitude());
		 this.setPlaceName(wardBoundary.getPlaceName());
		 this.setPolygonObject(wardBoundary.getPolygonObject());
		 this.setCityName(wardBoundary.getCityName());
		 this.setStreetName(wardBoundary.getStreetName());
		 this.setTotalPop(wardBoundary.getTotalPop());
		 this.setWardName(wardBoundary.getWardName());
		 this.setWardNum(wardBoundary.getWardNum());
	     this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(wardBoundary.getLatitude(),wardBoundary.getLongitude())));
		// TODO Auto-generated constructor stub
	}
   
public List<String> getGeocells() {
		
		return this.getGeoCells();
	}
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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
    
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    
    public void setWardNum(String wardNum){
    	this.wardNum = wardNum;
    }
    
    public void setTotalPop(Double totalPop){
    	this.totalPop = totalPop;
    }
    
    public void setCityName(String citydName) {
        this.cityName = citydName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
   /* public void setBlob(Blob blob)
    {
    	this.blob = blob;
    }*/
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public String[] getPolygonObject()
    {
    	return pcord;
    }

    public String getWardName() {
        return wardName;
    }
    
    public String getWardNum(){
    	return wardNum;
    }
    
    public Double getTotalPop() {
        return totalPop;
    }

    public String getCityName() {
        return cityName;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public String getKeyString() {
        return Long.valueOf(this.getObjectId()).toString();
    }

    public Point getLocation() {
        return new Point(this.getLatitude(),this.getLongitude());
    }
    
}
