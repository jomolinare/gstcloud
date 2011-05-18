package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;

@RemoteServiceRelativePath("wardBoundary")
public interface WardBoundariesService extends RemoteService{

	 String loadWardBoundary(WardBoundariesDTO wardBoundary);
	 List<WardBoundariesDTO> getWardBoundaries();
	 List<WardBoundariesDTO> searchWardBoundaryByName(String text);
	 List<WardBoundariesDTO> displayWardBoundariesWithinDistance(Double latitude,Double longitude,Double distance);
	 List<WardBoundariesDTO> getIntersectionData(String latLng);
	 List<WardBoundariesDTO> getData();
	 List<WardBoundariesDTO> getIntersectedPolygonWithPoint(String latLng);
}
