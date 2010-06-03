package com.rmsi.lim.gstcloud.client;

import java.util.List;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.shared.Landmarks;


public interface LandmarksServiceAsync 
{
	void loadStation(Landmarks station, AsyncCallback<String> callback);
    void displayStation(AsyncCallback<List<Landmarks>> callback);
    void searchByAddress(String text,AsyncCallback<List<Landmarks>> callback);
    void loadKML(String filename, AsyncCallback<String> asyncCallback);
}
