 package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.view.LayerTree;

import java.util.List; 	
 
@RemoteServiceRelativePath("loadLayer")
 public interface LayerService extends RemoteService 
 	{
  		String loadLayer(Layer layer);
 		List<Layer> getLayers();
//  		LayerTree populateLayer();
 	}


