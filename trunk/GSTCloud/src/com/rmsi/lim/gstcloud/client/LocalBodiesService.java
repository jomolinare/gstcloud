package com.rmsi.lim.gstcloud.client;

import java.util.HashMap;
import java.util.List;

import com.rmsi.lim.gstcloud.shared.Landmarks;
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
	List<Districts> getDistricts(String stateName);
	List<States> getStates();
	HashMap displayState(String text);
	HashMap displayDistrict(String text);
}

