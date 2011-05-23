package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.ComplaintsDTO;

@RemoteServiceRelativePath("complaint")
public interface ComplaintsService extends RemoteService{

	String loadStation(ComplaintsDTO complaint);
	List<ComplaintsDTO> getOutages();
}
