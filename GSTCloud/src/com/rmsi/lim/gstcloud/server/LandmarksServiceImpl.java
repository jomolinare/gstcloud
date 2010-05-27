package com.rmsi.lim.gstcloud.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;


import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.LandmarksService;
import com.rmsi.lim.gstcloud.server.PMF;
import com.rmsi.lim.gstcloud.shared.Landmarks;

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
	
	String query = "SELECT FROM org.emcode.samples.giscloud.shared.Landmarks WHERE category == 'Landmark'";
	@Override
	public List<Landmarks> displayStation() 
	{
		// TODO Auto-generated method stub
		
		List<Landmarks> landmarklist = (List<Landmarks>) pm.newQuery(query).execute();
		
		List<Landmarks>  tempList = new ArrayList<Landmarks>();
	 	
	    int rowCount=landmarklist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        
	    	tempList.add(landmarklist.get(ctr));
	    }
		
	    return tempList;
	}
	
	public List<Landmarks> searchStation(String text)
	{
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.Landmarks WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM org.emcode.samples.giscloud.shared.Landmarks WHERE placeName =='" + text +"'" ;
	
		List<Landmarks> landmarklist = (List<Landmarks>) pm.newQuery(querySearch).execute();
//		List<Landmarks> landmarklist = (List<Landmarks>) pm.newQuery(Landmarks.class, "this.placename =="+ "'" + text +"'").execute();
		List<Landmarks> tempList= new ArrayList<Landmarks>();
		int rowCount=landmarklist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	tempList.add(landmarklist.get(ctr));
	    }

		return tempList; 
	}

	@Override
	public String loadStation(Landmarks station) 
	{
		try{
			pm.makePersistent(station);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}

	@Override
	public String loadKML(String fileName) {
			
			try
			{
				
			Boolean fileOK = true;
			File fileIn = new File(fileName);
			
			System.out.println(fileIn);
			Kml kml = Kml.unmarshal(fileIn,fileOK);
		
			if (kml != null)
			{			
				Feature feature = kml.getFeature();
				processFeature(null, feature);
			}
			
			Placemark placemark ;		
			
	    }
	    catch (Exception e)
	    {
	      System.err.println(e);
	      System.exit(0);
	    }  
	  
	    return "Success";
	  }


		
		private  void processFeature(Feature parentFeature, Feature feature)
		{
			if (feature instanceof Document)
				{
					processDocument(parentFeature, (Document) feature);
				}
			else if (feature instanceof Folder)
				{
					processFolder(parentFeature, (Folder) feature);
				}
			else if (feature instanceof Placemark)
				{
					processPlacemark(parentFeature, (Placemark) feature);
				}
			else
				{
					System.out.println("Feature " + feature.getName() + " : " + feature);
				}
		}
		
		private  void processDocument(Feature parentFeature, Document doc)
		{
			List<Feature> features = doc.getFeature();

			 System.out.println("Document " + doc.getName());

			for (Feature docFeature : features)
				{
					processFeature(doc, docFeature);
				}
		}

		private  void processFolder(Feature parentFeature, Folder folder)
		{
			List<Feature> features = folder.getFeature();

			// System.out.println("Folder " + folder.getName());

			for (Feature folderFeature : features)
				{
					processFeature(folder, folderFeature);
				}
		
	}
		private  void processPlacemark(Feature parentFeature, Placemark placemark)
		{
			Point point = (Point) placemark.getGeometry();
			List<Coordinate> coordinates = point.getCoordinates();
			
			for (Coordinate coordinate : coordinates) 
			{
				Landmarks land = new Landmarks("Landmark",coordinate.getLatitude(),coordinate.getLongitude(),placemark.getName());
				pm.makePersistent(land);
			}	
			
		

		}
	
}