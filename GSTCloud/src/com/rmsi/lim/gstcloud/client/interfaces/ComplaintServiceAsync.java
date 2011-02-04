package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;


import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;


public interface ComplaintServiceAsync 
{
	void loadStation(ComplaintDTO station, AsyncCallback<String> callback);
    void getComplaints(AsyncCallback<List<ComplaintDTO>> callback);
    void searchComplaintsBySubtype(String text,AsyncCallback<List<ComplaintDTO>> callback);
    void searchComplaintsBySubSubtype(String text,AsyncCallback<List<ComplaintDTO>> callback);
    void displayComplaintsWithinDistance(Double latitude,Double longitude,Double distance,AsyncCallback<List<ComplaintDTO>> callback);   
}