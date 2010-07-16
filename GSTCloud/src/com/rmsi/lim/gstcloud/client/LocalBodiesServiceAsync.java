package com.rmsi.lim.gstcloud.client;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import com.rmsi.lim.gstcloud.shared.States;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocalBodiesServiceAsync 
{
	void loadStates(States state, AsyncCallback<String> callback);
	void loadDistricts(Districts district, AsyncCallback<String> callback);
	void loadLocalBody(LocalBodies localBody, AsyncCallback<String> callback);
	
	void getStates(AsyncCallback<List<States>> callback);
	
	void getStateByName(String text,AsyncCallback<States> callback);
	void getDistrictByName(String text,AsyncCallback<Districts> callback);
	void getLocalBodyByName(String localBodyName,AsyncCallback<LocalBodies> callback );
	
//	void getDistrictsByStateId (Long stateId,AsyncCallback<List<Districts>> callback);
	void getDistrictsByStateName(String stateName,AsyncCallback<List<Districts>> callback);
	void getLocalBodiesByDistrictName(String districtNamee,AsyncCallback<List<LocalBodies>> callback);
}