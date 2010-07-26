package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;


import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;


public interface CSCServiceAsync 
{
	void loadStation(CSCDTO station, AsyncCallback<String> callback);
    void getCSCs(AsyncCallback<List<CSCDTO>> callback);
    void searchCSCByName(String text,AsyncCallback<List<CSCDTO>> callback);
    void displayCSCsWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<CSCDTO>> callback);
}
