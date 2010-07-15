package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.State;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpatialBodiesServiceAsync 
{
	void loadStates(State state, AsyncCallback<String> callback);
	void loadDistricts(District district, AsyncCallback<String> callback);
	void loadLocalBody(LocalBody localBody, AsyncCallback<String> callback);
	
	void getStates(AsyncCallback<List<State>> callback);
	
	void getStateByName(String text,AsyncCallback<State> callback);
	void getDistrictByName(String text,AsyncCallback<District> callback);
	void getLocalBodyByName(String localBodyName,AsyncCallback<LocalBody> callback );
	
//	void getDistrictsByStateId (Long stateId,AsyncCallback<List<Districts>> callback);
	void getDistrictsByStateName(String stateName,AsyncCallback<List<District>> callback);
	void getLocalBodiesByDistrictName(String districtNamee,AsyncCallback<List<LocalBody>> callback);
}
