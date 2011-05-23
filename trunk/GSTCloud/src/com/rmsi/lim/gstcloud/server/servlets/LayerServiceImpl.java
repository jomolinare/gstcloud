package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.LayerService;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.State;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.view.LayerItem;
import com.rmsi.lim.gstcloud.client.view.LayerTree;
import com.rmsi.lim.gstcloud.server.utilities.PMF;

public class LayerServiceImpl extends RemoteServiceServlet implements LayerService
{
	//TableModelServiceImpl objTableMo
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public List<Layer> getLayers() 
	{
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer ";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		List<Layer>  tempList = new ArrayList<Layer>();
	 	int rowCount=layerlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        tempList.add(layerlist.get(ctr));
	    }
		return tempList;
		
	}

	@Override
	public String[] getColumns(String layerName) {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ layerName+"'";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		String[] arrColumn = null;
		
		int rowCount=layerlist.size();
	 	for (int ctr=0;ctr<rowCount;ctr++)
	    {
	 		arrColumn = layerlist.get(ctr).getColumnNames();
	    }
	 	
	 	
		return arrColumn;
	}

	@Override
	public String[] getColumnDisplayNames(String layerName) {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ layerName+"'";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		String[] arrColumn = null;
		
		int rowCount=layerlist.size();
	 	for (int ctr=0;ctr<rowCount;ctr++)
	    {
	 		arrColumn = layerlist.get(ctr).getColumnDisplayNames();
	    }
	 	
	 	
		return arrColumn;
	}
	
	@Override
	public String[] getFilterColumns(String layerName) {
		
		System.out.println("Layer Name for Filter is :"+layerName);
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ layerName+"'";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		String[] arrFilterColumn = null;
	 	int rowCount=layerlist.size();
	 	for (int ctr=0;ctr<rowCount;ctr++)
	    {
	 		arrFilterColumn = layerlist.get(ctr).getFilterColumnNames();
	 		
	    }
		
		return arrFilterColumn;
	}
	
	@Override
	public String[] getColumnTypes(String layerName) {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ layerName+"'";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		String[] arrColumnTypes = null;
		
		int rowCount=layerlist.size();
	 	for (int ctr=0;ctr<rowCount;ctr++)
	    {
	 		arrColumnTypes = layerlist.get(ctr).getColumnTypes();
	    }
	 	
	 	
		return arrColumnTypes;
	}

	@Override
	public String[] getFilterColumnTypes(String layerName) {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ layerName + "'";
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
		String[] arrFilterColumnTypes = null;
	 	int rowCount=layerlist.size();
	 	for (int ctr=0;ctr<rowCount;ctr++)
	    {
	 		arrFilterColumnTypes = layerlist.get(ctr).getFilterColumnTypes();
	    }
		
		return arrFilterColumnTypes;
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
		return "Layer"+layer.getLayerName()+" loaded";
	}
	
	public String refreshMap(String sti){
		HttpSession httpSession = this.getThreadLocalRequest().getSession();
		httpSession.setAttribute("selectedlayer",sti);	
		System.out.println("Value of the sessionLayer is :"+httpSession.getAttribute("selectedlayer"));
		
		return "Selected Layer set to "+sti;
	}

	@Override
	public Boolean IsLayerPolygon(String selectedLayer) {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.Layer where layerName == '"+ selectedLayer + "'";
		
		Boolean layerPolygon = false;
		List<Layer> layerlist = (List<Layer>) pm.newQuery(query).execute();
	 	int rowCount=layerlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        if(layerlist.get(ctr).getType() == "Polygon"){
	        	layerPolygon = true;
	        }
	    }
	    
		return layerPolygon;
	}

	

}
