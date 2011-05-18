package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PointFeatureServiceAsync extends FeatureServiceAsync{

	void storePointFeature(String layerName,Double latitude,Double longitude,String[] attributes,AsyncCallback<String> callback );
	void searchFeatureByName(String layerName,String text,AsyncCallback<List<Object>> callback);
	void searchFeatureByDistance(String layerName,Double latitude,Double longitude,Double distance,AsyncCallback<List<Object>> callback);
}
