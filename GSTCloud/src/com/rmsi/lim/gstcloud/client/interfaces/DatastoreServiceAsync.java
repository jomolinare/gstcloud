package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatastoreServiceAsync {

	void loadData(String layerName,String[] arrTextBoxVal,AsyncCallback<String> callback);
}
