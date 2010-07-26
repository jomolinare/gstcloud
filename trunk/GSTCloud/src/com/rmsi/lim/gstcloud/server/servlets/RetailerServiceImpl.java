package com.rmsi.lim.gstcloud.server.servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;


import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.model.Retailer;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.RetailerService;

import com.rmsi.lim.gstcloud.client.model.RetailerDTO;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;



@SuppressWarnings("serial")
public class RetailerServiceImpl extends RemoteServiceServlet implements RetailerService 
{

	
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Retailer WHERE category == 'RETAILER'";
	@Override
	public List<RetailerDTO> getRetailers() 
	{
		// TODO Auto-generated method stub
		
		List<Retailer> Retailerlist = (List<Retailer>) pm.newQuery(query).execute();
		
		List<RetailerDTO>  tempList = new ArrayList<RetailerDTO>();
	 	
	    int rowCount=Retailerlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        Retailer temp =Retailerlist.get(ctr);
	    	tempList.add(new RetailerDTO(temp.getCategory(),temp.getName(),temp.getAddress(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	@Override
	public List<RetailerDTO> displayRetailersWithinDistance(Double latitude,Double longitude, Double distance) {
		
		
		    com.beoui.geocell.model.Point centerPoint = new com.beoui.geocell.model.Point(latitude, longitude);
	        
	        List<Object> params = new ArrayList<Object>();
	        params.add("RETAILER");
	        //GeocellQuery baseQuery = new GeocellQuery("lastName == lastNameParam", "String lastNameParam", params);
            GeocellQuery baseQuery = new GeocellQuery("category== categoryParam","String categoryParam", params);
	        List<Retailer> objects = null;
	        try {
	            objects = GeocellManager.proximityFetch(centerPoint, GSTCloudServerConstants.maxSearchResults, distance, Retailer.class, baseQuery, pm);
	            //Assert.assertTrue(objects.size() > 0);
	        } catch (Exception e) {
	            
	        	e.printStackTrace();
	        	
	        }
		
		
		//List<Retailer> Retailerlist = (List<Retailers>) pm.newQuery(query).execute();
		
		List<RetailerDTO>  tempList = new ArrayList<RetailerDTO>();
	 	
	    int rowCount=objects.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	Retailer temp =objects.get(ctr);
	    	tempList.add(new RetailerDTO(temp.getCategory(),temp.getName(),temp.getAddress(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	public List<RetailerDTO> searchRetailerByName(String text)
	{
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.Retailers WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Retailer WHERE name =='" + text +"'" ;
	
		List<Retailer> Retailerlist = (List<Retailer>) pm.newQuery(querySearch).execute();
//		List<Retailers> Retailerlist = (List<Retailers>) pm.newQuery(Retailers.class, "this.placename =="+ "'" + text +"'").execute();
		List<RetailerDTO> tempList= new ArrayList<RetailerDTO>();
		int rowCount=Retailerlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Retailer temp =Retailerlist.get(ctr);
		    tempList.add(new RetailerDTO(temp.getCategory(),temp.getName(),temp.getAddress(),temp.getLatitude(),temp.getLongitude(),true));
	    }

		return tempList; 
	}

	@Override
	public String loadStation(RetailerDTO station) 
	{
		try{
			Retailer Retailer= new Retailer(station);
			//pm.makePersistent(station);
			pm.makePersistent(Retailer);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}
}