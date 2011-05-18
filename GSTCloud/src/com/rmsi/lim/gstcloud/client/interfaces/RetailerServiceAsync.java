package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.RetailerDTO;


public interface RetailerServiceAsync 
{
	void loadStation(RetailerDTO station, AsyncCallback<String> callback);
    void getRetailers(AsyncCallback<List<RetailerDTO>> callback);
    void searchRetailerByName(String text,AsyncCallback<List<RetailerDTO>> callback);
    void displayRetailersWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<RetailerDTO>> callback);   
}
