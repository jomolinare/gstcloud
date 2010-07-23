package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;


@RemoteServiceRelativePath("landmarks")
public interface LandmarksService extends RemoteService 
	{

	    String loadStation(LandmarkDTO station);
	    List<LandmarkDTO> getLandMarks();
	    List<LandmarkDTO> searchLandmarkByName(String text);
	    List<LandmarkDTO> displayLandmarksWithinDistance(Double latitude,Double longitude,Double distance);
	    String loadKML(String fileIn);
	}
