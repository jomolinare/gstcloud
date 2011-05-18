package com.rmsi.lim.gstcloud.client.interfaces;


import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.NLRTO;


@RemoteServiceRelativePath("NLR")
public interface NewLayerService extends RemoteService {
	 String loadStation(NLRTO station);
	    List<NLRTO> getNLRs();
	    List<NLRTO> searchNLRByName(String text);
	    List<NLRTO> displayNLRsWithinDistance(Double latitude,Double longitude,Double distance);
}
