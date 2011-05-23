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
		final State s1 = new State("Bihar",25.9077,85.8109,0);
		 final State s2 = new State("Uttar Pradesh",27.5705886,80.0981869,0);
		 final State s3 = new State("Maharashtra",19.7514798,75.7138884,0);
		 final State s4 = new State("Kerala",10.5143884,76.6412712,0);
		 final State s5 = new State("Punjab",31.1471305,75.3412179,0);
		 final State s6 = new State("Haryana",29.0587757,	76.085601,0);
		 final State s7 = new State("Goa",15.4253792,73.9830029,0);
		 final State s8 = new State("Jammu and Kashmir",34.1490875,76.8259652,0);
		 final State s9 = new State("Gujarat",22.258652,71.1923805,0);
		 final State s10 = new State("Delhi",28.635308,77.22496,0);
			 
		 
		 
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
			spatialBodiesService.loadStates(s10, geoCallBack);
	}
	/**
	 * 
	 *This is a function with hard coded values of 16 Districts for all the states loaded.The properties include 
	 *the stateId,District name ,Latitude and longitude for the respective district.
	 */
	public static void DistrictsLoader()
	{		
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
		 
		 final District d17 = new District("Bihar","Jehanabad",25.1683,84.8449);
		 final District d18 = new District("Bihar","Nalanda",25.2092,85.5369);
		 final District d19 = new District("Bihar","Patna",25.4838,85.386);
		 final District d20 = new District("Bihar","Vaishali",25.7638,85.3391);
		 
		 
		 
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
			spatialBodiesService.loadDistricts(d17, geoCallBack);
			spatialBodiesService.loadDistricts(d18, geoCallBack);
			spatialBodiesService.loadDistricts(d19, geoCallBack);
			spatialBodiesService.loadDistricts(d20, geoCallBack);
	}
	/**
	 *This is a function with hard coded values of 6 Local Bodies for all the Districts loaded.The properties include 
	 *the districtId,LocalBody name ,Latitude and longitude for the respective LocalBody.
	 */
	public static void LocalBodyLoader()
	{
		final LocalBody l1 = new LocalBody("South Delhi","Town","Chilla Saroda Bangar",	28.59577,77.30153);
		final LocalBody l2 = new LocalBody("East Delhi","village","Kondli",	28.61464,77.32613);
		final LocalBody l3 = new LocalBody("South Delhi","Town","Dwarka Sub City",28.6,77.0541667);
		final LocalBody l4 = new LocalBody("South Delhi","village","Najafgarh",28.6063932,76.9815877);
		final LocalBody l5 = new LocalBody("South Delhi","village","Bersarai",28.551276,77.1818935);
		final LocalBody l6 = new LocalBody("South Delhi","Town","Hauz Khas",28.5497644,77.1973997);
	
		final LocalBody l39 = new LocalBody("Nalanda","village","Dewara",25.2953,85.1545);
		final LocalBody l40 = new LocalBody("Nalanda","village","Indaut",25.3025,85.3147);
		final LocalBody l7 = new LocalBody("Nalanda","Town","Makhdumpur",25.084,84.9887);
		final LocalBody l8 = new LocalBody("Nalanda","Town","Silao",25.0824,84.4391);
		final LocalBody l13 = new LocalBody("Vaishali","Town","Lalganj ",25.8541,85.1785);
		final LocalBody l14 = new LocalBody("Vaishali","Town","Mokameh",25.3615,85.9071);
		final LocalBody l15 = new LocalBody("Vaishali","village","Baja Chak",26.0138,85.2529);
		final LocalBody l16 = new LocalBody("Vaishali","village","Nagwan",26.0033,85.2428);
		
		final LocalBody l9 = new LocalBody("Patna","Town","Mahnar Bazar",25.6092,85.4949);
		final LocalBody l10 = new LocalBody("Patna","Town","Phulwari Sharif",25.5696,85.0757);
		final LocalBody l11 = new LocalBody("Patna","village","Marai",25.7046,85.5775);
		final LocalBody l12 = new LocalBody("Patna","village","Arazi Chak Basdeo",25.7037,85.2986);
		final LocalBody l17 = new LocalBody("Patna","Town","Fatwah",25.5046,85.3059);
		final LocalBody l18 = new LocalBody("Patna","Town","Khusrupur",25.4883,85.374);
		final LocalBody l19 = new LocalBody("Patna","Town","Barh",25.4774,85.6788);
		final LocalBody l20 = new LocalBody("Patna","Town","Bakhtiarpur",25.415,85.5231);
		final LocalBody l21 = new LocalBody("Patna","Town","Rajgir ",25.0162,85.4434);
		final LocalBody l22 = new LocalBody("Patna","Town","Maner",25.6542,84.885);
		final LocalBody l23 = new LocalBody("Patna","Town","Khagaul",25.5824,85.0398);
		final LocalBody l24 = new LocalBody("Patna","village","Chak Kishun",25.748,85.4136);
		final LocalBody l25 = new LocalBody("Patna","village","Lachhmi Narayanpur",25.7466,85.4242);
		final LocalBody l26 = new LocalBody("Patna","village","Gurmain",25.7427,85.2568);
		final LocalBody l27 = new LocalBody("Patna","village","Mansingpur Rajauli",25.7289,85.2723);
		final LocalBody l28 = new LocalBody("Patna","village","Shankarpatti",25.7174,84.9522);
		final LocalBody l29 = new LocalBody("Patna","village","Saidpur Jhirua",25.7304,85.2539);
		final LocalBody l30 = new LocalBody("Patna","village","Bagh Asdullah Araz",25.715,85.2207);
		final LocalBody l31 = new LocalBody("Patna","village","Bishunpur Balbhada",25.7089,85.3624);
		final LocalBody l32 = new LocalBody("Patna","village","Jurawanpur Gopalpu",25.6176,85.3908);
		final LocalBody l33 = new LocalBody("Patna","village","Gorari",25.6064,85.3423);
		final LocalBody l34 = new LocalBody("Patna","village","Gokhulpur  Kurhar",25.6174,84.8666);
		
		final LocalBody l35 = new LocalBody("Jehanabad","Town","Hilsa",25.3079,85.2779);
		final LocalBody l36 = new LocalBody("Jehanabad","Town","Islampur",25.1464,85.2245);
		final LocalBody l37 = new LocalBody("Jehanabad","village","Jarkha",25.2888,84.8552);
		final LocalBody l38 = new LocalBody("Jehanabad","village","Chandaura",25.2977,85.5473);


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
		spatialBodiesService.loadLocalBody(l7, geoCallBack);
		spatialBodiesService.loadLocalBody(l8, geoCallBack);
		spatialBodiesService.loadLocalBody(l9, geoCallBack);
		spatialBodiesService.loadLocalBody(l10, geoCallBack);
		spatialBodiesService.loadLocalBody(l11, geoCallBack);
		spatialBodiesService.loadLocalBody(l12, geoCallBack);
		spatialBodiesService.loadLocalBody(l13, geoCallBack);
		spatialBodiesService.loadLocalBody(l14, geoCallBack);
		spatialBodiesService.loadLocalBody(l15, geoCallBack);
		spatialBodiesService.loadLocalBody(l16, geoCallBack);
		spatialBodiesService.loadLocalBody(l17, geoCallBack);
		spatialBodiesService.loadLocalBody(l18, geoCallBack);
		spatialBodiesService.loadLocalBody(l19, geoCallBack);
		spatialBodiesService.loadLocalBody(l20, geoCallBack);
		spatialBodiesService.loadLocalBody(l21, geoCallBack);
		spatialBodiesService.loadLocalBody(l22, geoCallBack);
		spatialBodiesService.loadLocalBody(l23, geoCallBack);
		spatialBodiesService.loadLocalBody(l24, geoCallBack);
		spatialBodiesService.loadLocalBody(l25, geoCallBack);
		spatialBodiesService.loadLocalBody(l26, geoCallBack);
		spatialBodiesService.loadLocalBody(l27, geoCallBack);
		spatialBodiesService.loadLocalBody(l28, geoCallBack);
		spatialBodiesService.loadLocalBody(l29, geoCallBack);
		spatialBodiesService.loadLocalBody(l30, geoCallBack);
		spatialBodiesService.loadLocalBody(l31, geoCallBack);
		spatialBodiesService.loadLocalBody(l32, geoCallBack);
		spatialBodiesService.loadLocalBody(l33, geoCallBack);
		spatialBodiesService.loadLocalBody(l34, geoCallBack);
		spatialBodiesService.loadLocalBody(l35, geoCallBack);
		spatialBodiesService.loadLocalBody(l36, geoCallBack);
		spatialBodiesService.loadLocalBody(l37, geoCallBack);
		spatialBodiesService.loadLocalBody(l38, geoCallBack);
		spatialBodiesService.loadLocalBody(l39, geoCallBack);
		spatialBodiesService.loadLocalBody(l40, geoCallBack);
	}
	
	/**
	 *This is a function with hard coded values of 7 Layers and their Types.The data include the name of the
	 *layer and its type (i.e point,polygon or line). 	 
	 */
	public static void layerLoader() 
	{
//		final Layer l1 = new Layer("States","Polygon");
//		final Layer l2 = new Layer("Districts","Polygon");
//		final Layer l3 = new Layer("Local Body","Point");
		String[] arrayLandmark = {"CurrentlySelected","Latitude","Longitude","Category","PlaceName"};
		//String[] arrayLandmark = {"CurrentlySelected","Category","Latitude","Longitude","PlaceName"};
		String[] arrayLandmarkDisplay = {"Selected","Latitude","Longitude","Category","PlaceName"};
		String[] arrayLandmarkTypes = {"String","Double","Double","String","String"};
		String[] arrayFilterLandmark = {"Category","PlaceName"};
		String[] arrayFilterLandmarkTypes = {"String","String"};
		final Layer l4 = new Layer("Landmark","Landmark","Point",
				arrayLandmark,arrayLandmarkDisplay,arrayLandmarkTypes,
				arrayFilterLandmark,arrayFilterLandmarkTypes);
		
		String[] arrayTower = {"CurrentlySelected","Latitude","Longitude","Category","Coverage","Height", "Name","Owner","Status"};
		//String[] arrayTower = {"CurrentlySelected","Category","Coverage","Height","Latitude","Longitude", "Name","Owner","Status"};
		String[] arrayTowerDisplay = {"Selected","Latitude","Longitude","Category","Coverage","Height", "Name","Owner","Status"};
		String[] arrayTowerTypes = {"String","Double","Double","String","String","Double","String","String","String"};
		String[] arrayFilterTower = {"Name","Owner","Category"};
		String[] arrayFilterTowerTypes = {"String","String","String"};
		final Layer l5 = new Layer("Tower","Tower","Point",
				arrayTower,arrayTowerDisplay,arrayTowerTypes,
				arrayFilterTower,arrayFilterTowerTypes);
		
		String[] arrayRetailer = {"CurrentlySelected","Latitude","Longitude","Address","Category","Name"};
		//String[] arrayRetailer = {"CurrentlySelected","Address","Category","Latitude","Longitude","Name"};
		String[] arrayRetailerDisplay = {"Selected","Latitude","Longitude","Address","Category","Name"};
		String[] arrayRetailerTypes = {"String","Double","Double","String","String","String"};
		String[] arrayFilterRetailer = {"Name","Category"};
		String[] arrayFilterRetailerTypes = {"String","String"};
		final Layer l6 = new Layer("Retailer","Retailer","Point",
				arrayRetailer,arrayRetailerDisplay,arrayRetailerTypes,
				arrayFilterRetailer,arrayFilterRetailerTypes);
		
		String[] arrayCustomerServiceCenter = {"CurrentlySelected","Latitude","Longitude","Address","Category","Contact_person","Name","Tower_name"};
		//String[] arrayCustomerServiceCenter = {"CurrentlySelected","Address","Category","Contact_person","Name","Latitude","Longitude","Tower_name"};
		String[] arrayCustomerServiceCenterDisplay = {"Selected","Latitude","Longitude","Address","Category","Contact_person","Name","Tower_name"};
		String[] arrayCustomerServiceCenterTypes = {"String","Double","Double","String","String","String","String","String"};
		String[] arrayFilterCustomerServiceCenter  = {"Category","Name"};
		String[] arrayFilterCustomerServiceCenteTypes = {"String","String"};
		final Layer l7 = new Layer("Customer Service Center","Customer Service Center","Point",
				arrayCustomerServiceCenter,arrayCustomerServiceCenterDisplay,arrayCustomerServiceCenterTypes,
				arrayFilterCustomerServiceCenter,arrayFilterCustomerServiceCenteTypes);
		
		final Layer l8 = new Layer("NewLayer","Point");
		
		final Layer polObj = new Layer("TestPolygon","Polygon");
		
		String[] arrayWardBoundaries = {"Category","Latitude","Longitude","PlaceName","PCord","CityName","StreetName", "WardName" ,"WardNum","TotalPop"};
		String[] arrayWardBoundariesDisplay = {"Category","Latitude","Longitude","PlaceName","PCord","CityName","StreetName", "WardName" ,"WardNum","TotalPop"};
		String[] arrayWardBoundariesTypes = {"String","Double","Double","String","String","String","String","String","String","Double"};
		String[] arrayFilterWardBoundaries = {"PlaceName","Category"};
		String[] arrayFilterWardBoundariesTypes = {"String","String"};
		final Layer l10 = new Layer("WardBoundaries","WardBoundaries","Polygon",
				arrayWardBoundaries,arrayWardBoundariesDisplay,arrayWardBoundariesTypes,
				arrayFilterWardBoundaries,arrayFilterWardBoundariesTypes);
		
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
		
//		layerService.loadLayer(l1, geoCallBack);
//		layerService.loadLayer(l2, geoCallBack);
//		layerService.loadLayer(l3, geoCallBack);
		layerService.loadLayer(l4, geoCallBack);
		layerService.loadLayer(l5, geoCallBack);
		layerService.loadLayer(l6, geoCallBack);
		layerService.loadLayer(l7, geoCallBack);
		layerService.loadLayer(l8, geoCallBack);
		layerService.loadLayer(polObj, geoCallBack);
		layerService.loadLayer(l10, geoCallBack);
	}
	
	
	
}
