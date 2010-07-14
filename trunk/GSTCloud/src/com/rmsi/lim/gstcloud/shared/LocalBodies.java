package com.rmsi.lim.gstcloud.shared;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class LocalBodies implements IsSerializable
{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long localBodyId;
	
	@Persistent
	private String districtName;
	
    @Persistent
    private String localBodyType;

    @Persistent
    private String localBodyName;
    
    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;

    public LocalBodies()
    {
    	
    }
    
    public LocalBodies(String districtName, String localBodyType,String localBodyName,Double latitude, Double longitude) 
    {
    	this.districtName = districtName;
    	this.localBodyType = localBodyType;
        this.localBodyName = localBodyName;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getDistrictName() 
    {
        return districtName;
    }

    public String getLocalBodyType() {
        return localBodyType;
    }
    
    public String getLocalBodyName() {
        return localBodyName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    
    
    public void setLocalBodyId(Long localBodyId) {
        this.localBodyId = localBodyId;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    
    public void setLocalBodyType(String localBodyType) {
        this.localBodyType = localBodyType;
    }
    
    public void setLocalBodyName(String localBodyName) {
        this.localBodyName = localBodyName;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
