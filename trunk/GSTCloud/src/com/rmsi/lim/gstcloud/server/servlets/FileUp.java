package com.rmsi.lim.gstcloud.server.servlets;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.beoui.geocell.GeocellManager;
import com.google.gwt.user.client.Window;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudSharedConstants;
import com.rmsi.lim.gstcloud.server.model.Landmark;
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
			selectedLayer=(String)(request.getSession().getAttribute("selectedlayer"));
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
	
	private  void processPlacemark(Feature parentFeature, Placemark placemark)
	{
		Point point = (Point) placemark.getGeometry();
		List<Coordinate> coordinates = point.getCoordinates();
		
		for (Coordinate coordinate : coordinates) 
		{
			if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0)
			{
			Landmark land = new Landmark("Landmark",coordinate.getLatitude(),coordinate.getLongitude(),placemark.getName(),GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
			pm.makePersistent(land);
			}
			else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0){
			Tower twr = new Tower("Tower",placemark.getName(),"Uninor","Under Construction","Coverage",100.0,coordinate.getLatitude(),coordinate.getLongitude(),GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(coordinate.getLatitude(),coordinate.getLongitude())));
			pm.makePersistent(twr);
			}
		}	
	}
	
}
