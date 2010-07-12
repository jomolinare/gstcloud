package com.rmsi.lim.gstcloud.server;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gwt.user.client.Window;
import com.rmsi.lim.gstcloud.shared.Landmarks;

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
import java.util.List;
//import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class FileUp extends HttpServlet{
	
	//public static String s;
	private static final long serialVersionUID = 1L;

	PersistenceManager pm = PMF.get().getPersistenceManager();
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		{ 
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
                
 
                /*// Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                    
                    //response.getOutputStream().write(buffer, 0, len);
                 //s = buffer.toString();
                 System.out.println(buffer);*/
                 
                 //}
/*
                int maxFileSize = 10*(1024*2); //10 megs max 
                if (out.size() > maxFileSize) { 
                    System.out.println("File is > than " + maxFileSize);
                    return;
                }*/
            }
        }
          catch(Exception e){
            e.printStackTrace();
         }
        }      
    }
	
	
/*	public String FileReturn(){
    	return Global.str;
	}*/
	
	

	/*public void init(ServletConfig config) throws ServletException{
		super.init(config);
		}
  
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileUp.class.getName());

  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      ServletFileUpload upload = new ServletFileUpload();
      res.setContentType("text/plain");

      FileItemIterator iterator = upload.getItemIterator(req);
      while (iterator.hasNext()) {
        FileItemStream item = iterator.next();
        InputStream stream = item.openStream();

        if (item.isFormField()) {
          log.warning("Got a form field: " + item.getFieldName());
        } else {
          log.warning("Got an uploaded file: " + item.getFieldName() +
                      ", name = " + item.getName());

          // You now have the filename (item.getName() and the
          // contents (which you can read from stream).  Here we just
          // print them back out to the servlet output stream, but you
          // will probably want to do something more interesting (for
          // example, wrap them in a Blob and commit them to the
          // datastore).
          int len;
        byte[] buffer = new byte[8192];
           while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            res.getOutputStream().write(buffer, 0, len);
            String file = getServletContext().getRealPath(Global.str);
            
                res.setContentType("text/plain");
                PrintWriter out = res.getWriter();
            
                returnFile(file, out);
          }
        }
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

private void returnFile(String file, PrintWriter out) throws FileNotFoundException, IOException{
	Reader in = null;
	  	      try {
	        in = new BufferedReader(new FileReader(file));
	        char[] buf = new char[4 * 1024];  // 4K char buffer
	        int charsRead;
	  	        while ((charsRead = in.read(buf)) != -1) {
	          out.write(buf, 0, charsRead);
	        }
	      }
	  	      finally {
	        if (in != null) in.close();
	      }
	    }*/
	
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
			Landmarks land = new Landmarks("Landmark",coordinate.getLatitude(),coordinate.getLongitude(),placemark.getName());
			pm.makePersistent(land);
		}	
	}
}
