package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataStore")
public interface DatastoreService extends RemoteService{

	String loadData(String layerName,String[] arrAttributes);
}
