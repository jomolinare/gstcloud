package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FeatureServiceAsync {

	void getAllFeatures(AsyncCallback<List<Object>> callback);
}
