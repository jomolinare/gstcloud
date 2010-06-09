package com.rmsi.lim.gstcloud.shared;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.rmsi.lim.gstcloud.shared.States;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class States implements IsSerializable
{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY )//,mappedBy = "stateId")
    private Long stateId;

    @Persistent
    private String stateName;

    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private int zoomLevel;

    public States()
    {
    	
    }
    
    public States(String stateName,Double latitude, Double longitude ,int zoomLevel) 
    {
    	
    	this.stateName = stateName;
        this.latitude=latitude;
        this.longitude=longitude;
        this.zoomLevel = zoomLevel;
    }


	public long getStateId() 
    {
        return stateId;
    }
	
	 public int getZoomLevel() 
	    {
	        return zoomLevel;
	    }

    public String getStateName() {
        return stateName;
    }

    public Double getLatitude() {
        return latitude;
    }	

    public Double getLongitude() {
        return longitude;
    }
    

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
