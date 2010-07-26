package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;


@RemoteServiceRelativePath("CSC")
public interface CSCService extends RemoteService 
	{

	    String loadStation(CSCDTO station);
	    List<CSCDTO> getCSCs();
	    List<CSCDTO> searchCSCByName(String text);
	    List<CSCDTO> displayCSCsWithinDistance(Double latitude,Double longitude,Double distance);
	}
