package com.rmsi.lim.gstcloud.server.servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;


import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.model.Tower;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.TowerService;

import com.rmsi.lim.gstcloud.client.model.TowerDTO;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;



@SuppressWarnings("serial")
public class TowerServiceImpl extends RemoteServiceServlet implements TowerService 
{

	
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Tower WHERE category == 'TOWER'";
	@Override
	public List<TowerDTO> getTowers() 
	{
		// TODO Auto-generated method stub
		
		List<Tower> Towerlist = (List<Tower>) pm.newQuery(query).execute();
		
		List<TowerDTO>  tempList = new ArrayList<TowerDTO>();
	 	 
	    int rowCount=Towerlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        Tower temp =Towerlist.get(ctr);
	    	tempList.add(new TowerDTO(temp.getCategory(),temp.getName(),temp.getOwner(),temp.getStatus(),temp.getCoverage(),temp.getHeight(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	@Override
	public List<TowerDTO> displayTowersWithinDistance(Double latitude,Double longitude, Double distance) {
		
		
		    com.beoui.geocell.model.Point centerPoint = new com.beoui.geocell.model.Point(latitude, longitude);
	        
	        List<Object> params = new ArrayList<Object>();
	        params.add("TOWER");
	        //GeocellQuery baseQuery = new GeocellQuery("lastName == lastNameParam", "String lastNameParam", params);
            GeocellQuery baseQuery = new GeocellQuery("category== categoryParam","String categoryParam", params);
	        List<Tower> objects = null;
	        try {
	            objects = GeocellManager.proximityFetch(centerPoint, GSTCloudServerConstants.maxSearchResults, distance, Tower.class, baseQuery, pm);
	            //Assert.assertTrue(objects.size() > 0);
	        } catch (Exception e) {
	            
	        	e.printStackTrace();
	        	
	        }
		
		
		//List<Tower> Towerlist = (List<Towers>) pm.newQuery(query).execute();
		
		List<TowerDTO>  tempList = new ArrayList<TowerDTO>();
	 	
	    int rowCount=objects.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	Tower temp =objects.get(ctr);
	    	tempList.add(new TowerDTO(temp.getCategory(),temp.getName(),temp.getOwner(),temp.getStatus(),temp.getCoverage(),temp.getHeight(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	public List<TowerDTO> searchTowerByName(String text)
	{
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.Towers WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Tower WHERE name =='" + text +"'" ;
	
		List<Tower> Towerlist = (List<Tower>) pm.newQuery(querySearch).execute();
//		List<Towers> Towerlist = (List<Towers>) pm.newQuery(Towers.class, "this.placename =="+ "'" + text +"'").execute();
		List<TowerDTO> tempList= new ArrayList<TowerDTO>();
		int rowCount=Towerlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Tower temp =Towerlist.get(ctr);
		    tempList.add(new TowerDTO(temp.getCategory(),temp.getName(),temp.getOwner(),temp.getStatus(),temp.getCoverage(),temp.getHeight(),temp.getLatitude(),temp.getLongitude(),true));
	    }

		return tempList; 
	}

	@Override
	public String loadStation(TowerDTO station) 
	{
		try{
			Tower Tower= new Tower(station);
			//pm.makePersistent(station);
			pm.makePersistent(Tower);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}
}