package com.rmsi.lim.gstcloud.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.LayerService;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerItem;
import com.rmsi.lim.gstcloud.shared.LayerTree;
import com.rmsi.lim.gstcloud.shared.States;

public class LayerServiceImpl extends RemoteServiceServlet implements LayerService
{
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public List<Layer> getLayers() 
	{
		String query = "SELECT FROM com.rmsi.lim.gstcloud.shared.Layer ";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		List<Layer>  tempList = new ArrayList<Layer>();
	 	int rowCount=layerlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        tempList.add(layerlist.get(ctr));
	    }
		return tempList;
		
	}

//	public LayerTree populateLayer()
//	{
//		List<Layer> result = getLayers();
//		LayerTree lm = new LayerTree();
//		int rowCount = result.size();
// 		for (int row = 0; row < rowCount; row ++) 
// 		{
// 			CheckBox checkbox = new CheckBox(result.get(row).getName());
//// 			LayerItem item = new LayerItem (checkbox);
// 			LayerTree lm = new LayerTree(checkbox);
//// 			lm.addLayer(item);
// 		
// 		}
//		return lm;
//	}
	
	@Override
	public String loadLayer(Layer layer) 
	{
		try{
			pm.makePersistent(layer);
		   } finally
		   	{
			}
		return null;
	}

}
