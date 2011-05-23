package com.rmsi.lim.gstcloud.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudService;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarkService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarkServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LayerService;
import com.rmsi.lim.gstcloud.client.interfaces.LayerServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.RowSelectionListener;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesService;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesServiceAsync;
import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.State;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.rmsi.lim.gstcloud.client.utilities.FieldVerifier;
import com.rmsi.lim.gstcloud.client.view.AdvancedTable;
import com.rmsi.lim.gstcloud.client.view.LayerItem;
import com.rmsi.lim.gstcloud.client.view.LayerManager;
import com.rmsi.lim.gstcloud.client.view.LayerTree;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import java.util.HashMap;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.IsSerializable;
/**
* Entry point classes define <code>onModuleLoad()</code>.
*/
@SuppressWarnings("deprecation")
public class GSTCloud implements EntryPoint 
{
	/*
	*//**
	 * Map elements in the UI
	 *//*
	private MapWidget map;
	private VerticalPanel mapWrapper = new VerticalPanel();
	*//**
	 * Other UI elements
	 *//*
	private static final String applicationTitle1 = "Site Search";*/
	private static final String applicationTitle2 = "RMSI in the Cloud ";/*
	private static final String applicationTitle3 = "Tools";
	private static final String applicationTitle4 = "Admin Block";
	
	private HorizontalPanel hp = new HorizontalPanel();
	private HorizontalPanel tablePanel=new HorizontalPanel();
	
	private VerticalPanel vp = new VerticalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	private VerticalPanel vp2 = new VerticalPanel();
	
	private DialogBox dialogBox;
	
	private HTML serverResponseLabel;*/
	private HTML title1,title2,title3,title4;/*
  
	private Label textToServerLabel = new Label();
	
	TabPanel queryTabPanel = new TabPanel();
	final FormPanel form = new FormPanel();
	*/
	final FileUpload upload = new FileUpload();/*
	final Button loadButton = new Button("Load");
	final Button displayButton = new Button ("Display");
	public Button submitButton =new Button("submit");
	private Button closeButton;
	
	private Geocoder geocoder;
	private HorizontalPanel tablePanel1 = new HorizontalPanel();
	private HorizontalPanel hp1=new HorizontalPanel();
    private Label labelMessages = new Label();
	private AdvancedTable datagrid = new AdvancedTable();*/
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	GSTCloudUI uiObject =new GSTCloudUI();

	/**
	  * Create a remote service proxy to talk to the server-side Greeting service.
	 *//*
	private final GisCloudServiceAsync gisCloudService = GWT
			.create(GisCloudService.class);
	private final LandmarkServiceAsync fea = GWT
		    .create(LandmarkService.class);
	private final SpatialBodiesServiceAsync dea = GWT
	        .create(SpatialBodiesService.class);
	private final LayerServiceAsync layerService = GWT
    		.create(LayerService.class);
	private final LandmarksTableModelServiceAsync landmarksModelService=GWT.create(LandmarksTableModelService.class);
	LayerManager lm = new LayerManager();
	*//**
	 * Draws a circle at a specified radius with a green outline.this functions takes the following as its parameters.
	 * @param center
	 * @param radius
	 * @param nbOfPoints
	 * Earth's radius is taken in meters.The Latitude and Longitude are constituted in a single LatLng class.
	 * The number of points calculated is always 60 
	 *//*
	public void drawCircleFromRadius(LatLng center, double radius,int nbOfPoints) 
	{
		
			 LatLngBounds bounds = LatLngBounds.newInstance();
			 LatLng[] circlePoints = new LatLng[nbOfPoints];

			 double EARTH_RADIUS = 6371000;
			 double d = radius / EARTH_RADIUS;
			 double lat1 = Math.toRadians(center.getLatitude());
			 double lng1 = Math.toRadians(center.getLongitude());

			 double a = 0;
			 double step = 360.0 / (double) nbOfPoints;
			 for (int i = 0; i < nbOfPoints; i++) 
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
			 map.setCenter(center,10);
			 map.addOverlay(circle);
			 return ;
	}*//*
	*//**
	 *This is a function with hard coded values of 4 Layers and their Types.The data include the name of the
	 *layer and its type (i.e point,polygon or line). 	 
	 *//*
	private void layerLoader() 
	{
		final Layer l1 = new Layer("States","Polygon");
		final Layer l2 = new Layer("Districts","Polygon");
		final Layer l3 = new Layer("Local Body","Point");
		final Layer l4 = new Layer("Landmark","Point");
		
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
	}
	*//**
	 *This is a function with hard coded values of 9 States.The properties include the state name,Latitude ,longitude
	 *and the zoom level for the respective state.
	 *//*
	private void StatesLoader()
	{
		 final State s1 = new State("Delhi",28.38, 77.12 ,10);
		 final State s2 = new State("Uttar Pradesh",27.40,80.00,9);
		 final State s3 = new State("Maharashtra",20.00,76.00,11);
		 final State s4 = new State("Kerala",10.00,76.25,12);
		 final State s5 = new State("Punjab",30.40,75.50,10);
		 final State s6 = new State("Haryana",30.30,74.60,8);
		 final State s7 = new State("Goa",28.00,72.00,9);
		 final State s8 = new State("Jammu and Kashmir",32.44,74.54,11);
		 final State s9 = new State("Gujarat",23.00,72.00,9);
	
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
			
			dea.loadStates(s1, geoCallBack);
			dea.loadStates(s2, geoCallBack);
			dea.loadStates(s3, geoCallBack);
			dea.loadStates(s4, geoCallBack);
			dea.loadStates(s5, geoCallBack);
			dea.loadStates(s6, geoCallBack);
			dea.loadStates(s7, geoCallBack);
			dea.loadStates(s8, geoCallBack);
			dea.loadStates(s9, geoCallBack);
	}
	
	*//**
	 *This is a function with hard coded values of 16 Districts for all the states loaded.The properties include 
	 *the stateId,District name ,Latitude and longitude for the respective district.
	 *//*
	private void DistrictsLoader()
	{
		 final District d1 = new District("Delhi","East Delhi",28.53,77.13);
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
		 final District d16 = new District("Gujarat","Vadodra",22.00,73.16);
	
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
			dea.loadDistricts(d1, geoCallBack);
			dea.loadDistricts(d2, geoCallBack);
			dea.loadDistricts(d3, geoCallBack);
			dea.loadDistricts(d4, geoCallBack);
			dea.loadDistricts(d5, geoCallBack);
			dea.loadDistricts(d6, geoCallBack);
			dea.loadDistricts(d7, geoCallBack);
			dea.loadDistricts(d8, geoCallBack);
			dea.loadDistricts(d9, geoCallBack);
			dea.loadDistricts(d10, geoCallBack);
			dea.loadDistricts(d11, geoCallBack);
			dea.loadDistricts(d12, geoCallBack);
			dea.loadDistricts(d13, geoCallBack);
			dea.loadDistricts(d14, geoCallBack);
			dea.loadDistricts(d15, geoCallBack);
			dea.loadDistricts(d16, geoCallBack);	
	}
	
	*//**
	 *This is a function with hard coded values of 6 Local Bodies for all the Districts loaded.The properties include 
	 *the districtId,LocalBody name ,Latitude and longitude for the respective LocalBody.
	 *//*
	private void LocalBodyLoader()
	{
		final LocalBody l1 = new LocalBody("South Delhi","Town","Chilla Saroda Bangar",28.29,77.00);
		final LocalBody l2 = new LocalBody("East Delhi","village","Kondli",28.11,77.29);
		final LocalBody l3 = new LocalBody("South Delhi","Town","Dwarka Sub City",28.19,77.00);
		final LocalBody l4 = new LocalBody("South Delhi","village","Najafgarh",27.11,77.909);
		final LocalBody l5 = new LocalBody("South Delhi","village","Bersarai",28.97,77.98);
		final LocalBody l6 = new LocalBody("South Delhi","Town","Hauz Khas",28.90,76.11);
		
		
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
		
		dea.loadLocalBody(l1, geoCallBack);
		dea.loadLocalBody(l2, geoCallBack);
		dea.loadLocalBody(l3, geoCallBack);
		dea.loadLocalBody(l4, geoCallBack);
		dea.loadLocalBody(l5, geoCallBack);
		dea.loadLocalBody(l6, geoCallBack);
		
		
	}
	*//*
	*//**
	 * This panel contains the Text boxes for entering Latitude, Longitude and the Specified Radius and 
	 * contains the buttons Search and clear.Search is used to search according to the textbox values and clear
	 *  is to clear all the overlays displayed on the map.
	 *//*
	private void setupLatLongPanel()
	{
		VerticalPanel vLatLongPanel = new VerticalPanel();
	    HorizontalPanel hLtLngPanel = new HorizontalPanel();

		final Button latLongSearch = new Button("Search ");
			latLongSearch.setStylePrimaryName("Button");
			hLtLngPanel.add(latLongSearch);
		final Button latLongClear = new Button("Clear");
			latLongClear.setStylePrimaryName("Button");
			hLtLngPanel.add(latLongClear);
		
		final TextBox latitudeBox = new TextBox();
		final TextBox longitudeBox = new TextBox();
		final TextBox SpeRadBox = new TextBox();
		
		Label latLabel = new Label("P1 Latitude");
		Label longLabel = new Label("P1 Longitude");
	    Label speRadLabel = new Label("Specified Radius");
		
	    final Label errorLabel = new Label();
	    
	    class latLongHandler implements ClickHandler, KeyUpHandler
		{
			public void onClick(ClickEvent event)
			{
				if (event.getSource()==latLongSearch)	
				{	
					Double radius = new Double(SpeRadBox.getText());
					
					errorLabel.setText("");
					String latChck = new String();
					latChck = latitudeBox.getText();
					String longChck = new String();
					longChck = longitudeBox.getText();
					if (!FieldVerifier.isaNumber(latChck,longChck))
					{
						errorLabel.setText("Enter only digits");
						dialogBox.setWidget(errorLabel);
						return;
					}
					String latCheck = new String (latitudeBox.getText());
					String longCheck = new String (longitudeBox.getText());
					if (!FieldVerifier.isValidNumber(latCheck,longCheck)) 
					{
						errorLabel.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
						return;
					}
									
					Double lat = new Double(latitudeBox.getText());	
					Double lng = new Double(longitudeBox.getText());
					LatLng point = LatLng.newInstance(lat,lng);
					drawCircleFromRadius(point,radius,60);
					map.setCenter(point,10);
				}
				else if (event.getSource()==latLongClear)
				{
					map.clearOverlays();
				}
			}
			public void onKeyUp(KeyUpEvent event) 
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				{
					Double radius = new Double(SpeRadBox.getText());
					
					errorLabel.setText("");
					String latChck = new String();
					latChck = latitudeBox.getText();
					String longChck = new String();
					longChck = longitudeBox.getText();
					if (!FieldVerifier.isaNumber(latChck,longChck))
					  {
						errorLabel.setText("Enter only digits");
						return;
					  }
					String latCheck = new String (latitudeBox.getText());
					String longCheck = new String (longitudeBox.getText());
					if (!FieldVerifier.isValidNumber(latCheck,longCheck)) 
					  {
						errorLabel.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
						return;
					  }
					
					Double lat = new Double(latitudeBox.getText());	
					Double lng = new Double(longitudeBox.getText());
					LatLng point = LatLng.newInstance(lat,lng);
					drawCircleFromRadius(point,radius,60);
					map.setCenter(point);
					 
				}
			}
		}
	    
	    final latLongHandler latLngSearchHandler = new latLongHandler();
	    
	    vLatLongPanel.setSize("200px", "450px");
		
	    vLatLongPanel.add(latLabel);
	    vLatLongPanel.add(latitudeBox);
			latitudeBox.setText("Enter Lattitude");
			latitudeBox.setWidth("180px");
			latitudeBox.addKeyUpHandler(latLngSearchHandler);
		vLatLongPanel.add(longLabel);
		vLatLongPanel.add(longitudeBox);
			longitudeBox.setText("Enter Longitude");
			longitudeBox.setWidth("180px");
			longitudeBox.addKeyUpHandler(latLngSearchHandler);
		vLatLongPanel.add(speRadLabel);
	    vLatLongPanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
		
	    vLatLongPanel.add(hLtLngPanel);
		queryTabPanel.add(vLatLongPanel, "LatLong");
		queryTabPanel.selectTab(0);
		
		latLongSearch.addClickHandler(latLngSearchHandler);
		latLongClear.addClickHandler(latLngSearchHandler);
	}
	
	*//**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 *//*
	
	private void setupKeywordPanel()
	{
		VerticalPanel vKeyWordPanel = new VerticalPanel();
		HorizontalPanel hKeyWrdPanel = new HorizontalPanel();
		
		Label addLabel = new Label("Address :");
		Label speRadLabel = new Label("Specified Radius");
		
		final TextBox addressBox = new TextBox();
		final TextBox SpeRadBox = new TextBox();
		
		final Button addressSearch = new Button("Search ");
			addressSearch.setStylePrimaryName("Button");
			hKeyWrdPanel.add(addressSearch);
		final Button addressClear = new Button("Clear");
			addressClear.setStylePrimaryName("Button");
			hKeyWrdPanel.add(addressClear);
		
		vKeyWordPanel.setSize("250px", "550px");
		
		vKeyWordPanel.add(addLabel);
		vKeyWordPanel.add(addressBox);
			addressBox.setWidth("180px");
		
		vKeyWordPanel.add(speRadLabel);
		vKeyWordPanel.add(SpeRadBox);
			SpeRadBox.setWidth("120px"); 
		
		vKeyWordPanel.add(hKeyWrdPanel);
		queryTabPanel.add(vKeyWordPanel ,"Keyword");
		
		class keyWordHandler implements ClickHandler
		{
			public void onClick(ClickEvent event)
			{
				if (event.getSource()==addressSearch)	
				{
					String boxText = new String();
					boxText = addressBox.getText().trim().replace("'", "\\'");
					
					fea.searchLandmarkByName(boxText,new AsyncCallback<List<LandmarkDTO>>()
					{
						public void onFailure(Throwable caught) 
						{
								
						}

					    public void onSuccess(List<LandmarkDTO> result ) 
					    {
					    	Double radius = new Double(SpeRadBox.getText());

							int rowCount = result.size();
							for (int row = 0; row < rowCount; row ++)
							{
								Double lat = result.get(row).getLatitude();
								Double lng = result.get(row).getLongitude();
								
								LatLng point = LatLng.newInstance(lat,lng);
//								// Add a marker
					   	          map.addOverlay(new Marker(point));
					   	          drawCircleFromRadius(point,radius,60);
					   	          map.setCenter(point, 10);
								// Add an info window to highlight a point of interest
						    	  map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
							}
							
					    }
					});
				}
				else if (event.getSource() == addressClear)
				{
					map.clearOverlays();
				}
			}
		}
		final keyWordHandler addressSearchHandler = new keyWordHandler();
		addressSearch.addClickHandler(addressSearchHandler);
		addressClear.addClickHandler(addressSearchHandler);
	}
	
	*//**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 *//*
	private void setupSpatialPanel()
	{
		Boolean dropdown = false;
		
		VerticalPanel vSpatialPanel = new VerticalPanel();
		HorizontalPanel hSpatPanel = new HorizontalPanel();
		
		Label stateLabel = new Label("State:");
		Label districtLabel = new Label("District:");
		Label localBodyLabel = new Label("Local Body:");
		Label speRadLabel = new Label("Specified Radius");
		
		final ListBox stateBox = new ListBox();
		final ListBox districtBox = new ListBox();
		final ListBox localBodyBox = new ListBox();
		final TextBox SpeRadBox = new TextBox();
		
		final Button spatialSearch = new Button("Search ");
			spatialSearch.setStylePrimaryName("Button");
			hSpatPanel.add(spatialSearch);
		final Button spatialClear = new Button("Clear");
			spatialClear.setStylePrimaryName("Button");
  			hSpatPanel.add(spatialClear);
		
  		vSpatialPanel.setSize("250px", "550px");

  		vSpatialPanel.add(stateLabel);           
  		vSpatialPanel.add(stateBox);
  			stateBox.setWidth("180px");
  			stateBox.addItem("Select State");
  		dea.getStates(new AsyncCallback<List<State>>()
  		{
  			public void onFailure(Throwable caught) 
			 	{		 
		     
			 	}
  		
			 	public void onSuccess(List<State> result) 
				{
			 		String state = new String();
			 		int rowCount = result.size();
			 		for (int row = 0; row < rowCount; row ++) 
			 		{
			 			state = result.get(row).getStateName();
			 			stateBox.addItem(state);
			 		}
				}
  		});
  	    if(!dropdown)
  	    	stateBox.setVisibleItemCount(1);
        
  	    districtBox.addItem("No Available Districts");
        localBodyBox.addItem("No Available Villages/Towns");
        
		vSpatialPanel.add(districtLabel);
		vSpatialPanel.add(districtBox);
			districtBox.setWidth("180px"); 
	    vSpatialPanel.add(localBodyLabel);
	    vSpatialPanel.add(localBodyBox);
	    	localBodyBox.setWidth("180px"); 
	    vSpatialPanel.add(speRadLabel);
	    vSpatialPanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
	    	
	    class SpatialHandler implements ClickHandler, ChangeHandler
	    {
			public void onClick(ClickEvent event)
			{
				 try 
				 {
					 if (event.getSource()== spatialSearch)	
				 
					 {
						 int selectedIndex=localBodyBox.getSelectedIndex();
						 if(selectedIndex==-1 || localBodyBox.getItemText(selectedIndex )== "Select Villages/Town"||localBodyBox.getItemText(selectedIndex )== "No Available Villages/Town")
						 {
							 selectedIndex = districtBox.getSelectedIndex();
							 if(selectedIndex==-1 ||districtBox.getItemText( selectedIndex)== "Select District"||districtBox.getItemText( selectedIndex)=="No Available Districts")
							 {
								 selectedIndex=stateBox.getSelectedIndex();
								 if(stateBox.getItemText( selectedIndex)== "Select State")
								 {
									 dialogBox.setText("Atleast Enter name of the State");
								 }	
								 else
								 {
									 String sName = stateBox.getItemText( stateBox.getSelectedIndex());
									 *//**
									  * This function call brings the attributes of the state by the name specified.
									  *//*
									 dea.getStateByName(sName,new AsyncCallback<State>()
										{
											public void onFailure(Throwable caught) 
											{
												
											}

											public void onSuccess(State result ) 
											{									
												LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
												map.addOverlay(new Marker(point));
												map.setCenter(point,result.getZoomLevel());
												drawCircleFromRadius(point,new Double(SpeRadBox.getText()),60);
											}
										});
								 }
							 }
							 else
							 {
								 String dName = districtBox.getItemText( districtBox.getSelectedIndex());
								 *//**
								  * This function call brings the attributes of the District by the name specified.
								  *//*
								 dea.getDistrictByName(dName,new AsyncCallback<District>()			
										 {
											public void onFailure(Throwable caught) 
											{
											
											}

											public void onSuccess(District result ) 
											{
												LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
												map.addOverlay(new Marker(point));
												map.setCenter(point,15);
												drawCircleFromRadius(point,new Double(SpeRadBox.getText()),60);
											}
										 });
							 }
						 }
						 else
						 {
							 String lbName = localBodyBox.getItemText( localBodyBox.getSelectedIndex());
							 *//**
							  * This function call brings the attributes of the Local Body by the name specified.
							  *//*
							 dea.getLocalBodyByName(lbName,new AsyncCallback<LocalBody>()			
									 {
										public void onFailure(Throwable caught) 
										{
										
										}

										public void onSuccess(LocalBody result ) 
										{
											LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
											map.addOverlay(new Marker(point));
											map.setCenter(point,15);
											drawCircleFromRadius(point,new Double(SpeRadBox.getText()),60);
										}
									 });
						 }
				}
				*//**
				 * Deletes all the Overlays displayed on the map
				 *//*
				else if (event.getSource()==spatialClear)
					{
						map.clearOverlays();
					}
				 }catch (Exception ex) 
				 {
					 System.out.println(ex.getMessage());
				 }
			}
			
			public void onChange(ChangeEvent event)
			{
				try{
					
				
				if (event.getSource() == stateBox)
				{
					String stateText = new String();
					int selectedIndex=stateBox.getSelectedIndex();
					stateText=stateBox.getItemText(selectedIndex);
					
					int count = districtBox.getItemCount();	
			 		for (int ctr1 = count-1; ctr1 >=0;ctr1--)
			 		{
			 			districtBox.removeItem(ctr1);
			 		}

			 			districtBox.addItem("No Available Districts");
			        int count2 = localBodyBox.getItemCount();	
			 		for (int ctr2 = count2-1; ctr2 >=0;ctr2--)
			 			localBodyBox.removeItem(ctr2);
			 			localBodyBox.addItem("No Available Villages/Towns");
					if(stateText!="Select State")
					dea.getDistrictsByStateName(stateText, new AsyncCallback<List<District>>(){
						public void onFailure(Throwable caught) 
					 	{		 
							
					 	}
		  		
					 	public void onSuccess(List<District> result) 
						{
					 		int count3 = districtBox.getItemCount();	
					 		for (int ctr3 = count3-1; ctr3 >=0 ;ctr3--)
					 			districtBox.removeItem(ctr3);
					 			districtBox.addItem("Select District");
					 		for (int row = 0; row < result.size(); row ++) 
					 			districtBox.addItem(result.get(row).getDistrictName());
					 		
						}
						
						
					});
					
				}
				
				else if(event.getSource() == districtBox)
				{
					String districtText = new String();
					int selectedIndex=districtBox.getSelectedIndex();
					districtText=districtBox.getItemText(selectedIndex);
					int count = localBodyBox.getItemCount();	
			 		for (int i = count-1; i >=0;i--)
			 			localBodyBox.removeItem(i);
			 			localBodyBox.addItem("No Available Villages/Towns");
					if (districtText!="Select District")
					dea.getLocalBodiesByDistrictName(districtText, new AsyncCallback<List<LocalBody>>(){
						public void onFailure(Throwable caught) 
					 	{		 
							
					 	}
		  		
					 	public void onSuccess(List<LocalBody> result) 
						{
					 		int count = localBodyBox.getItemCount();	
					 		for (int i = count-1; i >=0;i--)
					 			localBodyBox.removeItem(i);
					 			localBodyBox.addItem("Select Villages/Town");
					 		for (int row = 0; row < result.size(); row ++) 
					 			localBodyBox.addItem(result.get(row).getLocalBodyName());
					 		
						}
						
						
					});
					
				}
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
	    }
	    
	    final SpatialHandler spatHandler = new SpatialHandler();		
	    
	    //stateBox.addClickHandler(spatHandler);
	    stateBox.addChangeHandler(spatHandler);
	    districtBox.addChangeHandler(spatHandler);
	    spatialSearch.addClickHandler(spatHandler);
	    spatialClear.addClickHandler(spatHandler);
	    vSpatialPanel.add(hSpatPanel);
		queryTabPanel.add(vSpatialPanel, "Spatial");
	}

	*//**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 *//*
	
	private void setupAttributePanel()
	{
		VerticalPanel vAttributePanel = new VerticalPanel();
		HorizontalPanel hAttributePanel = new HorizontalPanel();
		
		Label queryIDLabel = new Label("Address:");
		Label speRadLabel = new Label("Specified Radius");
		
		final TextBox queryIDBox = new TextBox();
	    final TextBox SpeRadBox = new TextBox();
	    
	    final Button attributeSearch = new Button("Search ");
	    	attributeSearch.setStylePrimaryName("Button");
	    final Button attributeClear = new Button("Clear");
	    	attributeClear.setStylePrimaryName("Button");
	    
	    vAttributePanel.add(queryIDLabel);
	    vAttributePanel.add(queryIDBox);
	    	queryIDBox.setWidth("120px"); 
	    vAttributePanel.add(speRadLabel);
	    vAttributePanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
	         
	    hAttributePanel.add(attributeSearch);
	    hAttributePanel.add(attributeClear);
		vAttributePanel.add(hAttributePanel);	    
		
	    queryTabPanel.add(vAttributePanel, "Address");
	    
	    class AttributeHandler implements ClickHandler
		{
			public void onClick(ClickEvent event)
			{
				if (event.getSource()==attributeSearch)	
				{
					String boxText = new String();
					boxText = queryIDBox.getText().trim().replace("'", "\\'");
					showAddress(boxText);
					}
				else if (event.getSource() == attributeClear)
				{
					map.clearOverlays();
				}
			}
		}
		final AttributeHandler addressSearchHandler = new AttributeHandler();
		attributeSearch.addClickHandler(addressSearchHandler);
		attributeClear.addClickHandler(addressSearchHandler);
	    
	    
	    
	}
	
	private void showAddress(final String address) {
	    //InfoWindow info = map.getInfoWindow();
	    geocoder.getLatLng(address, new LatLngCallback() {
	      public void onFailure() {
	        //Window.alert(address + " not found");
	    	  System.out.println(address+ "not found");
	      }
	      public void onSuccess(LatLng point) {
	          map.setCenter(point, 13);
	          Marker marker = new Marker(point);
	          map.addOverlay(marker);
	          //info.open(marker, new InfoWindowContent(address));
	          //displayLatLng(point);
	        }
	      });
	}
	
	private void setupQueryPanels()
	{
		vp1.setBorderWidth(2);
		vp1.setStyleName("VerticalPanel");
		title1 = new HTML(applicationTitle1);
		title1.setStylePrimaryName("titlet");
		vp1.add(title1);
		vp1.setCellHorizontalAlignment(title1,HasHorizontalAlignment.ALIGN_LEFT);
		vp1.setSize("250px", "470px");
	
		VerticalPanel admin = new VerticalPanel();
		admin.setStyleName("VerticalPanel");
		title4 = new HTML(applicationTitle4);
		title4.setStylePrimaryName("titlet");
		admin.add(title4);
		admin.setCellHorizontalAlignment(title4,HasHorizontalAlignment.ALIGN_LEFT);
		admin.setSize("250px", "170px");
		admin.setVisible(true);
		
	    HorizontalPanel hp5 = new HorizontalPanel();
		hp5.add(upload);
	    
	    loadButton.setStylePrimaryName("Button");
		loadButton.addClickHandler(handler);
		hp5.add(loadButton);
		
		displayButton.setStylePrimaryName("Button");
		displayButton.addClickHandler(handler);
		hp5.add(displayButton);
     
		  //Add to the Query panel
		vp1.add(queryTabPanel);
		vp1.add(admin);
		admin.add(hp5);
						
	}
	
	class MyHandler implements ClickHandler
	{
		
		public void onClick(ClickEvent event)
		{
			
			if (event.getSource()==loadButton)
			{
				final AsyncCallback geoCallBack= new AsyncCallback<String>() 
				{
					public void onFailure(Throwable caught) 
					{
						System.out.println("failure");	
					}

					public void onSuccess(String result) 
					{
						System.out.println("success");
					} 
				};
				
				fea.loadKML(upload.getFilename(), new AsyncCallback<String>() {
					public void onFailure(Throwable caught) 
					{
						System.out.println("failure");	
					}

					public void onSuccess(String result) 
					{
						System.out.println("success");
					}
						
						
				});			
				System.out.println("file uploaded");
			}
			
			

			
			else if (event.getSource()==displayButton) 
					 fea.getLandMarks(new AsyncCallback<List<LandmarkDTO>>() 
					 {
						 public void onFailure(Throwable caught) 
						 {
							 
					     }

						 public void onSuccess(List<LandmarkDTO> result) 
								{
									
									int rowCount = result.size();
									for (int row = 0; row < rowCount; row ++) 
									{
													
										String cat = result.get(row).getCategory();	
										Double lat = result.get(row).getLatitude();
										Double lng = result.get(row).getLongitude();
										String name = result.get(row).getPlaceName();
																		
										LatLng point = LatLng.newInstance(lat,lng);
																
                                        // Add a marker
										map.addOverlay(new Marker(point));
										map.setCenter(point,15);
										
										// Add an info window to highlight a point of interest
										map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
									}	
									
								}
				
					 });	
		}
		
		*//**
		 * Fired when the user types in the nameField.
		 *//*
	
	}
	final MyHandler handler = new MyHandler();
	
	private void setupMap()
	{
      //LatLng saltLakeCountyCentroid = LatLng.newInstance(40.649387,-111.928711);
		LatLng delhiCityCentroid = LatLng.newInstance(28.5332740008996,77.1380750260093);
		
	    map = new MapWidget(delhiCityCentroid, 15);
	    map.addMapType(MapType.getHybridMap());
	    map.addMapType(MapType.getSatelliteMap());
	    map.addMapType(MapType.getNormalMap());
	    map.setCurrentMapType(MapType.getNormalMap());
	    map.addControl(new MapTypeControl());

	    map.setSize("700px", "450px");
	    map.addControl(new LargeMapControl3D());
	    map.addControl(new ScaleControl());
	    
	     * Disable double-click for zoom so we can 
	     * use double-click handler for other fun things
	     
	   
	    map.setDoubleClickZoom(false);
	    
	    
	     * Single click on map will send the map's 
	     * center point for WITHIN query on the server
	    map.addMapClickHandler(new MapClickHandler(){

			public void onClick(MapClickEvent event) {
				LatLng point = event.getLatLng();
				map.panTo(point);
				
				// now send point to server as "lng,lat" string
				double lat = point.getLatitude();
				double lng = point.getLongitude();
				
				String lngLat = Double.toString(lng) + "," + Double.toString(lat);
				//textToServerLabel.setText(lngLat);
				//sendMapCenter(lngLat);
				
			}
	    	
	    });
	    
		
		 * Zooming in close enough will put the map viewport
		 * within Salt Lake County. Zooming out too far will
		 * makes the map viewport larger than the county.
		 
	    map.addMapZoomEndHandler(new MapZoomEndHandler(){

			public void onZoomEnd(MapZoomEndEvent event) {
				
				
				 * We want a string to make a box, like this:
				 * "lng1 lat1, lng2 lat2, lng3 lat3, lng4 lat4, lng1 lat1"
				 
			    LatLngBounds bounds = map.getBounds();
				LatLng ne = bounds.getNorthEast();
				LatLng sw = bounds.getSouthWest();
				
				double nLat = ne.getLatitude();
				double eLng = ne.getLongitude();
				double sLat = sw.getLatitude();
				double wLng = sw.getLongitude();
//				
				
				 * Start at ne and go clockwise
				 
				String nePair = Double.toString(eLng) + " " + Double.toString(nLat);
				String sePair = Double.toString(eLng) + " " + Double.toString(sLat);
				String swPair = Double.toString(wLng) + " " + Double.toString(sLat);
          	String nwPair = Double.toString(wLng) + " " + Double.toString(nLat);
//				
				
				 * Finish bbox back at ne
				 
				String bbox = nePair + "," + sePair + "," + swPair + "," + nwPair + "," + nePair;
				
				textToServerLabel.setText(bbox);
				sendMapBounds(bbox);
			
			}
				
	    });
	    
	    
	     * Let's repeat the zoom handler funtionality as a drag end handler. If
	     * the user is zoomed in, and is within the county, we want to warn them
	     * if they drag the map beyond the edge of the county too.
	     
	    map.addMapDragEndHandler(new MapDragEndHandler(){
			public void onDragEnd(MapDragEndEvent event) {
				
				
				 * We want a string to make a box, like this:
				 * "lng1 lat1, lng2 lat2, lng3 lat3, lng4 lat4, lng1 lat1"
				 
				LatLngBounds bounds = map.getBounds();
				LatLng ne = bounds.getNorthEast();
				LatLng sw = bounds.getSouthWest();				
				double nLat = ne.getLatitude();
				double eLng = ne.getLongitude();
				double sLat = sw.getLatitude();
				double wLng = sw.getLongitude();
				
				
				 * Start at ne and go clockwise
				 
				String nePair = Double.toString(eLng) + " " + Double.toString(nLat);
				String sePair = Double.toString(eLng) + " " + Double.toString(sLat);
				String swPair = Double.toString(wLng) + " " + Double.toString(sLat);
				String nwPair = Double.toString(wLng) + " " + Double.toString(nLat);
				
				
				 * Finish bbox back at ne
				 
				String bbox = nePair + "," + sePair + "," + swPair + "," + nwPair + "," + nePair;
				
				//textToServerLabel.setText(bbox);
				sendMapBounds(bbox);
			}

			
			
	    	
	    });
	    mapWrapper.add(map);
		mapWrapper.setStylePrimaryName("map");
		mapWrapper.setCellHorizontalAlignment(map, HasHorizontalAlignment.ALIGN_CENTER);
		mapWrapper.setBorderWidth(2);
		
	}
	
	*//**
	 * This Functions is used to set up a layer in a tree format by extracting the 
	 * layer from the data base. 
	 *//*
	private void setupLayerManager()
	{
		

		layerService.getLayers(new AsyncCallback<List<Layer>>()
				{
					public void onFailure(Throwable caught) 
					{		 

					}

					public void onSuccess(List<Layer> result) 
					{		
						for (int row = 0; row < result.size(); row ++) 				
							lm.addLayer(result.get(row));
						vp2.add(lm);

					}

				});
				
//		class Listener implements TreeListener
//		{
//			public void onTreeItemSelected(TreeItem item) 
//			{		
//					String text = item.getText();
//					if (text == "States")
//					{
//						map.clearOverlays();
//						dea.getStates(new AsyncCallback<List<States>>()
//						  		{
//						  				public void onFailure(Throwable caught) 
//									 	{		 
//								     
//									 	}
//						  		
//									 	public void onSuccess(List<States> result) 
//										{
//									 		int rowCount = result.size();
//									 		for (int row = 0; row < rowCount; row ++) 
//									 		{
//									 			LatLng point = LatLng.newInstance(result.get(row).getLatitude(),result.get(row).getLongitude());
//												map.addOverlay(new Marker(point));
//												map.setCenter(point,10);
//									 		}
//										}
//						  		});
//					}
//					
//					
//				
//			}
//
//			@Override
//			public void onTreeItemStateChanged(TreeItem item) {
//				// TODO Auto-generated method stub
//				
//			}
//		}		

	}
	
	private void setupToolsPanel()
	{	    
		//creating vertical panel for Tools
		vp2.setBorderWidth(2);
		vp2.setStyleName("VerticalPanel");
		title3 = new HTML(applicationTitle3);
		title3.setStylePrimaryName("title");
		vp2.add(title3);
		vp2.setCellHorizontalAlignment(title3,HasHorizontalAlignment.ALIGN_CENTER);
		vp2.setSize("250px", "470px");

	}
	

	private void setupDialogBox(){
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		textToServerLabel = new Label();
		serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending coordinates to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event)
		
			{
				
				dialogBox.hide();
				//dialogBox.setVisible(true);
				
			}
	});
	}
		
	*//**
	 * This is the entry point method.
	 */
	private void setupFileUpload(){
		upload.setName("KmlFileUpload"); 
		uiObject.fpAdmin.setWidget(uiObject.vpAdmin1);
		uiObject.fpAdmin.setAction(GWT.getModuleBaseURL()+"FileUp");
		uiObject.fpAdmin.setEncoding(FormPanel.ENCODING_MULTIPART);
		uiObject.fpAdmin.setMethod(FormPanel.METHOD_POST);
		uiObject.hpAdmin.add(upload);
		 
	}
	
	public void onModuleLoad()
	{
		setupFileUpload();
		title2 = new HTML(applicationTitle2);
		Window.setTitle(applicationTitle2);
//		title2.setStylePrimaryName("title");
//		vp.add(title2);
//		vp.setCellHorizontalAlignment(title2, HasHorizontalAlignment.ALIGN_CENTER);
//		//setupDialogBox();
//		dea.getStates(new AsyncCallback<List<States>>()
//		  		{
//		  			public void onFailure(Throwable caught) 
//					 	{		 
//				     
//					 	}
//		  		
//					 	public void onSuccess(List<States> result) 
//						{
//					
//					 		if(result.size()== 0)
//					 		{
//					 			StatesLoader();
//					 			DistrictsLoader();
//					 			LocalBodyLoader();
//					 		}
//					 			
//						}
//		  		});
//					 		
//	    layerLoader();
//		setupLatLongPanel();
//		setupKeywordPanel();
//		setupSpatialPanel();
//		setupAttributePanel();
//		setupQueryPanels();
//		setupToolsPanel();
//		setupLayerManager();
//		setupTablePanel();
//		hp.add(vp1);
//	
//		setupMap();
//		//creating vertical panel for Map
//		hp.add(mapWrapper);
//		setupToolsPanel();
//		hp.add(vp2);
//	    vp.add(hp);
//	    /**vp.add(mapWrapper);
//	    vp.setCellHorizontalAlignment(mapWrapper, HasHorizontalAlignment.ALIGN_CENTER);*/
//	    // style the vp
//	    vp.setWidth("100%");
//	    vp.setStylePrimaryName("vp");
	    // Create the popup dialog box
		RootPanel.get().add(uiObject);
	    
	}
/*
	protected void sendMapBounds(String bbox) 
	{
		gisCloudService.polygonInPolygon(bbox,
				new AsyncCallback<String>() 
				{
					public void onFailure(Throwable caught) 
					{
						// Show the RPC error message to the user
						dialogBox
								.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) 
					{
						dialogBox.setText("Java Topology Suite on AppEngine Says...");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
	}
    
	private void setupTablePanel()
	{
		datagrid.setAllowRowMark(true);
		datagrid.setFirstColumnVisible(true);
		datagrid.setTableModelService(landmarksModelService);
		datagrid.addRowSelectionListener(new RowSelectionListener() {
			public void onRowSelected(AdvancedTable sender, String rowId) {
				labelMessages.setText("Row " + rowId + " selected.");
			}
		});
		
		
		datagrid.setSize("1024px", "200px");
		datagrid.setPageSize(5);

		vp.add(hp);
		vp.add(tablePanel);
		tablePanel.add(datagrid);
		vp.add(hp1);
		hp1.add(tablePanel1);
		final TextBox textBoxFilter = new TextBox();
		tablePanel1.add(textBoxFilter);
		textBoxFilter.setWidth("100%");
		tablePanel1.setCellWidth(textBoxFilter, "100%");

		final Button buttonApplyFilter = new Button();
		tablePanel1.add(buttonApplyFilter);
		buttonApplyFilter.addClickListener(new ClickListener() {
		public void onClick(Widget sender) {
				String filterText = textBoxFilter.getText();
				DataFilter filter = new DataFilter("keyword", filterText); 
				DataFilter[] filters = {filter};
				datagrid.applyFilters(filters);
				labelMessages.setText("Filter '" + filterText +"' applied.");
			}
		});
		buttonApplyFilter.setWidth("100");
		tablePanel1.setCellWidth(buttonApplyFilter, "100");
		tablePanel1.setCellHorizontalAlignment(
		buttonApplyFilter, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonApplyFilter.setText("Apply Filter");

		final Button clearFilterButton = new Button();
		tablePanel1.add(clearFilterButton);
		clearFilterButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.applyFilters(null);
				textBoxFilter.setText("");
				labelMessages.setText("Filter cleaned.");
			}
		});
		clearFilterButton.setWidth("100");
		tablePanel1.setCellWidth(clearFilterButton, "100");
		clearFilterButton.setText("Clear Filter");

		

		final Button buttonMarkAll = new Button();
		tablePanel1.add(buttonMarkAll);
		buttonMarkAll.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.markAllRows();
			}
		});
		buttonMarkAll.setWidth("128px");
	buttonMarkAll.setText("Mark All");
//
		final Button buttonMarkNothing = new Button();
		tablePanel1.add(buttonMarkNothing);
		buttonMarkNothing.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.clearMarkedRows();
			}
		});
		buttonMarkNothing.setSize("136px", "24px");
		buttonMarkNothing.setText("Mark Nothing");

		final Button buttonShowMarked = new Button();
		tablePanel1.add(buttonShowMarked);
		buttonShowMarked.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Set markedRows = datagrid.getMarkedRows();
				Window.alert("Marked rows:" + markedRows.toString());				
		}
		});
		buttonShowMarked.setSize("128px", "24px");
		buttonShowMarked.setText("Show Marked");
	}
	
	protected void sendMapCenter(String lngLat) 
	{
		gisCloudService.pointInPolygon(lngLat,
				new AsyncCallback<String>() 
				{
					public void onFailure(Throwable caught) 
					{
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) 
					{
						dialogBox.setText("Java Topology Suite on AppEngine Says...");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
	
	}*/
	}
