package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.State;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loadLocal")
public interface SpatialBodiesService extends RemoteService
{
	String loadStates(State state);
	String loadDistricts(District district);
	String loadLocalBody(LocalBody localBody);
	
	State getStateByName(String text);
	District getDistrictByName(String text);
	LocalBody getLocalBodyByName(String localBodyName);
	

	List<State> getStates();
	
	List<District> getDistrictsByStateName(String stateName);
	List<LocalBody> getLocalBodiesByDistrictName (String districtName);
}

