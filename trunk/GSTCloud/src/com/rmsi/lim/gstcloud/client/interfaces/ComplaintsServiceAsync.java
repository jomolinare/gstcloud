package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.ComplaintsDTO;

public interface ComplaintsServiceAsync {

	void loadStation(ComplaintsDTO complaint,AsyncCallback<String> callback);
	void getOutages(AsyncCallback<List<ComplaintsDTO>> callback);
}
