package com.rmsi.lim.gstcloud.server.servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.Assert;


import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.GeocellQuery;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.model.NLR;
import com.rmsi.lim.gstcloud.server.model.PolygonInsert;
import com.rmsi.lim.gstcloud.server.utilities.GSTCloudServerConstants;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.client.interfaces.NewLayerService;
import com.rmsi.lim.gstcloud.client.interfaces.PolygonService;

import com.rmsi.lim.gstcloud.client.model.NLRTO;
import com.rmsi.lim.gstcloud.client.model.PlTO;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

@SuppressWarnings("serial")

public class PolygonServiceImpl extends RemoteServiceServlet implements PolygonService  {
PersistenceManager pm = PMF.get().getPersistenceManager();
	
	String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.PolygonInsert ";
	
	public List<PlTO> getData() 
	{
		// TODO Auto-generated method stub
		
		List<PolygonInsert> polygonlist = (List<PolygonInsert>) pm.newQuery(query).execute();
		
		List<PlTO>  tempList = new ArrayList<PlTO>();
	 	
	    int rowCount=polygonlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	PolygonInsert temp =polygonlist.get(ctr);
	    	tempList.add(new PlTO(temp.getPlaceName(),temp.getLatitude(),temp.getLongitude(),temp.getPolygonObject(),true));
	    }
		
	    return tempList;
	}
	
	
	
	public List<PlTO> getIntersectionData(String latLng)
	{
	
		List<PolygonInsert> polygonlist = (List<PolygonInsert>) pm.newQuery(query).execute();
		
		List<PlTO>  tempList = new ArrayList<PlTO>();
		GeometryFactory gf = new GeometryFactory();
		WKTReader wkt = new WKTReader(gf);
	    int rowCount=polygonlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	    	PolygonInsert temp =polygonlist.get(ctr);
	    	String polObj[] = temp.getPolygonObject();
	    	String wktObj = "POLYGON((";
			
			for(int polCount=0; polCount<polObj.length;polCount++)
			{
				//StringTokenizer stz = new StringTokenizer(llObj[polCount], ",");
				//wktPoly =
				if (polCount==0)
					wktObj = wktObj + polObj[polCount].split(",")[0]+" "+polObj[polCount].split(",")[1];
				else
					wktObj = wktObj + ","+ polObj[polCount].split(",")[0]+" "+polObj[polCount].split(",")[1];
				
			}
			wktObj = wktObj + "))";
			//wkt.read(wktObj);
			try
			{
				
				
				com.vividsolutions.jts.geom.Geometry wktGeoom= wkt.read(wktObj);
				
				com.vividsolutions.jts.geom.Polygon vmp = (com.vividsolutions.jts.geom.Polygon)wktGeoom;
				com.vividsolutions.jts.geom.Geometry wktIntrctGeom = vmp.intersection(wkt.read(latLng));
				/*System.out.println("The points after intersection are : "+wktIntrctGeom.getCoordinates().toString());
				System.out.println("The area of the intersected region is : "+wktIntrctGeom.getArea());*/
				//com.vividsolutions.jts.geom.Geometry wktIntrctGeom = vmp.intersection(wkt.read(latLng));
				boolean b = vmp.intersects(wkt.read(latLng));
				
				double percentageCovered=wktIntrctGeom.getArea()/wktGeoom.getArea();
				System.out.println("The percentage covred is : ");
				Boolean bObj =  new Boolean(b);
				
				//bObj.toString();
				System.out.println(wktIntrctGeom.getGeometryType()+"-"+wktIntrctGeom.isEmpty());
				if(wktIntrctGeom.getGeometryType().toLowerCase().equals("polygon"))
				{
					String str = wktIntrctGeom.toText().replaceAll("POLYGON","");
					String stRep = str.replaceAll("\\(","");
					String stRb = stRep.replaceAll("\\)", "");
					//String str = wktIntrctGeom.toText().split("(")[1];
					//String stRep = str.split("))")[0];
					tempList.add(new PlTO(stRb));
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
	    	//tempList.add(new PlTO(temp.getPlaceName(),temp.getLatitude(),temp.getLongitude(),temp.getPolygonObject(),true));
	    }
		
	    return tempList;
	}

}
