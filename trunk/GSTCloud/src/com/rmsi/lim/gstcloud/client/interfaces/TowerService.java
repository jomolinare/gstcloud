package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;


@RemoteServiceRelativePath("tower")
public interface TowerService extends RemoteService 
	{

	    String loadStation(TowerDTO station);
	    List<TowerDTO> getTowers();
	    List<TowerDTO> searchTowerByName(String text);
	    List<TowerDTO> displayTowersWithinDistance(Double latitude,Double longitude,Double distance);
	    String loadKML(String fileIn);
	}
