package com.rmsi.lim.gstcloud.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.shared.Landmarks;


@RemoteServiceRelativePath("load")
public interface LandmarksService extends RemoteService 
	{

	    String loadStation(Landmarks station);
	    List<Landmarks> displayStation();
	    List<Landmarks> searchStation(String text);
	    String loadKML(String fileIn);
	}
