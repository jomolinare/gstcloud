package com.rmsi.lim.gstcloud.shared;

import com.google.gwt.user.client.rpc.IsSerializable; 

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class Landmarks  implements IsSerializable{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String category;

    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private String placeName;

    public Landmarks()
    {
    	
    }
    
    public Landmarks(String category,Double latitude, Double longitude, String placeName) {
        this.category = category;
        this.latitude=latitude;
        this.longitude=longitude;
        this.placeName = placeName;
    }

    public long getid() {
        return id;
    }

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
}
