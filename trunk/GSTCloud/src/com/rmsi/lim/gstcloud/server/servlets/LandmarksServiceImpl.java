package com.rmsi.lim.gstcloud.server.servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;


import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.model.Landmark;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksService;

import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;



@SuppressWarnings("serial")
public class LandmarksServiceImpl extends RemoteServiceServlet implements LandmarksService 
{

	
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Landmark WHERE category == 'LANDMARK'";
	@Override
	public List<LandmarkDTO> getLandMarks() 
	{
		// TODO Auto-generated method stub
		
		List<Landmark> landmarklist = (List<Landmark>) pm.newQuery(query).execute();
		
		List<LandmarkDTO>  tempList = new ArrayList<LandmarkDTO>();
	 	
	    int rowCount=landmarklist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        Landmark temp =landmarklist.get(ctr);
	    	tempList.add(new LandmarkDTO(temp.getCategory(),temp.getLatitude(),temp.getLongitude(),temp.getPlaceName(),true));
	    }
		
	    return tempList;
	}
	
	@Override
	public List<LandmarkDTO> displayLandmarksWithinDistance(Double latitude,Double longitude, Double distance) {
		
		
		    com.beoui.geocell.model.Point centerPoint = new com.beoui.geocell.model.Point(latitude, longitude);
	        
	        List<Object> params = new ArrayList<Object>();
	        params.add("LANDMARK");
	        //GeocellQuery baseQuery = new GeocellQuery("lastName == lastNameParam", "String lastNameParam", params);
            GeocellQuery baseQuery = new GeocellQuery("category== categoryParam","String categoryParam", params);
	        List<Landmark> objects = null;
	        try {
	            objects = GeocellManager.proximityFetch(centerPoint, GSTCloudServerConstants.maxSearchResults, distance, Landmark.class, baseQuery, pm);
	            //Assert.assertTrue(objects.size() > 0);
	        } catch (Exception e) {
	            
	        	e.printStackTrace();
	        	
	        }
		
		
		//List<Landmark> landmarklist = (List<Landmarks>) pm.newQuery(query).execute();
		
		List<LandmarkDTO>  tempList = new ArrayList<LandmarkDTO>();
	 	
	    int rowCount=objects.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	Landmark temp =objects.get(ctr);
	    	tempList.add(new LandmarkDTO(temp.getCategory(),temp.getLatitude(),temp.getLongitude(),temp.getPlaceName(),true));
	    }
		
	    return tempList;
	}
	
	public List<LandmarkDTO> searchLandmarkByName(String text)
	{
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.Landmarks WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Landmark WHERE placeName =='" + text +"'" ;
	
		List<Landmark> landmarklist = (List<Landmark>) pm.newQuery(querySearch).execute();
//		List<Landmarks> landmarklist = (List<Landmarks>) pm.newQuery(Landmarks.class, "this.placename =="+ "'" + text +"'").execute();
		List<LandmarkDTO> tempList= new ArrayList<LandmarkDTO>();
		int rowCount=landmarklist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Landmark temp =landmarklist.get(ctr);
		    tempList.add(new LandmarkDTO(temp.getCategory(),temp.getLatitude(),temp.getLongitude(),temp.getPlaceName(),true));
	    }

		return tempList; 
	}

	@Override
	public String loadStation(LandmarkDTO station) 
	{
		try{
			Landmark landmark= new Landmark(station);
			//pm.makePersistent(station);
			pm.makePersistent(landmark);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}
}