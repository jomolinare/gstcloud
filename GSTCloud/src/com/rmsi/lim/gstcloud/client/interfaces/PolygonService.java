package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.maps.client.geom.LatLng;
import com.rmsi.lim.gstcloud.client.model.NLRTO;
import com.rmsi.lim.gstcloud.client.model.PlTO;

@RemoteServiceRelativePath("POL")

public interface PolygonService extends RemoteService {

	List<PlTO> getData();
	List<PlTO> getIntersectionData(String latLng);
}
