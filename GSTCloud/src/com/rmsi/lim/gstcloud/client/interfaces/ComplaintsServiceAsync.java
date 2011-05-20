package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;

public interface ComplaintsServiceAsync {

	void loadStation(ComplaintDTO complaint,AsyncCallback<String> callback);
	void getOutages(AsyncCallback<List<ComplaintDTO>> callback);
}
