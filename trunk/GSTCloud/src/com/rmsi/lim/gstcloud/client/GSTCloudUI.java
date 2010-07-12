package com.rmsi.lim.gstcloud.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.rmsi.lim.gstcloud.shared.Landmarks;
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
import com.rmsi.lim.gstcloud.shared.DataFilter;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.FieldVerifier;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerManager;
import com.rmsi.lim.gstcloud.shared.LayerTree;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import com.rmsi.lim.gstcloud.shared.States;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import java.util.HashMap;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.rmsi.lim.gstcloud.shared.LayerItem;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
import java.util.List;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.FieldVerifier;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerManager;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import com.rmsi.lim.gstcloud.shared.States;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
//import com.rmsi.lim.gstcloud.client.GSTCloud.MyHandler;
//@SuppressWarnings("deprecation")

public class GSTCloudUI  extends Composite {

	private static testagainUiBinder uiBinder = GWT
			.create(testagainUiBinder.class);
 @UiTemplate("GSTCloudUI.ui.xml")
	interface testagainUiBinder extends UiBinder<Panel, GSTCloudUI> {
	}
	
	@UiField 
	VerticalPanel vp,vp1,vp2,mapwrapper,vLatLongPanel,vKeyWordPanel,vSpatialPanel;
	@UiField 
	HorizontalPanel hp,hLatLongPanel,hKeyWrdPanel;
	@UiField
	TextBox longitudeBox,latitudeBox,SpeRadBox;
	@UiField
	Label errorLabel,latLabel,longLabel,speRadLabel,addLabel,kspeRadLabel,stateLabel,localBodyLabel,sspeRadLabel;

	@UiField
	Button latLongSearch,latLongClear,addressSearch,addressClear;
	@UiField
	TabLayoutPanel queryTabPanel;
	@UiField
	TextBox addressBox,kSpeRadBox;
	@UiField
	ListBox stateBox,districtBox;
	@UiField
	ListBox localBodyBox;
	@UiField
	TextBox sSpeRadBox;
	@UiField
	HorizontalPanel hSpatPanel;
	@UiField
	Button spatialSearch;
	@UiField
	Button spatialClear;
	@UiField
	VerticalPanel vAttributePanel;
	@UiField
	Label queryIDLabel;
	@UiField
	TextBox queryIDBox;
	@UiField
	Label aspeRadLabel;
	@UiField
	TextBox aSpeRadBox;
	@UiField
	HorizontalPanel hAttributePanel;
	@UiField
	Button attributeSearch;
	@UiField
	Button attributeClear;
	@UiField
	HorizontalPanel hp5;
	@UiField
	VerticalPanel admin;
	@UiField
	Button loadButton;
	@UiField
	Button displayButton;
//	@UiField
//	Button closeButton;
	@UiField
	MapWidget map;
	@UiField
	VerticalPanel dialogVPanel;
	@UiField
	HTML serverResponseLabel;
	@UiField
	Label labelMessages;
	@UiField
	Label textToServerLabel;
	@UiField
	DialogBox dialogBox;
//	@UiField
//	HorizontalPanel hp1;
	@UiField 
	HorizontalPanel tablePanel1;
	@UiField
	TextBox  textBoxFilter;
	@UiField 
	Button buttonApplyFilter;
	@UiField 
	Button  clearFilterButton;
	@UiField 
	Button  buttonMarkAll;
	@UiField 
	Button buttonMarkNothing;
	@UiField 
	Button  buttonShowMarked;
	@UiField
	VerticalPanel vpdg;
	@UiField
	FormPanel adminform;
	
	
	/**
	  * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private AdvancedTable datagrid = new AdvancedTable();
	@UiField VerticalPanel tablePanel;
	Button closeButton = new Button();
	
	private final GisCloudServiceAsync gisCloudService = GWT
	.create(GisCloudService.class);
	private final LandmarksServiceAsync fea = GWT
    .create(LandmarksService.class);
	private final LocalBodiesServiceAsync dea = GWT
    .create(LocalBodiesService.class);
	private final LayerServiceAsync layerService = GWT
	.create(LayerService.class);
	private final LandmarksTableModelServiceAsync landmarksModelService=GWT.create(LandmarksTableModelService.class);
	LayerManager lm = new LayerManager();

	/**
	 * Draws a circle at a specified radius with a green outline.this functions takes the following as its parameters.
	 * @param center
	 * @param radius
	 * @param nbOfPoints
	 * Earth's radius is taken in meters.The Latitude and Longitude are constituted in a single LatLng class.
	 * The number of points calculated is always 60 
	 */
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
	}
	
	//FileUpload upload = new FileUpload();
	
	public GSTCloudUI() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		
		  dea.getStates(new AsyncCallback<List<States>>()
			  		{
			  			public void onFailure(Throwable caught) 
						 	{		 
					     
						 	}
			  		
						 	public void onSuccess(List<States> result) 
							{
						 		if(result.size()== 0)
						 		{
						 			StatesLoader();
						 			DistrictsLoader();
						 			LocalBodyLoader();
						 		}
						 			
							}
			  		});
		dea.getStates(new AsyncCallback<List<States>>()
  		{
  			public void onFailure(Throwable caught) 
			 	{		 
			 	}
			 	public void onSuccess(List<States> result) 
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

	}
	
	/**
	 *This is a function with hard coded values of 9 States.The properties include the state name,Latitude ,longitude
	 *and the zoom level for the respective state.
	 */	
	private void StatesLoader()
	{
		 final States s1 = new States("Delhi",28.38, 77.12 ,10);
		 final States s2 = new States("Uttar Pradesh",27.40,80.00,9);
		 final States s3 = new States("Maharashtra",20.00,76.00,11);
		 final States s4 = new States("Kerala",10.00,76.25,12);
		 final States s5 = new States("Punjab",30.40,75.50,10);
		 final States s6 = new States("Haryana",30.30,74.60,8);
		 final States s7 = new States("Goa",28.00,72.00,9);
		 final States s8 = new States("Jammu and Kashmir",32.44,74.54,11);
		 final States s9 = new States("Gujarat",23.00,72.00,9);
	
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
	/**
	 *This is a function with hard coded values of 16 Districts for all the states loaded.The properties include 
	 *the stateId,District name ,Latitude and longitude for the respective district.
	 */
	private void DistrictsLoader()
	{
		 final Districts d1 = new Districts(new Long(1840),"East Delhi",28.53,77.13);
		 final Districts d2 = new Districts(new Long(1840),"West Delhi",28.595,77.102);
		 final Districts d3 = new Districts(new Long(1840),"South Delhi",28.500,77.100);
		 final Districts d4 = new Districts(new Long(1838),"Varanasi",25.20,83.00);
		 final Districts d5 = new Districts(new Long(1842),"Bombay",18.55,72.54);
		 final Districts d6 = new Districts(new Long(1837),"Ernakulam (Cochin)",10.00,76.15);
		 final Districts d7 = new Districts(new Long(1837),"Kannur",11.52,75.25);
		 final Districts d8 = new Districts(new Long(1839),"Amritsar",31.37,74.55);
		 final Districts d9 = new Districts(new Long(1839),"Ludhiana",30.55,75.54);
		 final Districts d10 = new Districts(new Long(1841),"Kaithal",29.48,78.26);
		 final Districts d11 = new Districts(new Long(1845),"Vasco",15.25,73.43);
		 final Districts d12 = new Districts(new Long(1844),"Leh Ladakh",34.10,77.40);
		 final Districts d13 = new Districts(new Long(1844),"Srinagar",30.40,77.00);
		 final Districts d14 = new Districts(new Long(1844),"Jammu",32.43,74.54);
		 final Districts d15 = new Districts(new Long(1843),"Ahemdabad",23.03,72.40);
		 final Districts d16 = new Districts(new Long(1843),"Vadodra",22.00,73.16);
	
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
	/**
	 *This is a function with hard coded values of 6 Local Bodies for all the Districts loaded.The properties include 
	 *the districtId,LocalBody name ,Latitude and longitude for the respective LocalBody.
	 */
	private void LocalBodyLoader()
	{
		final LocalBodies l1 = new LocalBodies(new Long(1889),"Town","Chilla Saroda Bangar",28.29,77.00);
		final LocalBodies l2 = new LocalBodies(new Long(1889),"village","Kondli",28.11,77.29);
		final LocalBodies l3 = new LocalBodies(new Long(1887),"Town","Dwarka Sub City",28.19,77.00);
		final LocalBodies l4 = new LocalBodies(new Long(1887),"village","Najafgarh",27.11,77.909);
		final LocalBodies l5 = new LocalBodies(new Long(1884),"village","Bersarai",28.97,77.98);
		final LocalBodies l6 = new LocalBodies(new Long(1884),"Town","Hauz Khas",28.90,76.11);
		
		
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
	
	@UiHandler("adminform")
	public void onSubmit(SubmitEvent event) 
    {
      if(event.getSource() == loadButton)
      {
      
  	// This event is fired just before the form is submitted. We can take
      // this opportunity to perform validation.
        Window.alert("file uploaded");
        event.cancel();
      
      }
    }

	@UiHandler("adminform")
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
	
	@UiHandler({"loadButton","displayButton","attributeClear","aSpeRadBox","queryIDBox","latLongSearch","latLongClear", "latitudeBox","longitudeBox","SpeRadBox","addressBox","kSpeRadBox","addressSearch","addressClear","spatialSearch","spatialClear","sSpeRadBox"})
	public void onClick(ClickEvent event) {
		eventMessageClick(event);
	}
	
	private void eventMessageClick(GwtEvent<?> event) {		
//		if (event.getSource()==attributeSearch)	
//		{
//			String boxText = new String();
//			boxText = queryIDBox.getText().trim().replace("'", "\\'");
//			showAddress(boxText);
//			}
		if (event.getSource()==loadButton)
		{
			adminform.submit();
			/*final AsyncCallback geoCallBack= new AsyncCallback<String>() 
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
*/		}
		
		


		else if (event.getSource()==displayButton) 
				 fea.displayStation(new AsyncCallback<List<Landmarks>>() 
				 {
					 public void onFailure(Throwable caught) 
					 {
				
				     }

					 public void onSuccess(List<Landmarks> result) 
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

	else if (event.getSource()==attributeClear) 
		{
			aSpeRadBox.setValue("");
			queryIDBox.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==aSpeRadBox) 
		{
			aSpeRadBox.setValue("");
		}
		else if (event.getSource()==queryIDBox) 
		{
			queryIDBox.setValue("");
		}
		else if (event.getSource()==sSpeRadBox) 
		{
			sSpeRadBox.setValue("");
		}
		else if (event.getSource()==latLongClear) 
		{
			latitudeBox.setValue("");
			longitudeBox.setValue("");
			SpeRadBox.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==spatialSearch) 
			 
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
//							 dialogBox.setText("Atleast Enter name of the State");
						 }	
						 else
						 {
							 String sName = stateBox.getItemText( stateBox.getSelectedIndex());
							 /**
							  * This function call brings the attributes of the state by the name specified.
							  */
							 dea.getStateByName(sName,new AsyncCallback<States>()
								{
									public void onFailure(Throwable caught) 
									{
										
									}

									public void onSuccess(States result ) 
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
						 /**
						  * This function call brings the attributes of the District by the name specified.
						  */
						 dea.getDistrictByName(dName,new AsyncCallback<Districts>()			
								 {
									public void onFailure(Throwable caught) 
									{
									
									}

									public void onSuccess(Districts result ) 
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
					 /**
					  * This function call brings the attributes of the Local Body by the name specified.
					  */
					 dea.getLocalBodyByName(lbName,new AsyncCallback<LocalBodies>()			
							 {
								public void onFailure(Throwable caught) 
								{
								
								}

								public void onSuccess(LocalBodies result ) 
								{
									LatLng point = LatLng.newInstance(result.getLatitude(),result.getLongitude());
									map.addOverlay(new Marker(point));
									map.setCenter(point,15);
									drawCircleFromRadius(point,new Double(SpeRadBox.getText()),60);
								}
							 });
				 }
		}
		/**
		 * Deletes all the Overlays displayed on the map
		 */
		else if (event.getSource()==spatialClear)
			{
				map.clearOverlays();
			}
		
//		catch (Exception ex) 
//		 {
//			 System.out.println(ex.getMessage());
//		 }

			
	
		
		else if (event.getSource()==latLongSearch)	
		{	
			Double radius = new Double(SpeRadBox.getText());
			errorLabel.setText("");
			String latChck = new String();
			latChck = latitudeBox.getText();
			String longChck = new String();
			longChck = longitudeBox.getText();
			String spradChck = new String();
			spradChck = SpeRadBox.getText();
			if (!FieldVerifier.isaNumber(latChck,longChck))
			{
				errorLabel.setText("Enter only digits");
				latitudeBox.setValue("Enter again");
				longitudeBox.setValue("Enter again");
				SpeRadBox.setValue("Enter again");
				return;
			}
			//Double latCheck = new Integer (latitudeBox.getText());
			//Double longCheck = new Integer (longitudeBox.getText());
			if (!FieldVerifier.isValidNumber(latitudeBox.getText(),longitudeBox.getText())) 
			{
				errorLabel.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
				latitudeBox.setValue("Enter again");
				longitudeBox.setValue("Enter again");
				SpeRadBox.setValue("Enter again");
				return;
			}
			Double lat = new Double(latitudeBox.getText());	
			Double lng = new Double(longitudeBox.getText());
			LatLng point = LatLng.newInstance(lat,lng);
			drawCircleFromRadius(point,radius,60);
			map.setCenter(point,10);
		}
		else if (event.getSource()==latitudeBox)
		{
			latitudeBox.setValue("");
		}
		else if (event.getSource()==longitudeBox)
		{
			longitudeBox.setValue("");
		}
		else if (event.getSource()==SpeRadBox) 
		{
			SpeRadBox.setValue("");
		}
		else if(event.getSource()== addressBox){
			addressBox.setValue("");
		}
		else if(event.getSource()== kSpeRadBox){
			kSpeRadBox.setValue("");
		}
		else if(event.getSource()== addressClear){
			kSpeRadBox.setValue("");
			addressBox.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==addressSearch)	
		{
////			check for creation of object fea,
		
			String boxText = new String();
			boxText = addressBox.getText().trim().replace("'", "\\'");
			fea.searchByAddress(boxText,new AsyncCallback<List<Landmarks>>()
			{
				public void onFailure(Throwable caught) 
				{
				}
			    public void onSuccess(List<Landmarks> result ) 
			    {
			    //	kSpeRadBox.setValue("Running");
			    	Double radius = new Double(kSpeRadBox.getText());
					int rowCount = result.size();
					for (int row = 0; row < rowCount; row ++)
					{
						Double lat = result.get(row).getLatitude();
						Double lng = result.get(row).getLongitude();
						LatLng point = LatLng.newInstance(lat,lng);
//						// Add a marker
			   	          map.addOverlay(new Marker(point));
			   	          drawCircleFromRadius(point,radius,60);
			   	          map.setCenter(point, 10);
						// Add an info window to highlight a point of interest
				    	  map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
					}
			    }
			});
		}
}

//	private void showAddress(final String address) {
//	    //InfoWindow info = map.getInfoWindow();
//	    geocoder.getLatLng(address, new LatLngCallback() {
//	      public void onFailure() {
//	        //Window.alert(address + " not found");
//	    	  System.out.println(address+ "not found");
//	      }
//	      public void onSuccess(LatLng point) {
//	          map.setCenter(point, 13);
//	          Marker marker = new Marker(point);
//	          map.addOverlay(marker);
//	          //info.open(marker, new InfoWindowContent(address));
//	          //displayLatLng(point);
//	        }
//	      });
//	}	
	
	@UiHandler({"districtBox","stateBox"})
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
			dea.getDistrictsByStateName(stateText, new AsyncCallback<List<Districts>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<Districts> result) 
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
			dea.getLocalBodiesByDistrictName(districtText, new AsyncCallback<List<LocalBodies>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<LocalBodies> result) 
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

	@UiHandler("latLongSearch")
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
			//Integer latCheck = new Integer (latitudeBox.getText());
			//Integer longCheck = new Integer (longitudeBox.getText());
			if (!FieldVerifier.isValidNumber(latitudeBox.getText(),longitudeBox.getText())) 
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

	public void setupMap()
	{
      //LatLng saltLakeCountyCentroid = LatLng.newInstance(40.649387,-111.928711);
		LatLng delhiCityCentroid = LatLng.newInstance(28.5332740008996,77.1380750260093);
		
	    map = new MapWidget(delhiCityCentroid, 15);
	    map.addMapType(MapType.getHybridMap());
	    map.addMapType(MapType.getSatelliteMap());
	    map.addMapType(MapType.getNormalMap());
	    map.setCurrentMapType(MapType.getNormalMap());
	    map.addControl(new MapTypeControl());
	   
	    
	    map.setSize("693px", "377px");
	//    map.setGoogleBarEnabled(true);
	    map.addControl(new LargeMapControl3D());
	    map.addControl(new ScaleControl());
	     /* Disable double-click for zoom so we can 
	     * use double-click handler for other fun things
	     */
	   
	    map.setDoubleClickZoom(false);
	    
	    /*
	     * Single click on map will send the map's 
	     * center point for WITHIN query on the server*/
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
		dialogVPanel.add(new HTML("<b>Sending coordinates to the server:</b>"));
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
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
		datagrid.setFirstColumnVisible(true);
		datagrid.setTableModelService(landmarksModelService);
		datagrid.addRowSelectionListener(new RowSelectionListener() {
			public void onRowSelected(AdvancedTable sender, String rowId) {
				labelMessages.setText("Row " + rowId + " selected.");
			}
		});
		datagrid.setSize("1152px", "100px");
		datagrid.setPageSize(3);
		vpdg.add(datagrid);

		textBoxFilter.setWidth("100%");
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

		clearFilterButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.applyFilters(null);
				textBoxFilter.setText("");
				labelMessages.setText("Filter cleaned.");
			}
		});
		clearFilterButton.setWidth("100");
		tablePanel1.setCellWidth(clearFilterButton, "100");

		buttonMarkAll.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.markAllRows();
			}
		});
		buttonMarkAll.setWidth("128px");

		buttonMarkNothing.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				datagrid.clearMarkedRows();
			}
		});
		buttonMarkNothing.setSize("136px", "24px");

		buttonShowMarked.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Set markedRows = datagrid.getMarkedRows();
				Window.alert("Marked rows:" + markedRows.toString());				
		}
		});
		buttonShowMarked.setSize("128px", "24px");
}

}
