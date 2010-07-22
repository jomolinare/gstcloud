package com.rmsi.lim.gstcloud.client.utilities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.interfaces.LayerService;
import com.rmsi.lim.gstcloud.client.interfaces.LayerServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesService;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesServiceAsync;
import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.State;

public class GSTCloudUtils {

	/**
	  * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private static final SpatialBodiesServiceAsync spatialBodiesService = GWT
    .create(SpatialBodiesService.class);
	private static final LayerServiceAsync layerService = GWT
	.create(LayerService.class);
	/**
	 * Draws a circle at a specified radius with a green outline.this functions takes the following as its parameters.
	 * @param center
	 * @param radius
	 * @param nbOfPoints
	 * Earth's radius is taken in meters.The Latitude and Longitude are constituted in a single LatLng class.
	 * The number of points calculated is always 60 
	 */
	public static Polygon drawSearchCircleOnScreen(LatLng center, double radius,int nbOfPoints) 
	{
		
			 LatLngBounds bounds = LatLngBounds.newInstance();
			 LatLng[] circlePoints = new LatLng[nbOfPoints+1];

			 //double EARTH_RADIUS = 6371000;
			 double d = radius / GSTCloudConstants.EarthRadius;
			 double lat1 = Math.toRadians(center.getLatitude());
			 double lng1 = Math.toRadians(center.getLongitude());

			 double a = 0;
			 double step = 360.0 / (double) nbOfPoints;
			 for (int i = 0; i <= nbOfPoints; i++) 
			 {
				 double tc = Math.toRadians(a);
				 double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d) + Math.cos(lat1)* Math.sin(d) 
						                    * Math.cos(tc));
				 double lng2 = lng1 + Math.atan2(Math.sin(tc) * Math.sin(d) * Math.cos(lat1),
			                   Math.cos(d) - Math.sin(lat1) * Math.sin(lat2));
				 LatLng point = LatLng.newInstance(Math.toDegrees(lat2), Math.toDegrees(lng2));
				 circlePoints[i] = point;
				 bounds.extend(point);
				 a += step;
			 }

			 Polygon circle = new Polygon(circlePoints, "green", 1, 1, "green", 0);
			 //map.setCenter(center,10);
			 //map.addOverlay(circle);
			 return circle;
	}

	
	/**
	 *This is a function with hard coded values of 9 States.The properties include the state name,Latitude ,longitude
	 *and the zoom level for the respective state.
	 */	
	public static void StatesLoader()
	{
		 /*final State s1 = new State("Delhi",28.38, 77.12 ,10);
		 final State s2 = new State("Uttar Pradesh",27.40,80.00,9);
		 final State s3 = new State("Maharashtra",20.00,76.00,11);
		 final State s4 = new State("Kerala",10.00,76.25,12);
		 final State s5 = new State("Punjab",30.40,75.50,10);
		 final State s6 = new State("Haryana",30.30,74.60,8);
		 final State s7 = new State("Goa",28.00,72.00,9);
		 final State s8 = new State("Jammu and Kashmir",32.44,74.54,11);
		 final State s9 = new State("Gujarat",23.00,72.00,9);*/
		 final State s1 = new State("Delhi",28.635308,77.22496,0);
		 final State s2 = new State("Uttar Pradesh",27.5705886,80.0981869,0);
		 final State s3 = new State("Maharashtra",19.7514798,75.7138884,0);
		 final State s4 = new State("Kerala",10.5143884,76.6412712,0);
		 final State s5 = new State("Punjab",31.1471305,75.3412179,0);
		 final State s6 = new State("Haryana",29.0587757,	76.085601,0);
		 final State s7 = new State("Goa",15.4253792,73.9830029,0);
		 final State s8 = new State("Jammu and Kashmir",34.1490875,76.8259652,0);
		 final State s9 = new State("Gujarat",22.258652,71.1923805,0);
		 
		 
		 
		 
		 final AsyncCallback geoCallBack= new AsyncCallback<String>() 
			{
				public void onFailure(Throwable caught) 
				{
					//System.out.println("failure");	
				}
		//
				public void onSuccess(String result) 
				{
					System.out.println("success");
				} 
			};
			
			spatialBodiesService.loadStates(s1, geoCallBack);
			spatialBodiesService.loadStates(s2, geoCallBack);
			spatialBodiesService.loadStates(s3, geoCallBack);
			spatialBodiesService.loadStates(s4, geoCallBack);
			spatialBodiesService.loadStates(s5, geoCallBack);
			spatialBodiesService.loadStates(s6, geoCallBack);
			spatialBodiesService.loadStates(s7, geoCallBack);
			spatialBodiesService.loadStates(s8, geoCallBack);
			spatialBodiesService.loadStates(s9, geoCallBack);
	}
	/**
	 * 
	 *This is a function with hard coded values of 16 Districts for all the states loaded.The properties include 
	 *the stateId,District name ,Latitude and longitude for the respective district.
	 */
	public static void DistrictsLoader()
	{
		 /*final District d1 = new District("Delhi","East Delhi",28.53,77.13);
		 final District d2 = new District("Delhi","West Delhi",28.595,77.102);
		 final District d3 = new District("Delhi","South Delhi",28.500,77.100);
		 final District d4 = new District("Uttar Pradesh","Varanasi",25.20,83.00);
		 final District d5 = new District("Maharashtra","Bombay",18.55,72.54);
		 final District d6 = new District("Kerala","Ernakulam (Cochin)",10.00,76.15);
		 final District d7 = new District("Kerala","Kannur",11.52,75.25);
		 final District d8 = new District("Punjab","Amritsar",31.37,74.55);
		 final District d9 = new District("Punjab","Ludhiana",30.55,75.54);
		 final District d10 = new District("Punjab","Kaithal",29.48,78.26);
		 final District d11 = new District("Goa","Vasco",15.25,73.43);
		 final District d12 = new District("Jammu and Kashmir","Leh Ladakh",34.10,77.40);
		 final District d13 = new District("Jammu and Kashmir","Srinagar",30.40,77.00);
		 final District d14 = new District("Jammu and Kashmir","Jammu",32.43,74.54);
		 final District d15 = new District("Gujarat","Ahemdabad",23.03,72.40);
		 final District d16 = new District("Gujarat","Vadodra",22.00,73.16);*/
		 final District d1 = new District("Delhi","East Delhi",42.2989728,-74.8787711);
		 final District d2 = new District("Delhi","West Delhi",42.2995281,-75.0082191);
		 final District d3 = new District("Delhi","South Delhi",28.635308,77.22496);
		 final District d4 = new District("Uttar Pradesh","Varanasi",25.309722,	82.988611);
		 final District d5 = new District("Maharashtra","Bombay",19.017656,19.017656);
		 final District d6 = new District("Kerala","Ernakulam (Cochin)",10.0448261,76.3275467);
		 final District d7 = new District("Kerala","Kannur",	11.8688889,75.3555556);
		 final District d8 = new District("Punjab","Amritsar",31.63089,74.871552);
		 final District d9 = new District("Punjab","Ludhiana",30.90609,75.846786);
		 final District d10 = new District("Punjab","Kaithal",29.795441,76.399269);
		 final District d11 = new District("Goa","Vasco",15.4083333,73.7916667);
		 final District d12 = new District("Jammu and Kashmir","Leh Ladakh",34.1490875,76.8259652);
		 final District d13 = new District("Jammu and Kashmir","Srinagar",34.08278,74.808492);
		 final District d14 = new District("Jammu and Kashmir","Jammu",32.709743,	74.851969);
		 final District d15 = new District("Gujarat","Ahemdabad",23.039574,72.56602);
		 final District d16 = new District("Gujarat","Vadodra",22.306549,	73.187576);
		 
		 
		 
		 
		 final AsyncCallback geoCallBack= new AsyncCallback<String>() 
			{
				public void onFailure(Throwable caught) 
				{
					//System.out.println("failure");	
				}
		
				public void onSuccess(String result) 
				{
					System.out.println("success");
				} 
			};
			spatialBodiesService.loadDistricts(d1, geoCallBack);
			spatialBodiesService.loadDistricts(d2, geoCallBack);
			spatialBodiesService.loadDistricts(d3, geoCallBack);
			spatialBodiesService.loadDistricts(d4, geoCallBack);
			spatialBodiesService.loadDistricts(d5, geoCallBack);
			spatialBodiesService.loadDistricts(d6, geoCallBack);
			spatialBodiesService.loadDistricts(d7, geoCallBack);
			spatialBodiesService.loadDistricts(d8, geoCallBack);
			spatialBodiesService.loadDistricts(d9, geoCallBack);
			spatialBodiesService.loadDistricts(d10, geoCallBack);
			spatialBodiesService.loadDistricts(d11, geoCallBack);
			spatialBodiesService.loadDistricts(d12, geoCallBack);
			spatialBodiesService.loadDistricts(d13, geoCallBack);
			spatialBodiesService.loadDistricts(d14, geoCallBack);
			spatialBodiesService.loadDistricts(d15, geoCallBack);
			spatialBodiesService.loadDistricts(d16, geoCallBack);	
	}
	/**
	 *This is a function with hard coded values of 6 Local Bodies for all the Districts loaded.The properties include 
	 *the districtId,LocalBody name ,Latitude and longitude for the respective LocalBody.
	 */
	public static void LocalBodyLoader()
	{
		/*final LocalBody l1 = new LocalBody("South Delhi","Town","Chilla Saroda Bangar",28.29,77.00);
		final LocalBody l2 = new LocalBody("East Delhi","village","Kondli",28.11,77.29);
		final LocalBody l3 = new LocalBody("South Delhi","Town","Dwarka Sub City",28.19,77.00);
		final LocalBody l4 = new LocalBody("South Delhi","village","Najafgarh",27.11,77.909);
		final LocalBody l5 = new LocalBody("South Delhi","village","Bersarai",28.97,77.98);
		final LocalBody l6 = new LocalBody("South Delhi","Town","Hauz Khas",28.90,76.11);
		*/
		final LocalBody l1 = new LocalBody("South Delhi","Town","Chilla Saroda Bangar",	28.59577,77.30153);
		final LocalBody l2 = new LocalBody("East Delhi","village","Kondli",	28.61464,77.32613);
		final LocalBody l3 = new LocalBody("South Delhi","Town","Dwarka Sub City",28.6,77.0541667);
		final LocalBody l4 = new LocalBody("South Delhi","village","Najafgarh",28.6063932,76.9815877);
		final LocalBody l5 = new LocalBody("South Delhi","village","Bersarai",28.551276,77.1818935);
		final LocalBody l6 = new LocalBody("South Delhi","Town","Hauz Khas",28.5497644,77.1973997);
		
		
		final AsyncCallback geoCallBack= new AsyncCallback<String>() 
		{
			public void onFailure(Throwable caught) 
			{
				//System.out.println("failure");	
			}
	
			public void onSuccess(String result) 
			{
				System.out.println("success");
			} 
		};
		
		spatialBodiesService.loadLocalBody(l1, geoCallBack);
		spatialBodiesService.loadLocalBody(l2, geoCallBack);
		spatialBodiesService.loadLocalBody(l3, geoCallBack);
		spatialBodiesService.loadLocalBody(l4, geoCallBack);
		spatialBodiesService.loadLocalBody(l5, geoCallBack);
		spatialBodiesService.loadLocalBody(l6, geoCallBack);
		
		
	}
	
	/**
	 *This is a function with hard coded values of 7 Layers and their Types.The data include the name of the
	 *layer and its type (i.e point,polygon or line). 	 
	 */
	public static void layerLoader() 
	{
		final Layer l1 = new Layer("States","Polygon");
		final Layer l2 = new Layer("Districts","Polygon");
		final Layer l3 = new Layer("Local Body","Point");
		final Layer l4 = new Layer("Landmark","Point");
		final Layer l5 = new Layer("Tower","Point");
		final Layer l6 = new Layer("Retailer","Point");
		final Layer l7 = new Layer("Customer Service Center","Point");
		
		
		final AsyncCallback geoCallBack= new AsyncCallback<String>() 
		{
			public void onFailure(Throwable caught) 
			{
				//System.out.println("failure");	
			}
	
			public void onSuccess(String result) 
			{
				System.out.println("success");
			} 
		};
		
		layerService.loadLayer(l1, geoCallBack);
		layerService.loadLayer(l2, geoCallBack);
		layerService.loadLayer(l3, geoCallBack);
		layerService.loadLayer(l4, geoCallBack);
		layerService.loadLayer(l5, geoCallBack);
		layerService.loadLayer(l6, geoCallBack);
		layerService.loadLayer(l7, geoCallBack);
	}
	
	
	
}
