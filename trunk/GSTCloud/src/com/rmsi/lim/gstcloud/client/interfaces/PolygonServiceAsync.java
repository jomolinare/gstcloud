package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.PlTO;

public interface PolygonServiceAsync {
	void getData(AsyncCallback<List<PlTO>> Callback);
	void getIntersectionData(String latLng,AsyncCallback<List<PlTO>> callback);
}
