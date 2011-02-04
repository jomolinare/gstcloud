package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
// import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;


@RemoteServiceRelativePath("Complaint")
public interface ComplaintService extends RemoteService 
	{

	    String loadStation(ComplaintDTO station);
	    List<ComplaintDTO> getComplaints();
	    List<ComplaintDTO> searchComplaintsBySubtype(String text);
	    List<ComplaintDTO> searchComplaintsBySubSubtype(String text);
	    List<ComplaintDTO> displayComplaintsWithinDistance(Double latitude,Double longitude,Double distance);
		//List<ComplaintDTO> getComplains();
	}
