package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;


import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;


public interface TowerServiceAsync 
{
	void loadStation(TowerDTO station, AsyncCallback<String> callback);
    void getTowers(AsyncCallback<List<TowerDTO>> callback);
    void searchTowerByName(String text,AsyncCallback<List<TowerDTO>> callback);
    void displayTowersWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<TowerDTO>> callback);   
}
