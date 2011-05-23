package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.WardBoundariesService;
import com.rmsi.lim.gstcloud.client.model.PlTO;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;
import com.rmsi.lim.gstcloud.server.model.PolygonInsert;
import com.rmsi.lim.gstcloud.server.model.Tower;
import com.rmsi.lim.gstcloud.server.model.WardBoundaries;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;

public class WardBoundariesServiceImpl extends RemoteServiceServlet implements WardBoundariesService{


	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.WardBoundaries WHERE category == 'WardBoundaries'";
	String queryWard = "SELECT FROM com.rmsi.lim.gstcloud.server.model.WardBoundaries ";
	public List<WardBoundariesDTO> getWardBoundaries() 
	{
		// TODO Auto-generated method stub
		
		List<WardBoundaries> wardBoundaryList = (List<WardBoundaries>) pm.newQuery(query).execute();
		
		List<WardBoundariesDTO>  tempList = new ArrayList<WardBoundariesDTO>();
	 	
	    int rowCount=wardBoundaryList.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        WardBoundaries tempWardBoundary =wardBoundaryList.get(ctr);
	    	tempList.add(new WardBoundariesDTO(tempWardBoundary.getCategory(),tempWardBoundary.getPlaceName(),tempWardBoundary.getLatitude(),tempWardBoundary.getLongitude(),
	    			     tempWardBoundary.getPolygonObject(),tempWardBoundary.getWardName(),tempWardBoundary.getWardNum(),
	    			     tempWardBoundary.getTotalPop(),tempWardBoundary.getCityName(),tempWardBoundary.getStreetName()));
	    }
		
	    return tempList;
	}
	
	public List<WardBoundariesDTO> getData() 
	{
		// TODO Auto-generated method stub
		
		List<WardBoundaries> wardBoundaryList = (List<WardBoundaries>) pm.newQuery(queryWard).execute();
		
		List<WardBoundariesDTO>  tempList = new ArrayList<WardBoundariesDTO>();
	 	
	    int rowCount=wardBoundaryList.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	WardBoundaries temp =wardBoundaryList.get(ctr);
	    	tempList.add(new WardBoundariesDTO(temp.getCategory(),temp.getPlaceName(),temp.getLatitude(),temp.getLongitude(),temp.getPolygonObject(),
	    			temp.getWardName(),temp.getWardNum(),temp.getTotalPop(),temp.getCityName(),temp.getStreetName()));
	    }
		
	    return tempList;
	}
	
	public List<WardBoundariesDTO> getIntersectionData(String latLng)
	{
	
		List<WardBoundaries> wardBoundarylist = (List<WardBoundaries>) pm.newQuery(queryWard).execute();
		List<WardBoundariesDTO>  tempList = new ArrayList<WardBoundariesDTO>();
		GeometryFactory gf = new GeometryFactory();
		WKTReader wkt = new WKTReader(gf);
		
	    int rowCount=wardBoundarylist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	WardBoundaries temp =wardBoundarylist.get(ctr);
	    	String polObj[] = temp.getPolygonObject();
	    	String wktObj = "POLYGON((";
			
			for(int polCount=0; polCount<polObj.length;polCount++)
			{
				
				if (polCount==0)
					wktObj = wktObj + polObj[polCount].split(",")[0]+" "+polObj[polCount].split(",")[1];
				else
					wktObj = wktObj + ","+ polObj[polCount].split(",")[0]+" "+polObj[polCount].split(",")[1];
				
			}
			wktObj = wktObj + "))";
			try
			{
				
				com.vividsolutions.jts.geom.Geometry wktGeoom= wkt.read(wktObj);
				
				com.vividsolutions.jts.geom.Polygon vmp = (com.vividsolutions.jts.geom.Polygon)wktGeoom;
				com.vividsolutions.jts.geom.Geometry wktIntrctGeom = vmp.intersection(wkt.read(latLng));
				/*System.out.println("The points after intersection are : "+wktIntrctGeom.getCoordinates().toString());
				System.out.println("The area of the intersected region is : "+wktIntrctGeom.getArea());*/
				//com.vividsolutions.jts.geom.Geometry wktIntrctGeom = vmp.intersection(wkt.read(latLng));
				boolean b = vmp.intersects(wkt.read(latLng));
				
				double percentageCovered = wktIntrctGeom.getArea()/wktGeoom.getArea();
				System.out.println("The percentage covred is : ");
				Boolean bObj =  new Boolean(b);
				
				double popForPercentageCovered = percentageCovered * temp.getTotalPop();
				
				
				
				//bObj.toString();
				System.out.println(wktIntrctGeom.getGeometryType()+"-"+wktIntrctGeom.isEmpty());
				if(wktIntrctGeom.getGeometryType().toLowerCase().equals("polygon"))
				{
					String str = wktIntrctGeom.toText().replaceAll("POLYGON","");
					String stRep = str.replaceAll("\\(","");
					String stRb = stRep.replaceAll("\\)", "");
					//String str = wktIntrctGeom.toText().split("(")[1];
					//String stRep = str.split("))")[0];
					if (b) tempList.add(new WardBoundariesDTO(stRb,popForPercentageCovered,temp.getWardName()));
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
	    	//tempList.add(new PlTO(temp.getPlaceName(),temp.getLatitude(),temp.getLongitude(),temp.getPolygonObject(),true));
	    }
		
	    return tempList;
	}
	
	public List<WardBoundariesDTO> searchWardBoundaryByName(String text)
	{
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Tower WHERE placeName =='" + text +"'" ;
	
		List<WardBoundaries> wardBoundarylist = (List<WardBoundaries>) pm.newQuery(querySearch).execute();
		List<WardBoundariesDTO> tempList= new ArrayList<WardBoundariesDTO>();
		int rowCount=wardBoundarylist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	    	WardBoundaries temp =wardBoundarylist.get(ctr);
		    tempList.add(new WardBoundariesDTO(temp.getCategory(),temp.getPlaceName(),temp.getLatitude(),temp.getLongitude(),temp.getPolygonObject(),
		    			temp.getWardName(),temp.getWardNum(),temp.getTotalPop(),temp.getCityName(),temp.getStreetName()));
	    }

		return tempList; 
	}

	public String loadWardBoundary(WardBoundariesDTO wardBoundaryStation) 
	{
		try{
			WardBoundaries wardBoundary= new WardBoundaries(wardBoundaryStation);
			pm.makePersistent(wardBoundary);
		   } finally
		   	{
			   
			}
		return null;
	}


	@Override
	public List<WardBoundariesDTO> displayWardBoundariesWithinDistance(
			Double latitude, Double longitude, Double distance) {
		
		return null;
	}

	@Override
	public List<WardBoundariesDTO> getIntersectedPolygonWithPoint(String latLng) {
		// TODO Auto-generated method stub
		return null;
	}
}
