package com.rmsi.lim.gstcloud.client.interfaces;


import java.util.List;


import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.NLRTO;

public interface NewLayerServiceAsync {
	void loadStation(NLRTO station, AsyncCallback<String> callback);
    void getNLRs(AsyncCallback<List<NLRTO>> callback);
    void searchNLRByName(String text,AsyncCallback<List<NLRTO>> callback);
    void displayNLRsWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<NLRTO>> callback);
}
