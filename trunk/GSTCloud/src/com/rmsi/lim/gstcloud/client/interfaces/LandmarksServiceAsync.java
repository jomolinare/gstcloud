package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;


import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;


public interface LandmarksServiceAsync 
{
	void loadStation(LandmarkDTO station, AsyncCallback<String> callback);
    void getLandMarks(AsyncCallback<List<LandmarkDTO>> callback);
    void searchLandmarkByName(String text,AsyncCallback<List<LandmarkDTO>> callback);
    void displayLandmarksWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<LandmarkDTO>> callback);
    void loadKML(String filename, AsyncCallback<String> asyncCallback);
}
