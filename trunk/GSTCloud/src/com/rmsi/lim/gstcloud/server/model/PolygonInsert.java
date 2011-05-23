package com.rmsi.lim.gstcloud.server.model;


import java.util.Collection;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.LocationCapable;
import com.beoui.geocell.model.Point;
import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.rmsi.lim.gstcloud.client.model.NLRTO;
import com.rmsi.lim.gstcloud.client.model.PlTO;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;



@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(customStrategy = "complete-table")

public class PolygonInsert  extends Geohashed implements  IsSerializable {
	
	@Persistent
    private String placeName;
	
	@Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private String pcord[];
    
    //@Persistent
   // private Blob blob;
  //  @Persistent
   // private java.util.ArrayList<List> wktObj;
    
    public PolygonInsert()
    {
    	
    }
    public PolygonInsert(String placeName,Double latitude,Double longitude,String pcord[])
    {
    	this.placeName = placeName;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.pcord = pcord;
    	
    	
    }
    
    public PolygonInsert(PlTO station) {
		//this.setCategory(station.getCategory());
		this.setLatitude(station.getLatitude());
		this.setLongitude(station.getLongitude());
		 this.setPlaceName(station.getPlaceName());
		 this.setPolygonObject(station.getPolygonObject());
		 
	   //  this.setGeoCells(GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(station.getLatitude(),station.getLongitude())));
		// TODO Auto-generated constructor stub
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
    
    public String getPlaceName() {
        return placeName;
    }
    
    public String[] getPolygonObject()
    {
    	return pcord;
    }

   
    public String getKeyString() {
        return Long.valueOf(this.getObjectId()).toString();
    }
	/*public Blob getBlob()
	{
		return blob;
	}*/
}

