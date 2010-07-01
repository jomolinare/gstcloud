 package com.rmsi.lim.gstcloud.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerTree;

import java.util.List; 	
 
@RemoteServiceRelativePath("loadLayer")
 public interface LayerService extends RemoteService 
 	{
  		String loadLayer(Layer layer);
 		List<Layer> getLayers();
//  		LayerTree populateLayer();
 	}


