package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.RetailerDTO;


@RemoteServiceRelativePath("retailer")
public interface RetailerService extends RemoteService 
	{

	    String loadStation(RetailerDTO station);
	    List<RetailerDTO> getRetailers();
	    List<RetailerDTO> searchRetailerByName(String text);
	    List<RetailerDTO> displayRetailersWithinDistance(Double latitude,Double longitude,Double distance);
	}
