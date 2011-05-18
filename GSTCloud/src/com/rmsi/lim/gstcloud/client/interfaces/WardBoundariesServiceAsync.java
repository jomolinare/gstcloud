package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;

public interface WardBoundariesServiceAsync {

	void loadWardBoundary(WardBoundariesDTO wardBoundary, AsyncCallback<String> callback);
    void getWardBoundaries(AsyncCallback<List<WardBoundariesDTO>> callback);
    void searchWardBoundaryByName(String text,AsyncCallback<List<WardBoundariesDTO>> callback);
    void displayWardBoundariesWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<WardBoundariesDTO>> callback);   
    void getIntersectionData(String latLng,AsyncCallback<List<WardBoundariesDTO>> callback);
    void getData(AsyncCallback<List<WardBoundariesDTO>> callback);
    void getIntersectedPolygonWithPoint(String latLng,AsyncCallback<List<WardBoundariesDTO>> callback);
}
