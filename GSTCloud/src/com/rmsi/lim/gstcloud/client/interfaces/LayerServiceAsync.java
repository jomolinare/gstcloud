package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.view.LayerTree;

public interface LayerServiceAsync 
{
	void loadLayer(Layer layer, AsyncCallback<String> callback);
	void getLayers(AsyncCallback<List<Layer>>callback);
//	void populateLayer(AsyncCallback<LayerTree> callback);
}
