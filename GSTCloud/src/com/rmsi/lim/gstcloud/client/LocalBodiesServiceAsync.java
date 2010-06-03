package com.rmsi.lim.gstcloud.client;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.States;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocalBodiesServiceAsync 
{
	void loadStates(States state, AsyncCallback<String> callback);
	void loadDistricts(Districts district, AsyncCallback<String> callback);
	void getDistricts(String StateName,AsyncCallback<List<Districts>> callback);
	void getStates(AsyncCallback<List<States>> callback);
	void displayState(String text,AsyncCallback<HashMap> callback);
	void displayDistrict(String text,AsyncCallback<HashMap> callback);
}
