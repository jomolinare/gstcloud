/*package com.rmsi.lim.gstcloud.server.servlets;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.beoui.geocell.GeocellManager;
import com.google.gwt.user.client.Window;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudSharedConstants;
import com.rmsi.lim.gstcloud.server.model.CSC;
import com.rmsi.lim.gstcloud.server.model.Landmark;
import com.rmsi.lim.gstcloud.server.model.Retailer;
import com.rmsi.lim.gstcloud.server.model.Tower;
import com.rmsi.lim.gstcloud.server.utilities.PMF;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
//import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
public class FileUp extends HttpServlet{

	private static final long serialVersionUID = 1L;
	PersistenceManager pm = PMF.get().getPersistenceManager();
	String selectedLayer;
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		{ 
			Object temp=request.getSession().getAttribute("selectedlayer");
			if (temp!=null)
				selectedLayer=(String)temp;
			else selectedLayer=GSTCloudSharedConstants.Landmark;
			
            ServletFileUpload upload = new ServletFileUpload();
            try{
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                                 
                 Kml kml = Kml.unmarshal(stream);
                 
                 if (kml != null)
     			{			
     				Feature feature = kml.getFeature();
     				processFeature(null, feature);
     			}
            }
        }
          catch(Exception e){
            e.printStackTrace();
         }
        }      
    }
	
	public  void processFeature(Feature parentFeature, Feature feature)
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
	
	public  void processDocument(Feature parentFeature, Document doc)
	{
		List<Feature> features = doc.getFeature();
		 System.out.println("Document " + doc.getName());
		for (Feature docFeature : features)
		{
			processFeature(doc, docFeature);		}
	}

	public void processFolder(Feature parentFeature, Folder folder)
	{
		List<Feature> features = folder.getFeature();
		// System.out.println("Folder " + folder.getName());
		for (Feature folderFeature : features)
			{
				processFeature(folder, folderFeature);
			}	
	}
	private String extractTagValue(String description, String tag){
		int i=description.indexOf(tag);		
		i+=(tag.length()+9);
		int j=description.indexOf('<', i);
		return (description.substring(i, j));		 
	}
	
	private  void processPlacemark(Feature parentFeature, Placemark placemark)
	{
		Point point = (Point) placemark.getGeometry();
		List<Coordinate> coordinates = point.getCoordinates();
		String str=(String)placemark.getDescription();
		System.out.println(str);
		for (Coordinate coordinate : coordinates) 
		{
			if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0){
				Landmark land = new Landmark(extractTagValue(str,"CATEGORY:")
						                    ,coordinate.getLatitude()
						                    ,coordinate.getLongitude()
						                    ,placemark.getName()
						                    ,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
				pm.makePersistent(land);
			}
			else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0){
												
				Tower twr = new Tower(extractTagValue(str,"CATEGORY:")
						             ,placemark.getName()
						             ,extractTagValue(str,"OWNER:")
						             ,extractTagValue(str,"STATUS:")
						             ,extractTagValue(str,"COVERAGE:")
						             ,new Double(extractTagValue(str,"TOWER_HEIGHT:"))
						             ,coordinate.getLatitude()
						             ,coordinate.getLongitude()
						             ,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
				pm.makePersistent(twr);
			}
			else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0){
				CSC csc = new CSC(extractTagValue(str,"CATEGORY:")
								 ,placemark.getName()
								 ,extractTagValue(str,"CONTACT_PERSON:")
								 ,extractTagValue(str,"ADDRESS:")
								 ,extractTagValue(str,"TOWER_NAME:")
								 ,coordinate.getLatitude()
								 ,coordinate.getLongitude()
								 ,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
				pm.makePersistent(csc);
			}
			else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0){				
				Retailer ret = new Retailer(extractTagValue(str,"CATEGORY:")
										   ,placemark.getName()
										   ,extractTagValue(str,"ADDRESS:")
										   ,coordinate.getLatitude()
										   ,coordinate.getLongitude()
										   ,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
				pm.makePersistent(ret);
			}
		}	
	}
	
}
*/
package com.rmsi.lim.gstcloud.server.servlets;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.datanucleus.sco.backed.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.beoui.geocell.GeocellManager;
//import com.google.gwt.maps.client.overlay.Polygon;
import com.google.appengine.api.datastore.Blob;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudSharedConstants;
import com.rmsi.lim.gstcloud.server.model.CSC;
import com.rmsi.lim.gstcloud.server.model.Landmark;
import com.rmsi.lim.gstcloud.server.model.PolygonInsert;
import com.rmsi.lim.gstcloud.server.model.Retailer;
import com.rmsi.lim.gstcloud.server.model.Tower;
import com.rmsi.lim.gstcloud.server.model.WardBoundaries;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.rmsi.lim.gstcloud.server.model.NLR;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
import com.vividsolutions.jts.geom.CoordinateSequences;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.gml2.GMLConstants;
import com.vividsolutions.jts.io.gml2.GMLHandler;


import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.Polygon;


import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.gml2.*;


import java.util.*;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
//import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
public class FileUp extends HttpServlet{

	private static final long serialVersionUID = 1L;
	PersistenceManager pm = PMF.get().getPersistenceManager();
	String selectedLayer;

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		{ 
			Object temp=request.getSession().getAttribute("selectedlayer");
			
			if (temp!=null)
				selectedLayer=(String)temp;
			else 
				selectedLayer=GSTCloudSharedConstants.NewLayer;
			
				//selectedLayer=GSTCloudSharedConstants.Landmark;
			
            ServletFileUpload upload = new ServletFileUpload();
            try{
            //	GeometryFactory gf = new GeometryFactory();
    		//	WKTReader wkt = new WKTReader(gf);
            	
            FileItemIterator iter = upload.getItemIterator(request);
            //org.apache.xerces.parsers.SAXParser sax = new org.apache.xerces.parsers.SAXParser();
            //iter.
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                
                String name = item.getFieldName();
                System.out.println("Field Name"+name);
                InputStream stream = item.openStream();
                //java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(stream));     
//                jstKmlReader(stream);
                 Kml kml = Kml.unmarshal(stream);
                 
                 if (kml != null)
     			{			
     				Feature feature = kml.getFeature();
     				//	System.out.println(feature.getClass().getSimpleName());
     				processFeature(null, feature);
     			}
            }
           // mPolygon = mPolygon + "))";
          
            //com.vividsolutions.jts.geom.Geometry vgt = wkt.read(mPolygon);
            //com.vividsolutions.jts.geom.MultiPolygon vmp = (com.vividsolutions.jts.geom.MultiPolygon)vgt;
            //System.out.println(vmp.intersects(wkt.read("Polygon((37.42257124044786 -122.0848938459612,37.42211922626856 -122.0849580979198,37.42207183952619 -122.0847469573047,37.42257124044786 -122.0848938459612))")));
            //com.vividsolutions.jts.geom.
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//ObjectOutputStream oos = new ObjectOutputStream(bos);
			//oos.writeObject(index);
			//byte[] bArray = bos.toByteArray();
			//Blob blob = new Blob(bArray);
           // System.out.println(mPolygon);
        }
          catch(Exception e){
            e.printStackTrace();
         }
        }      
    }
	

	
	
	public  void processFeature(Feature parentFeature, Feature feature)
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
	
	public  void processDocument(Feature parentFeature, Document doc)
	{
		List<Feature> features = doc.getFeature();
		 System.out.println("Document " + doc.getName());
		 
		 
		for (Feature docFeature : features)
		{
			processFeature(doc, docFeature);
			//if (temp < features.size())
			//	mPolygon = mPolygon + ",";
			
			//temp++;
		}
		
	}

	public void processFolder(Feature parentFeature, Folder folder)
	{
		List<Feature> features = folder.getFeature();
		// System.out.println("Folder " + folder.getName());
		for (Feature folderFeature : features)
			{
				processFeature(folder, folderFeature);
			}	
	}
	private String extractTagValue(String description, String tag){
		int i=description.indexOf(tag);		
		i+=(tag.length()+9);
		int j=description.indexOf('<', i);
		return (description.substring(i, j));		 
	}
	
	private  void processPlacemark(Feature parentFeature, Placemark placemark)
	{
		
		
		Geometry ge = placemark.getGeometry();
		System.out.println(ge.toString());
		System.out.println(ge.getId());
		System.out.println(ge.getClass().getCanonicalName());
		System.out.println(ge.getClass().getName());
		System.out.println(ge.getClass().getSimpleName());
		List<Object> obj = ge.getGeometrySimpleExtension();
		for(Object ob : obj)
		{
			System.out.println(ob);
		}
		//placemark.get
		//System.out.println(ge.getClass().equals("Point"));
		if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.TestPolygon.trim())==0)
		{
			//get the geometry as polygon object
			if(ge.getClass().getSimpleName().equals("MultiGeometry"))
			{
				MultiGeometry mGeometry = (MultiGeometry) placemark.getGeometry();
				List<Geometry> mGList = mGeometry.getGeometry();
				for(int iM=0;iM<mGList.size();iM++)
				{
					Polygon pg = (Polygon) mGList.get(iM);
					Boundary bd = pg.getOuterBoundaryIs(); //create the boundary object 
					LinearRing lg = bd.getLinearRing(); //get the LinearRing Object 
					List <Coordinate> pcord = lg.getCoordinates();// assign the coordinate to the Coordinates object 
					Object cObj[] = pcord.toArray();
					List cList = Arrays.asList(cObj);
					String st[] = new String[pcord.size()];
					int i=0;
					//mPolygon = mPolygon +"(";
					String wktPolygon = "POLYGON((";
					for(Coordinate coordinate : pcord)
					{

						String latlng1 = coordinate.getLatitude()+","+coordinate.getLongitude();
						/*if(i==0)
							mPolygon = mPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
					else
						 mPolygon = mPolygon + ","+ coordinate.getLatitude()+" "+coordinate.getLongitude();*/
						 
						if(i==0)
							wktPolygon = wktPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
						else
							wktPolygon = wktPolygon + ","+ coordinate.getLatitude()+" "+coordinate.getLongitude();

						st[i] = latlng1;
						i++;
					}
					wktPolygon = wktPolygon+"))";

					//String wkPol[] = new String[]{wktPolygon};
					//mPolygon = mPolygon + ")";
					//Store into as object 
					//st variable is array object to store into the object field.
					PolygonInsert pi1 = new PolygonInsert(placemark.getName(),122.0834204551642,37.42237075460644,st);
					//System.out.println(coordinate.getLongitude()+","+coordinate.getLatitude());
					pm.makePersistent(pi1);
				}
				
			}else
			{
			Polygon pol = (Polygon) placemark.getGeometry();
			Boundary bd = pol.getOuterBoundaryIs(); //create the boundary object 
			LinearRing lg = bd.getLinearRing(); //get the LinearRing Object 
			Object olg = (Object) lg;
			//com.vividsolutions.jts.geom.LinearRing vlg = (com.vividsolutions.jts.geom.LinearRing)olg;
			//com.vividsolutions.jts.geom.Point  vp = vlg.getCentroid();
			//System.out.println(vp.getX()+"-"+vp.getY());
			List <Coordinate> pcord = lg.getCoordinates();// assign the coordinate to the Coordinates object 
			Object cObj[] = pcord.toArray();
			List<Object> cList = Arrays.asList(cObj);
			
			//java.util.List lt = new java.util.List();
			
			//java.util.ArrayList al = new java.util.ArrayList();
			//al.
			//lt.toArray(cObj);
			String st[] = new String[pcord.size()];
			int i=0;
			//GeometryFactory gf = new GeometryFactory();
			//WKTReader wkt = new WKTReader(gf);
			//String latlng1 = "Polygon(";
			//mPolygon = mPolygon +"(";
			//String wkPol[] = new String[]{};
			String wktPolygon = "POLYGON((";
			for(Coordinate coordinate : pcord)
			{
				
				//latlng1 = latlng1+coordinate.getLatitude()+","+coordinate.getLongitude();
				st[i] = coordinate.getLatitude()+","+coordinate.getLongitude();
				/*if (i==0)
					mPolygon = mPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
				else
					mPolygon = mPolygon +","+ coordinate.getLatitude()+" "+coordinate.getLongitude();*/
					
				
				if(i==0)
					wktPolygon = wktPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
				else
					wktPolygon = wktPolygon + ","+ coordinate.getLatitude()+" "+ coordinate.getLongitude();
		
				i++;
			}
			wktPolygon = wktPolygon+"))";
			
			//String wkPol[] = new String[]{wktPolygon};
			//mPolygon = mPolygon + ")";
			//latlng1= latlng1 +")";
			//ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//ObjectOutputStream oos = new ObjectOutputStream(bos);
			//oos.writeObject(index);
			//byte[] bArray = bos.toByteArray();
			//Blob blob = new Blob(bArray);
			
			//Store into as object 
			//st variable is array object to store into the object field.
			PolygonInsert pi1 = new PolygonInsert(placemark.getName(),122.0834204551642,37.42237075460644,st);
			//System.out.println(coordinate.getLongitude()+","+coordinate.getLatitude());
			pm.makePersistent(pi1);
			}
		}
		//SA Start
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.WardBoundaries.trim())==0)
		{
			//get the geometry as polygon object
			if(ge.getClass().getSimpleName().equals("MultiGeometry"))
			{
				MultiGeometry mGeometry = (MultiGeometry) placemark.getGeometry();
				List<Geometry> mGList = mGeometry.getGeometry();
				for(int iM=0;iM<mGList.size();iM++)
				{
				Polygon pg = (Polygon) mGList.get(iM);
				Boundary bd = pg.getOuterBoundaryIs(); //create the boundary object 
				LinearRing lg = bd.getLinearRing(); //get the LinearRing Object 
				List <Coordinate> pcord = lg.getCoordinates();// assign the coordinate to the Coordinates object 
				List<Coordinate>  pcord1 = lg.getCoordinates();
				Object cObj[] = pcord.toArray();
				List cList = Arrays.asList(cObj);
				String st[] = new String[pcord.size()];
				int i=0;
				String wktPolygon = "POLYGON((";
				for(Coordinate coordinate : pcord)
				{
					
					String latlng1 = coordinate.getLatitude()+","+coordinate.getLongitude();
					
					if(i==0)
						wktPolygon = wktPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
					else
						wktPolygon = wktPolygon + ","+ coordinate.getLatitude()+" "+coordinate.getLongitude();
			
					st[i] = latlng1;
					i++;
				}
				wktPolygon = wktPolygon+"))";
				String str=(String)placemark.getDescription();
				WardBoundaries wb1 = new WardBoundaries("WardBoundaries"
														,placemark.getName()
														,122.0834204551642,37.42237075460644
														,st
														,extractTagValue(str,"WARD_NAME:")
														,extractTagValue(str,"WARD_NO:")
														,new Double(extractTagValue(str,"TOT_P:"))
														,extractTagValue(str,"CITY_NAME:")
														,extractTagValue(str,"STATE_NAME:")
														,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(122.0834204551642,37.42237075460644)));
				pm.makePersistent(wb1);
				}
				
			}
			else
			{
				Polygon pol = (Polygon) placemark.getGeometry();
				Boundary bd = pol.getOuterBoundaryIs(); //create the boundary object 
				LinearRing lg = bd.getLinearRing(); //get the LinearRing Object 
				Object olg = (Object) lg;

				List <Coordinate> pcord = lg.getCoordinates();// assign the coordinate to the Coordinates object 
				Object cObj[] = pcord.toArray();
				List<Object> cList = Arrays.asList(cObj);


				String st[] = new String[pcord.size()];
				int i=0;

				String wktPolygon = "POLYGON((";
				for(Coordinate coordinate : pcord)
				{
					st[i] = coordinate.getLatitude()+","+coordinate.getLongitude();
					if(i==0)
						wktPolygon = wktPolygon + coordinate.getLatitude()+" "+coordinate.getLongitude();
					else
						wktPolygon = wktPolygon + ","+ coordinate.getLatitude()+" "+ coordinate.getLongitude();

					i++;
				}
				wktPolygon = wktPolygon+"))";

				//WardBoundaries wb1 = new WardBoundaries(placemark.getName(),placemark.getDescription(),122.0834204551642,37.42237075460644,st);
				String str=(String)placemark.getDescription();
				WardBoundaries wb1 = new WardBoundaries("WardBoundaries"
														,placemark.getName()
														,122.0834204551642,37.42237075460644
														,st
														,extractTagValue(str,"WARD_NAME:")
														,extractTagValue(str,"WARD_NO:")
														,new Double(extractTagValue(str,"TOT_P:"))
														,extractTagValue(str,"CITY_NAME:")
														,extractTagValue(str,"STATE_NAME:")
														,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(122.0834204551642,37.42237075460644)));
				
				pm.makePersistent(wb1);
			}
		}
		//SA Stop
		else 
		{
			Point point = (Point) placemark.getGeometry();



			//Polygon poly = (Polygon) placemark

			List<Coordinate> coordinates = point.getCoordinates();
			String str=(String)placemark.getDescription();
			System.out.println(str);
			for (Coordinate coordinate : coordinates) 
			{
				/*if(GSTCloudSharedConstants.Landmark.equalsIgnoreCase(selectedLayer.trim())){
					
				}*/
				if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0){
					Landmark land = new Landmark(extractTagValue(str,"CATEGORY:")
							,coordinate.getLatitude()
							,coordinate.getLongitude()
							,placemark.getName()
							,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
					pm.makePersistent(land);
				}
				else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0){

					Tower twr = new Tower(extractTagValue(str,"CATEGORY:")
							,placemark.getName()
							,extractTagValue(str,"OWNER:")
							,extractTagValue(str,"STATUS:")
							,extractTagValue(str,"COVERAGE:")
							,new Double(extractTagValue(str,"TOWER_HEIGHT:"))
					,coordinate.getLatitude()
					,coordinate.getLongitude()
					,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
					pm.makePersistent(twr);
				}
				else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0){
					CSC csc = new CSC(extractTagValue(str,"CATEGORY:")
							,placemark.getName()
							,extractTagValue(str,"CONTACT_PERSON:")
							,extractTagValue(str,"ADDRESS:")
							,extractTagValue(str,"TOWER_NAME:")
							,coordinate.getLatitude()
							,coordinate.getLongitude()
							,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
					pm.makePersistent(csc);
				}
				else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0){				
					Retailer ret = new Retailer(extractTagValue(str,"CATEGORY:")
							,placemark.getName()
							,extractTagValue(str,"ADDRESS:")
							,coordinate.getLatitude()
							,coordinate.getLongitude()
							,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
					pm.makePersistent(ret);
				}
				else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.NewLayer.trim())==0){
					NLR nlr = new NLR(extractTagValue(str,"CATEGORY:")
							,coordinate.getLatitude()
							,coordinate.getLongitude()
							,placemark.getName()
							,GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
					pm.makePersistent(nlr);
				}

				//else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.TestPolygon.trim())==0)
				//{


				//}

			}

		}
		//pm.close();
	}
	
}

