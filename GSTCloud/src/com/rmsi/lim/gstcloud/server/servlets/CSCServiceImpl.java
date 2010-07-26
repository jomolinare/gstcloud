package com.rmsi.lim.gstcloud.server.servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;


import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.model.CSC;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.CSCService;

import com.rmsi.lim.gstcloud.client.model.CSCDTO;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;



@SuppressWarnings("serial")
public class CSCServiceImpl extends RemoteServiceServlet implements CSCService 
{
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.CSC WHERE category == 'CSC'";
	@Override
	public List<CSCDTO> getCSCs() 
	{
		// TODO Auto-generated method stub
		
		List<CSC> CSClist = (List<CSC>) pm.newQuery(query).execute();
		
		List<CSCDTO>  tempList = new ArrayList<CSCDTO>();
	 	
	    int rowCount=CSClist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        CSC temp =CSClist.get(ctr);
	    	tempList.add(new CSCDTO(temp.getCategory(),temp.getName(),temp.getContact_person(),temp.getAddress(),temp.getTower_name(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	@Override
	public List<CSCDTO> displayCSCsWithinDistance(Double latitude,Double longitude, Double distance) {

		com.beoui.geocell.model.Point centerPoint = new com.beoui.geocell.model.Point(latitude, longitude);
	        
	        List<Object> params = new ArrayList<Object>();
	        params.add("CSC");
	        //GeocellQuery baseQuery = new GeocellQuery("lastName == lastNameParam", "String lastNameParam", params);
            GeocellQuery baseQuery = new GeocellQuery("category== categoryParam","String categoryParam", params);
	        List<CSC> objects = null;
	        try {
	            objects = GeocellManager.proximityFetch(centerPoint, GSTCloudServerConstants.maxSearchResults, distance, CSC.class, baseQuery, pm);
	            //Assert.assertTrue(objects.size() > 0);
	        } catch (Exception e) {	            
	        	e.printStackTrace();	        	
	        }		
		
		//List<CSC> CSClist = (List<CSCs>) pm.newQuery(query).execute();
		
		List<CSCDTO>  tempList = new ArrayList<CSCDTO>();
	 	
	    int rowCount=objects.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	CSC temp =objects.get(ctr);
	    	tempList.add(new CSCDTO(temp.getCategory(),temp.getName(),temp.getContact_person(),temp.getAddress(),temp.getTower_name(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		
	    return tempList;
	}
	
	public List<CSCDTO> searchCSCByName(String text)
	{
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.CSCs WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.CSC WHERE name =='" + text +"'" ;
	
		List<CSC> CSClist = (List<CSC>) pm.newQuery(querySearch).execute();
//		List<CSCs> CSClist = (List<CSCs>) pm.newQuery(CSCs.class, "this.placename =="+ "'" + text +"'").execute();
		List<CSCDTO> tempList= new ArrayList<CSCDTO>();
		int rowCount=CSClist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 CSC temp =CSClist.get(ctr);
		    tempList.add(new CSCDTO(temp.getCategory(),temp.getName(),temp.getContact_person(),temp.getAddress(),temp.getTower_name(),temp.getLatitude(),temp.getLongitude(),true));
	    }
		return tempList; 
	}

	@Override
	public String loadStation(CSCDTO station) 
	{
		try{
			CSC csc= new CSC(station);
			//pm.makePersistent(station);
			pm.makePersistent(csc);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}	
}