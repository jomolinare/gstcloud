package com.rmsi.lim.gstcloud.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
	//import com.rmsi.lim.gstcloud.client.GSTCloud.MyHandler;
//@SuppressWarnings("deprecation")
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudService;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksServiceAsync;
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
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudConstants;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudUtils;
import com.rmsi.lim.gstcloud.client.view.AdvancedTable;
import com.rmsi.lim.gstcloud.client.view.LayerManager;

public class GSTCloudUI  extends Composite {
	 
	
	private static testagainUiBinder uiBinder = GWT
			.create(testagainUiBinder.class);
 @UiTemplate("GSTCloudUI.ui.xml")
	interface testagainUiBinder extends UiBinder<Panel, GSTCloudUI> {
	}
 
 	
 //Main Fields
 @UiField 
 VerticalPanel pb,vp,vp1,vp2,mapwrapper;
 @UiField
 HorizontalPanel hp;
 @UiField
 TabLayoutPanel queryTabPanel;
 @UiField
 MapWidget map;
 
 //Fields for LatLngPanel
 @UiField
 VerticalPanel vpLatLng;
 @UiField
 HorizontalPanel hpLatLng;
 @UiField
 TextBox tbLongitude,tbLatitude,tbLatLngRadius;
 @UiField
 Label lblError,lblLatitude,lblLongitude,lblLatLngRadius;
 @UiField
 Button btnLatLngSearch,btnLatLngClear;
 
 //Fields for AttributePanel
 @UiField
 VerticalPanel vpAttribute;
 @UiField
 HorizontalPanel hpAttribute;
 @UiField
 Label lblAttribute,lblAttributeRadius;
 @UiField
 TextBox tbAttribute,tbAttributeRadius;
 @UiField
 Button btnAttributeSearch,btnAttributeClear;
 
 //Fields for SpatialPanel
 @UiField
 VerticalPanel vpSpatial;
 @UiField
 HorizontalPanel hpSpatial;
 @UiField
 Label lblState,lblLocalBody,lblSpatialRadius;
 @UiField
 ListBox lbState,lbDistrict,lbLocalBody;
 @UiField
 TextBox tbSpatialRadius;
 @UiField
 Button btnSpatialSearch, btnSpatialClear;
 
 //Fields for GeoCodedPanel
 @UiField
 VerticalPanel vpGeoCoded;
 @UiField
 HorizontalPanel hpGeoCoded;
 @UiField
 Label lblGeoCodedAddress,lblGeoCodedRadius;
 @UiField
 TextBox tbGeoCodedAddress,tbGeoCodedRadius;
 @UiField
 Button btnGeoCodedSearch,btnGeoCodedClear;
 
 //Fields for AdminPanel
 @UiField
 VerticalPanel vpAdmin,vpAdmin1;
 @UiField
 HorizontalPanel hpAdmin;
 @UiField
 Button btnAdminLoad,btnAdminDisplay;
 
 //Fields for ToolPanel
 @UiField
 HorizontalPanel toolsPanel;
 
 //Fields for DialogBox
 @UiField
 VerticalPanel vpDialogBox;
 @UiField
 HTML serverResponseLabel;
 @UiField
 Label textToServerLabel;
 @UiField
 DialogBox dialogBox;
 
 //Fields for TablePanel & DataGirdPanel
 @UiField
 VerticalPanel vpDataGird,vpTable;
 @UiField
 HorizontalPanel hpTable;
 @UiField
 FormPanel fpAdmin;
 @UiField
 Label lblMessages;
 @UiField
 TextBox  tbFilter;
 @UiField
 Button btnApplyFilter, btnClearFilter,btnMarkAll,btnMarkNothing,btnShowMarked;
	

	/**
	  * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private AdvancedTable datagrid = new AdvancedTable();
	final FileUpload upload = new FileUpload();
    Geocoder geocoder = new Geocoder();
	
	Button closeButton = new Button();
	
	private final GisCloudServiceAsync gisCloudService = GWT
	.create(GisCloudService.class);
	private final LandmarksServiceAsync landMarksService = GWT
    .create(LandmarksService.class);
	private final SpatialBodiesServiceAsync spatialBodiesService = GWT
    .create(SpatialBodiesService.class);
	private final LayerServiceAsync layerService = GWT
	.create(LayerService.class);
	private final LandmarksTableModelServiceAsync landmarksModelService=GWT.create(LandmarksTableModelService.class);
	LayerManager lm = new LayerManager();

	private List<LatLng> listofClicks = new ArrayList<LatLng>();
	
	public GSTCloudUI() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		
		  spatialBodiesService.getStates(new AsyncCallback<List<State>>()
			  		{
			  			public void onFailure(Throwable caught) 
						 	{		 
					     
						 	}
			  		
						 	public void onSuccess(List<State> result) 
							{
						 		if(result.size()== 0)
						 		{
						 			StatesLoader();
						 			DistrictsLoader();
						 			LocalBodyLoader();
						 		}
						 			
							}
			  		});
		spatialBodiesService.getStates(new AsyncCallback<List<State>>()
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
			 			lbState.addItem(state);
			 		}
				}
  		});
		
		
		

		lbState.addItem("Select State");
		lbDistrict.addItem("No Available Districts");
		lbLocalBody.addItem("No Available Villages/Towns");
		setupMap();
		//hpAdmin.add(upload);
		layerLoader();
		setupLayerManager();
		setupTablePanel();
		//setupUninorDemo();
	}
	
	
	
	private void setupUninorDemo(){
		
		final MapClickHandler uniclick = 
		new MapClickHandler() {
		      public void onClick(MapClickEvent e) {
		        MapWidget sender = e.getSender();
		        Overlay overlay = e.getOverlay();
		        LatLng point = e.getLatLng();

		        if (overlay != null && overlay instanceof Marker) {
		          sender.removeOverlay(overlay);
		        } else {
		          sender.addOverlay(new Marker(point));
		          listofClicks.add(point);
		        }
		      }
		     };
		    
		
		
		
		final Button showOverLayButton;
		final Button removeOverLayButton;
		final Button startClicking= new Button ("Click required points");
		startClicking.addClickHandler(new ClickHandler(){
			 public void onClick(ClickEvent event) {
				 map.addMapClickHandler(uniclick);
			 }
		});
		toolsPanel.add(startClicking);
	    // Toggle the visibility of the overlays by
	    // adding and removing the overlay.
		showOverLayButton = new Button("Add Territory Overlay");
	    // Toggle the visibility of the overlays
	    // using the show() and hide() methods
		removeOverLayButton = new Button("Hide");
		removeOverLayButton.setEnabled(false);
		showOverLayButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        map.removeMapClickHandler(uniclick);
	        listofClicks.add(listofClicks.get(0));
	        Polygon territoryOverLay = new Polygon(listofClicks.toArray(new LatLng[0]), "green", 1, 1, "green", 0.5);
	    	map.addOverlay(territoryOverLay);
	    	removeOverLayButton.setEnabled(true);
	        //Create and add overlay 
	        //toggleOverlay();
	      }
	    });
		toolsPanel.add(showOverLayButton);

	    removeOverLayButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	    	  map.clearOverlays();
	    	  listofClicks.clear();
	    	  showOverLayButton.setEnabled(true);
	    	  //remove overlay
	       /* if (geoXml == null) {
	          return;
	        }
	        if (geoXml.isHidden()) {
	          geoXml.setVisible(true);
	          showHideButton.setText("Hide");
	        } else {
	          geoXml.setVisible(false);
	          showHideButton.setText("Show");
	        }*/
	      }
	    });

	    toolsPanel.add(removeOverLayButton);
	    //initWidget(panel);
	}
	
	/**
	 * This Function is used to find the Landmarks within a circle using Proximity Search of JavaGeoModel
	 * @param point Centerpoint of the search circle
	 * @param radius Radius of the search circle
	 * @param map 
	 */
	private void drawLandMarksWithinCircle(final LatLng point, final Double radius)
	{
		final Double localRadius=radius;
		final LatLng localPoint =point;
		landMarksService.displayLandmarksWithinDistance
		(localPoint.getLatitude(), 
		 localPoint.getLongitude(),
		 localRadius, 
		 new AsyncCallback<List<LandmarkDTO>>()
		{
			public void onFailure(Throwable caught) 
			{
			}
		    public void onSuccess(List<LandmarkDTO> result ) 
		    {
		    //	tbAttributeRadius.setValue("Running");
		    	int rowCount = result.size();
		    	
		    	map.addOverlay(GSTCloudUtils.drawSearchCircleOnScreen(localPoint,localRadius,60));
		    	map.setCenter(localPoint, 10);
		    	map.addOverlay(new Marker(localPoint));
				for (int row = 0; row < rowCount; row ++)
				{
					final LandmarkDTO lm =result.get(row);
					Double pntlat = lm.getLatitude();
					Double pntlng = lm.getLongitude();
					final LatLng point = LatLng.newInstance(pntlat,pntlng);
//					// Add a marker
					final Marker marker =new Marker(point);
		   	          
		   	         marker.addMarkerClickHandler(new MarkerClickHandler() {
		   	          public void onClick(MarkerClickEvent event) {
		   	          InfoWindow info = map.getInfoWindow();
		   	          info.open(marker,
		   	              new InfoWindowContent("<tr><td>Category</td><td>" +lm.getCategory()+
		   	            		  "</tr><tr><td>Latitude:</td><td>"+lm.getLatitude()+
		   	            		  "</tr><tr><td>Longitude:</td><td>"+lm.getLongitude()+
		   	            		  "</tr><tr><td>Landmark Name</td><td>"+lm.getPlaceName()+
		   	            		  "</td></tr>"));
		   	        }
		   	      });
		   	      map.addOverlay(marker);

		   	          
					
				}
		    }
		});
	}
	
	
	
	/**
	 *This is a function with hard coded values of 9 States.The properties include the state name,Latitude ,longitude
	 *and the zoom level for the respective state.
	 */	
	private void StatesLoader()
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
	 *This is a function with hard coded values of 16 Districts for all the states loaded.The properties include 
	 *the stateId,District name ,Latitude and longitude for the respective district.
	 */
	private void DistrictsLoader()
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
	private void LocalBodyLoader()
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
	 *This is a function with hard coded values of 4 Layers and their Types.The data include the name of the
	 *layer and its type (i.e point,polygon or line). 	 
	 */
	
	public void layerLoader() 
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
	
	@UiHandler("fpAdmin")
	public void onSubmit(SubmitEvent event) 
    {
      if(event.getSource() == btnAdminLoad)
      {
      
  	// This event is fired just before the form is submitted. We can take
      // this opportunity to perform validation.
        Window.alert("file uploaded");
        event.cancel();
      
      }
    }

	@UiHandler("fpAdmin")
	public void onSubmitComplete(SubmitCompleteEvent event) {
		      // When the form submission is successfully completed, this event is
		      // fired. Assuming the service returned a response of type text/html,
		      // we can get the result text here (see the FormPanel documentation for
		      // further explanation).
		     
		    	//Window.alert(event.getResults());
		    	/* land.loadKML(upload1.getFilename(), new AsyncCallback<String>(){
						public void onFailure(Throwable caught) 
						{
							System.out.println("failure");	
						}

						public void onSuccess(String result) 
						{
							System.out.println("success");
						}					
					});
		    	    }*/		
		    }
	
	@UiHandler({"btnAdminLoad","btnAdminDisplay","btnGeoCodedSearch","btnGeoCodedClear","tbGeoCodedRadius","tbGeoCodedAddress","btnLatLngSearch","btnLatLngClear", "tbLatitude","tbLongitude","tbLatLngRadius","tbAttribute","tbAttributeRadius","btnAttributeSearch","btnAttributeClear","btnSpatialSearch","btnSpatialClear","tbSpatialRadius"})
	public void onClick(ClickEvent event) {
		eventMessageClick(event);
	}
	
	private void eventMessageClick(GwtEvent<?> event) {		
		if (event.getSource()==btnAdminLoad)
		{
			fpAdmin.submit();
		}
		else if (event.getSource()==btnAdminDisplay) 
			showAllLandmarks();
		else if (event.getSource()==btnGeoCodedSearch)	
		{
			showByGeoCodedAddress(tbGeoCodedAddress.getText());
		}
		else if (event.getSource()==btnGeoCodedClear) 
		{
			tbGeoCodedRadius.setValue("");
			tbGeoCodedAddress.setValue("");
			tbGeoCodedRadius.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==btnSpatialSearch) 
		{
		    showBySpatialLocation();		 
		}
		else if (event.getSource()==btnSpatialClear)
			{
				map.clearOverlays();
			}
		else if (event.getSource()==btnLatLngSearch)	
		{			
			showByLatLong();
		}
		else if (event.getSource()==btnLatLngClear) 
		{
			tbLatitude.setValue("");
			tbLongitude.setValue("");
			tbLatLngRadius.setValue("");
			map.clearOverlays();
		}
		else if(event.getSource()== btnAttributeClear){
			tbAttributeRadius.setValue("");
			tbAttribute.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==btnAttributeSearch)	
		{
			showByName(tbAttribute.getText().trim().replace("'", "\\'"));
		}
		/*else if (event.getSource()==tbLatitude)
		{
			tbLatitude.setValue("");
		}
		else if (event.getSource()==tbLongitude)
		{
			tbLongitude.setValue("");
		}
		else if (event.getSource()==tbLatLngRadius) 
		{
			tbLatLngRadius.setValue("");
		}
		else if(event.getSource()== tbAttribute){
			tbAttribute.setValue("");
		}
		else if(event.getSource()== tbAttributeRadius){
			tbAttributeRadius.setValue("");
		}
		else if (event.getSource()==tbGeoCodedRadius) 
		{
			tbGeoCodedRadius.setValue("");
		}
		else if (event.getSource()==tbGeoCodedAddress) 
		{
			tbGeoCodedAddress.setValue("");
		}
		else if (event.getSource()==tbSpatialRadius) 
		{
			tbSpatialRadius.setValue("");
		}*/
		
}
	private void showAllLandmarks(){
		landMarksService.getLandMarks(new AsyncCallback<List<LandmarkDTO>>() 
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
									//map.setCenter(point,15);
									
									// Add an info window to highlight a point of interest
									//map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
									
								}	
								
							}
			
				 });
	}
	
	private void showBySpatialLocation(){
		final Double radius = new Double(tbSpatialRadius.getText());
		int selectedIndex=lbLocalBody.getSelectedIndex();
		 if(selectedIndex==-1 || lbLocalBody.getItemText(selectedIndex )== "Select Villages/Town"||lbLocalBody.getItemText(selectedIndex )== "No Available Villages/Town")
		 {
			 selectedIndex = lbDistrict.getSelectedIndex();
			 if(selectedIndex==-1 ||lbDistrict.getItemText( selectedIndex)== "Select District"||lbDistrict.getItemText( selectedIndex)=="No Available Districts")
			 {
				 selectedIndex=lbState.getSelectedIndex();
				 if(lbState.getItemText( selectedIndex)== "Select State")
				 {
//					 dialogBox.setText("At least Enter name of the State");
				 }	
				 else
				 {
					 String sName = lbState.getItemText( lbState.getSelectedIndex());
					 /**
					  * This function call brings the attributes of the state by the name specified.
					  */
					 spatialBodiesService.getStateByName(sName,new AsyncCallback<State>()
						{
							public void onFailure(Throwable caught) 
							{
								
							}

							public void onSuccess(State result ) 
							{									
								LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
								//map.addOverlay(new Marker(point));
								//map.setCenter(point,result.getZoomLevel());
								//GSTCloudUtils.drawSearchCircleOnScreen(point,new Double(tbLatLngRadius.getText()),60,map);
								drawLandMarksWithinCircle(point,radius);
							}
						});
				 }
			 }
			 else
			 {
				 String dName = lbDistrict.getItemText( lbDistrict.getSelectedIndex());
				 /**
				  * This function call brings the attributes of the District by the name specified.
				  */
				 spatialBodiesService.getDistrictByName(dName,new AsyncCallback<District>()			
						 {
							public void onFailure(Throwable caught) 
							{
							
							}

							public void onSuccess(District result ) 
							{
								LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
								//map.addOverlay(new Marker(point));
								//map.setCenter(point,15);
								//GSTCloudUtils.drawSearchCircleOnScreen(point,new Double(tbLatLngRadius.getText()),60,map);
								drawLandMarksWithinCircle(point,radius);
							}
						 });
			 }
		 }
		 else
		 {
			 String lbName = lbLocalBody.getItemText( lbLocalBody.getSelectedIndex());
			 /**
			  * This function call brings the attributes of the Local Body by the name specified.
			  */
			 spatialBodiesService.getLocalBodyByName(lbName,new AsyncCallback<LocalBody>()			
					 {
						public void onFailure(Throwable caught) 
						{
						
						}

						public void onSuccess(LocalBody result ) 
						{
							LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
							//map.addOverlay(new Marker(point));
							//map.setCenter(point,15);
							//GSTCloudUtils.drawSearchCircleOnScreen(point,new Double(tbLatLngRadius.getText()),60,map);
							drawLandMarksWithinCircle(point,radius);
						}
					 });
		 }
	}
	
	private void showByLatLong(){
		final Double radius = new Double(tbLatLngRadius.getText());
		lblError.setText("");
		if (!FieldVerifier.isaNumber(tbLatitude.getText(),tbLongitude.getText()))
		{
			lblError.setText("Enter only digits");
			tbLatitude.setValue("Enter again");
			tbLongitude.setValue("Enter again");
			tbLatLngRadius.setValue("Enter again");
			return;
		}
		if (!FieldVerifier.isValidNumber(tbLatitude.getText(),tbLongitude.getText())) 
		{
			lblError.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
			tbLatitude.setValue("Enter again");
			tbLongitude.setValue("Enter again");
			tbLatLngRadius.setValue("Enter again");
			return;
		}
		final LatLng centerPoint = LatLng.newInstance(new Double(tbLatitude.getText()),new Double(tbLongitude.getText()));
		drawLandMarksWithinCircle(centerPoint,radius);
	}
	private void showByName(String landMarkName){
		final Double radius = new Double(tbAttributeRadius.getText());
		landMarksService.searchLandmarkByName(landMarkName,new AsyncCallback<List<LandmarkDTO>>()
				{
					public void onFailure(Throwable caught) 
					{
					}
				    public void onSuccess(List<LandmarkDTO> result ) 
				    {
				    
						int rowCount = result.size();
						for (int row = 0; row < rowCount; row ++)
						{
							Double lat = result.get(row).getLatitude();
							Double lng = result.get(row).getLongitude();
							LatLng centerPoint = LatLng.newInstance(lat,lng);
//							// Add a marker
				   	          //map.addOverlay(new Marker(centerPoint));
				   	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,radius,60,map);
				   	          //map.setCenter(centerPoint, 10);
							// Add an info window to highlight a point of interest
					    	  
					    	  
					    	  map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
					    	  drawLandMarksWithinCircle(centerPoint,radius);
						}
				    }
				});
	}

	private void showByGeoCodedAddress(final String address) {
		final Double radius = new Double(tbGeoCodedRadius.getText());
		//final InfoWindow info = map.getInfoWindow();
	    try{
		geocoder.getLatLng(address, new LatLngCallback() {
	      public void onFailure() {
	        //Window.alert(address + " not found");
	    	  System.out.println(address+ "not found");
	      }
	      public void onSuccess(LatLng centerPoint) {
	    	  final LatLng localPoint = centerPoint;
	    	  //map.setCenter(centerPoint, 13);
	          //Marker marker = new Marker(centerPoint);
	          //map.addOverlay(marker);
	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,new Double(tbGeoCodedRadius.getText()),60,map);
	          drawLandMarksWithinCircle(localPoint,radius);
	          //info.open(marker, new InfoWindowContent(address));
//	          displayLatLng(point);
	        }
	      });
	}	
	    catch(Exception ex){
	    	 System.out.println(ex.getMessage());
	    		    	 
	    }
	}
	
	
	@UiHandler({"lbDistrict","lbState"})
	public void onChange(ChangeEvent event)
	{
		try{
			
		
		if (event.getSource() == lbState)
		{
			String stateText = new String();
			int selectedIndex=lbState.getSelectedIndex();
			stateText=lbState.getItemText(selectedIndex);
			
			int count = lbDistrict.getItemCount();	
	 		for (int ctr1 = count-1; ctr1 >=0;ctr1--)
	 		{
	 			lbDistrict.removeItem(ctr1);
	 		}

	 			lbDistrict.addItem("No Available Districts");
	        int count2 = lbLocalBody.getItemCount();	
	 		for (int ctr2 = count2-1; ctr2 >=0;ctr2--)
	 			lbLocalBody.removeItem(ctr2);
	 			lbLocalBody.addItem("No Available Villages/Towns");
			if(stateText!="Select State")
			spatialBodiesService.getDistrictsByStateName(stateText, new AsyncCallback<List<District>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<District> result) 
				{
			 		int count3 = lbDistrict.getItemCount();	
			 		for (int ctr3 = count3-1; ctr3 >=0 ;ctr3--)
			 			lbDistrict.removeItem(ctr3);
			 			lbDistrict.addItem("Select District");
			 		for (int row = 0; row < result.size(); row ++) 
			 			lbDistrict.addItem(result.get(row).getDistrictName());
			 		
				}
				
				
			});
			
		}
		
		else if(event.getSource() == lbDistrict)
		{
			String districtText = new String();
			int selectedIndex=lbDistrict.getSelectedIndex();
			districtText=lbDistrict.getItemText(selectedIndex);
			int count = lbLocalBody.getItemCount();	
	 		for (int i = count-1; i >=0;i--)
	 			lbLocalBody.removeItem(i);
	 			lbLocalBody.addItem("No Available Villages/Towns");
			if (districtText!="Select District")
			spatialBodiesService.getLocalBodiesByDistrictName(districtText, new AsyncCallback<List<LocalBody>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<LocalBody> result) 
				{
			 		int count = lbLocalBody.getItemCount();	
			 		for (int i = count-1; i >=0;i--)
			 			lbLocalBody.removeItem(i);
			 			lbLocalBody.addItem("Select Villages/Town");
			 		for (int row = 0; row < result.size(); row ++) 
			 			lbLocalBody.addItem(result.get(row).getLocalBodyName());
			 		
				}
				
				
			});
			
		}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@UiHandler({"tbLatLngRadius","tbLatitude","tbLongitude"})
	public void onKeyUp(KeyUpEvent event) 
	{
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
		{
			Double radius = new Double(tbLatLngRadius.getText());
			
			lblError.setText("");
			String latChck = new String();
			latChck = tbLatitude.getText();
			String longChck = new String();
			longChck = tbLongitude.getText();
			if (!FieldVerifier.isaNumber(latChck,longChck))
			  {
				lblError.setText("Enter only digits");
				return;
			  }
			//Integer latCheck = new Integer (tbLatitude.getText());
			//Integer longCheck = new Integer (tbLongitude.getText());
			if (!FieldVerifier.isValidNumber(tbLatitude.getText(),tbLongitude.getText())) 
			  {
				lblError.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
				return;
			  }
			
			Double lat = new Double(tbLatitude.getText());	
			Double lng = new Double(tbLongitude.getText());
//			LatLng point = LatLng.newInstance(lat,lng);
//			GSTCloudUtils.drawSearchCircleOnScreen(point,radius,60,map);
//			map.setCenter(point); 
		}
	}

	public void setupMap()
	{
      //LatLng saltLakeCountyCentroid = LatLng.newInstance(40.649387,-111.928711);
		LatLng delhiCityCentroid = LatLng.newInstance(28.5332740008996,77.1380750260093);
		
	    map = new MapWidget(delhiCityCentroid, 15);
	    map.addMapType(MapType.getHybridMap());
	    map.addMapType(MapType.getSatelliteMap());
	    map.addMapType(MapType.getNormalMap());
	    map.addMapType(MapType.getPhysicalMap());
	    map.setCurrentMapType(MapType.getNormalMap());
	    
	    map.addControl(new MapTypeControl());
	    
	    map.setGoogleBarEnabled(false);
	    map.setSize("990px", "400px");
	//    map.setGoogleBarEnabled(true);
	    map.addControl(new LargeMapControl3D());
	    
	    map.addControl(new ScaleControl());
	     /* Disable double-click for zoom so we can 
	     * use double-click handler for other fun things
	     */
	   
	    map.setDoubleClickZoom(true);
	    
	    /*
	     * Single click on map will send the map's 
	     * center point for WITHIN query on the server*/
	   /* map.addMapClickHandler(new MapClickHandler(){

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
	    	
	    });*/
	    
		/*
		 * Zooming in close enough will put the map viewport
		 * within Salt Lake County. Zooming out too far will
		 * makes the map viewport larger than the county.
		 */
	   /* map.addMapZoomEndHandler(new MapZoomEndHandler(){

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
	    */mapwrapper.add(map);
//		mapwrapper.setStylePrimaryName("map");
//		mapwrapper.setCellHorizontalAlignment(map, HasHorizontalAlignment.ALIGN_CENTER);		mapwrapper.setBorderWidth(2);
		
	}

	private void setupDialogBox(){

		// We can set the id of a widget by accessing its Element
		vpDialogBox.add(new HTML("<b>Sending coordinates to the server:</b>"));
		vpDialogBox.add(new HTML("<br><b>Server replies:</b>"));
		vpDialogBox.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogBox.setWidget(vpDialogBox);

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
	
	public void setupLayerManager()
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
//			}
//
//			@Override
//			public void onTreeItemStateChanged(TreeItem item) {
//				// TODO Auto-generated method stub
//				
//			}
//		}		

	}

	public void setupTablePanel()
	{
		datagrid.setAllowRowMark(true);
		datagrid.setFirstColumnVisible(false);
		datagrid.setTableModelService(landmarksModelService);
		datagrid.addRowSelectionListener(new RowSelectionListener() {
			public void onRowSelected(AdvancedTable sender, String rowId) {
				lblMessages.setText("Row " + rowId + " selected.");
			}
		});
		datagrid.setSize("1260px", "100px");
		datagrid.setPageSize(3);
		vpDataGird.add(datagrid);

		tbFilter.setWidth("100%");
		btnApplyFilter.addClickListener(new ClickListener() {
		public void onClick(Widget sender) {
				String filterText = tbFilter.getText();
				DataFilter filter = new DataFilter("keyword", filterText); 
				DataFilter[] filters = {filter};
				datagrid.applyFilters(filters);
				lblMessages.setText("Filter '" + filterText +"' applied.");
			}
		});
		btnApplyFilter.setWidth("128px");
		hpTable.setCellWidth(btnApplyFilter, "100");
		hpTable.setCellHorizontalAlignment(
		btnApplyFilter, HasHorizontalAlignment.ALIGN_RIGHT);

		btnClearFilter.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.applyFilters(null);
				tbFilter.setText("");
				lblMessages.setText("Filter cleaned.");
			}
		});
		btnClearFilter.setWidth("128px");
		hpTable.setCellWidth(btnClearFilter, "100");

		btnMarkAll.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.markAllRows();
			}
		});
		btnMarkAll.setWidth("128px");

		btnMarkNothing.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.clearMarkedRows();
			}
		});
		btnMarkNothing.setWidth("128px");

		btnShowMarked.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Set markedRows = datagrid.getMarkedRows();
				Window.alert("Marked rows:" + markedRows.toString());				
		}
		});
		btnShowMarked.setWidth("128px");
}

}
