 package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.view.LayerTree;

import java.util.List; 	
 
@RemoteServiceRelativePath("loadLayer")
 public interface LayerService extends RemoteService 
 	{
  		String loadLayer(Layer layer);
 		List<Layer> getLayers();
//  		LayerTree populateLayer();
 		String refreshMap(String sti);
 		Boolean IsLayerPolygon(String selectedLayer);
 		String[] getFilterColumns(String layerName);
 		String[] getColumns(String layerName);
 		String[] getColumnDisplayNames(String layerName);
 		String[] getFilterColumnTypes(String layerName);
 		String[] getColumnTypes(String layerName);
 	}


