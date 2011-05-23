package com.rmsi.lim.gstcloud.client.model;



import com.google.gwt.user.client.rpc.IsSerializable;

public class WardBoundariesDTO implements IsSerializable{

	
    private String category;
	
    private String placeName;
	
    private Double latitude;
    
    private Double longitude;
    
    private String pcord[];
    
    private String wardName;

    private String wardNo;
    
    private Double totalPop;
    
    private String cityName;
    
    private String streetName;
    
    private String intrctData; 
    
    private Double populationPerIntersection;
    
    public WardBoundariesDTO()
    {
    	
    }
    
    public WardBoundariesDTO(String intrctData,Double populationPerIntersection,String wardName)
    {
    	this.intrctData = intrctData;
    	this.populationPerIntersection = populationPerIntersection;
    	this.wardName = wardName;
    }
    
    public WardBoundariesDTO(String category,String placeName,Double latitude,Double longitude,String pcord[],String wardName,
    		String wardNo,Double totalPop,String cityName,String streetName)
    {
    	this.category = category;
    	this.placeName = placeName;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.pcord = pcord;
    	this.wardName = wardName;
    	this.wardNo = wardNo;
    	this.totalPop = totalPop;
    	this.cityName = cityName;
    	this.streetName = streetName;
    	
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
    	this.wardNo = wardNum;
    }
    
    public void setTotalPop(Double totalPop){
    	this.totalPop = totalPop;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public void setIntrctData(String intrctData)
    {
    	this.intrctData = intrctData;
    }
    
    public void setPopulationPerIntersection(Double populationPerIntersection ){
    	this.populationPerIntersection = populationPerIntersection ;
    }
   
    
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
    	return wardNo;
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
    
    public String getIntrctData()
    {
    	return intrctData;
    }
    
    public Double getPopulationPerIntersection() {
        return populationPerIntersection;
    }
    
}
