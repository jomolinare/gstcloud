package com.rmsi.lim.gstcloud.client;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import com.rmsi.lim.gstcloud.shared.States;
import com.rmsi.lim.gstcloud.shared.Districts;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loadLocal")
public interface LocalBodiesService extends RemoteService
{
	String loadStates(States state);
	String loadDistricts(Districts district);
	String loadLocalBody(LocalBodies localBody);
	
	States getStateByName(String text);
	Districts getDistrictByName(String text);
	LocalBodies getLocalBodyByName(String localBodyName);
	

	List<States> getStates();
	
//	List<Districts> getDistrictsByStateId(Long stateId);
	List<Districts> getDistrictsByStateName(String stateName);
	List<LocalBodies> getLocalBodiesByDistrictName (String districtName);
}

