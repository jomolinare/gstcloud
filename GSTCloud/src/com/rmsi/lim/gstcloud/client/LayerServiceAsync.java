package com.rmsi.lim.gstcloud.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerTree;

public interface LayerServiceAsync 
{
	void loadLayer(Layer layer, AsyncCallback<String> callback);
	void getLayers(AsyncCallback<List<Layer>>callback);
//	void populateLayer(AsyncCallback<LayerTree> callback);
}
