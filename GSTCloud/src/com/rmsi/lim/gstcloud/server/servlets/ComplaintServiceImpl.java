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
import com.rmsi.lim.gstcloud.server.model.Complaint;

import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.ComplaintService;


import com.rmsi.lim.gstcloud.client.model.CSCDTO;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;




import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;



@SuppressWarnings("serial")
public  class ComplaintServiceImpl extends RemoteServiceServlet implements ComplaintService 
{

	
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Complaint WHERE category == 'Complaint'";
	public List<ComplaintDTO> getComplaint() 
	{
		 List<ComplaintDTO> tempList = new ArrayList<ComplaintDTO>() ;
		// TODO Auto-generated method stub
		
		List<Complaint> Complaintlist = (List<Complaint>) pm.newQuery(query).execute();
		
		List<ComplaintDTO>  ComplaintList = new ArrayList<ComplaintDTO>();
	 	
	    int rowCount=Complaintlist.size();
	   
		for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        Complaint temp =Complaintlist.get(ctr);
	    	tempList.add(new ComplaintDTO(temp.getCategory(),
	    			                      temp.getCircle(),
	    			                      temp.getSubtype(),
	    			                      temp.getSubSubtype(),
	    			                      temp.getProblemSummary(),
	    			                      temp.getLatitude(),
	    			                      temp.getLongitude(),
	    			                      true));
	    }
		
	    return tempList;
	}
	
	@Override
	public List<ComplaintDTO> displayComplaintsWithinDistance(Double latitude,Double longitude, Double distance) {
		
		
		    com.beoui.geocell.model.Point centerPoint = new com.beoui.geocell.model.Point(latitude, longitude);
	        
	        List<Object> params = new ArrayList<Object>();
	        params.add("Complaint");
	       
            GeocellQuery baseQuery = new GeocellQuery("category== categoryParam","String categoryParam", params);
	        List<Complaint> objects = null;
	        try {
	            objects = GeocellManager.proximityFetch(centerPoint, GSTCloudServerConstants.maxSearchResults, distance, Complaint.class, baseQuery, pm);
	          
	        } catch (Exception e) {
	            
	        	e.printStackTrace();
	        	
	        }
		
		
		
		List<ComplaintDTO>  tempList = new ArrayList<ComplaintDTO>();
	 	
	    int rowCount=objects.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	Complaint temp =objects.get(ctr);
	    	tempList.add(new ComplaintDTO(temp.getCategory(),
                    temp.getCircle(),
                    temp.getSubtype(),
                    temp.getSubSubtype(),
                    temp.getProblemSummary(),
                    temp.getLatitude(),
                    temp.getLongitude(),
                    true));
	    }
		
	    return tempList;
	}
	
	/*public List<ComplaintDTO> searchLandmarkByName(String text)
	{
		
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Complaint WHERE placeName =='" + text +"'" ;
	
		List<Complaint> Complaintlist = (List<Complaint>) pm.newQuery(querySearch).execute();

		List<ComplaintDTO> tempList= new ArrayList<ComplaintDTO>();
		int rowCount=Complaintlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Complaint temp =Complaintlist.get(ctr);
		    tempList.add(new ComplaintDTO(temp.getcategory(),
                    temp.getCircle(),
                    temp.getSubtype(),
                    temp.getSubSubtype(),
                    temp.getLatitude(),
                    temp.getLongitude(),
                    true));
	    }
		return tempList; 
	}*/

	@Override
	public String loadStation(ComplaintDTO station) 
	{
		try{
			Complaint Complaint= new Complaint(station);
			//pm.makePersistent(station);
			pm.makePersistent(Complaint);
		   } finally
		   	{
			   //pm.close();
			}
		return null;
	}

	@Override
	public List<ComplaintDTO> getComplaints() {
    List<Complaint> Complaintlist = (List<Complaint>) pm.newQuery(query).execute();
		
		List<ComplaintDTO>  tempList = new ArrayList<ComplaintDTO>();
	 	
	    int rowCount=Complaintlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	Complaint temp =Complaintlist.get(ctr);
	    	tempList.add(new ComplaintDTO(temp.getCategory(),
	    			temp.getCircle(),
                    temp.getSubtype(),
                    temp.getSubSubtype(),
                    temp.getProblemSummary(),
                    temp.getLatitude(),
                    temp.getLongitude(),
                    true));
	    }
		
	    return tempList;
	}

	@Override
	public List<ComplaintDTO> searchComplaintsBySubtype(String text) {
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Complaint WHERE subtype =='" + text +"'" ;
		
		List<Complaint> Complaintlist = (List<Complaint>) pm.newQuery(querySearch).execute();

		List<ComplaintDTO> tempList= new ArrayList<ComplaintDTO>();
		int rowCount=Complaintlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Complaint temp =Complaintlist.get(ctr);
		    tempList.add(new ComplaintDTO(temp.getCategory(),
                    temp.getCircle(),
                    temp.getSubtype(),
                    temp.getSubSubtype(),
                    temp.getProblemSummary(),
                    temp.getLatitude(),
                    temp.getLongitude(),
                    true));
	    }
		return tempList; 
	}



	@Override
	public List<ComplaintDTO> searchComplaintsBySubSubtype(String text) {
String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Complaint WHERE subSubtype =='" + text +"'" ;
		
		List<Complaint> Complaintlist = (List<Complaint>) pm.newQuery(querySearch).execute();

		List<ComplaintDTO> tempList= new ArrayList<ComplaintDTO>();
		int rowCount=Complaintlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Complaint temp =Complaintlist.get(ctr);
		    tempList.add(new ComplaintDTO(temp.getCategory(),
                    temp.getCircle(),
                    temp.getSubtype(),
                    temp.getSubSubtype(),
                    temp.getProblemSummary(),
                    temp.getLatitude(),
                    temp.getLongitude(),
                    true));
	    }
		return tempList; 
	}

	
}