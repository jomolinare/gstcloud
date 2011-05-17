package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;

@RemoteServiceRelativePath("complaint")
public interface ComplaintService extends RemoteService{

	String loadStation(ComplaintDTO complaint);
	List<ComplaintDTO> getOutages();
}
