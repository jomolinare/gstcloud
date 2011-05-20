package com.rmsi.lim.gstcloud.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.rmsi.lim.gstcloud.client.model.ComplaintsDTO;
import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class Complaints implements IsSerializable{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
    @Persistent
    private String outageType;
  
	@Persistent
	private Integer content;

	@Persistent
	private Double latitude;

	@Persistent
	private Double longitude;

	public Complaints(){
		
	}
	
    public Complaints(String outageType,Integer content,Double latitude,Double longitude){
		this.outageType = outageType;
		this.content = content;
		this.latitude=latitude;
        this.longitude=longitude;
	}
    
    public Complaints(ComplaintsDTO complaint){
		this.setOutageType(complaint.getOutageType());
		this.setContent(complaint.getContent());
		this.setLatitude(complaint.getLatitude());
		this.setLongitude(complaint.getLongitude());
	}
    
    public String getOutageType(){
    	 return outageType;
    }

    public Integer getContent(){
   	 return content;
    }
    
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    
    
    public void setOutageType(String outageType){
    	this.outageType = outageType;
    }
    
    public void setContent(Integer content){
    	this.content = content;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
