package com.rmsi.lim.gstcloud.client.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ComplaintsDTO implements IsSerializable {

    private Integer content;
	
	private String outageType;

	private Double latitude;

	private Double longitude;

	
	public ComplaintsDTO(){
		
	}
	
    public ComplaintsDTO(String outageType,Integer content,Double latitude,Double longitude){
		this.outageType = outageType;
		this.content = content;
		this.latitude=latitude;
        this.longitude=longitude;
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
