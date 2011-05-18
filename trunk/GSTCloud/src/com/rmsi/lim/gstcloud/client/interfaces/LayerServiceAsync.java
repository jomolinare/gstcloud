package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.view.LayerTree;

public interface LayerServiceAsync 
{
	void loadLayer(Layer layer, AsyncCallback<String> callback);
	void getLayers(AsyncCallback<List<Layer>>callback);
//	void populateLayer(AsyncCallback<LayerTree> callback);
	void refreshMap(String sti,AsyncCallback<String> callback);
	void IsLayerPolygon(String selectedLayer,AsyncCallback<Boolean> callback);
	void getFilterColumns(String layerName,AsyncCallback<String[]> callback);
	void getColumns(String layerName,AsyncCallback<String[]> callback);
	void getColumnDisplayNames(String layerName,AsyncCallback<String[]> callback);
	void getFilterColumnTypes(String layerName,AsyncCallback<String[]> callback);
	void getColumnTypes(String layerName,AsyncCallback<String[]> callback);
}
