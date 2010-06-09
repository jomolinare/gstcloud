package com.rmsi.lim.gstcloud.shared;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class Districts implements IsSerializable
{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long districtId;
	
	//@ForeignKey
	@Persistent
	private Long stateId;
	
    @Persistent
    private String districtName;

    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;

    public Districts()
    {
    	
    }
    
    public Districts(Long stateId,String districtName,Double latitude, Double longitude) 
    {
        this.stateId = stateId;
        this.districtName = districtName;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Long getDistrictId() 
    {
        return districtId;
    }
    
    public long getStateId() 
    {
        return stateId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    public void setDistrictId(Long districtId) 
    {
    	this.districtId = districtId;
    }
    
    public void setStateId(Long stateId) 
    {
    	this.stateId = stateId;
    }

    public void setDistrictName(String stateName) {
        this.districtName = stateName;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
