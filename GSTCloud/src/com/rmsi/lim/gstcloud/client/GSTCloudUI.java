package com.rmsi.lim.gstcloud.client;


import java.util.List;
import java.util.Set;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.maps.client.geom.LatLng;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rmsi.lim.gstcloud.shared.DataFilter;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.FieldVerifier;
import com.rmsi.lim.gstcloud.shared.Layer;
import com.rmsi.lim.gstcloud.shared.LayerManager;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import com.rmsi.lim.gstcloud.shared.States;
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

public class GSTCloudUI  extends Composite {
	 
	
	private static testagainUiBinder uiBinder = GWT
			.create(testagainUiBinder.class);
 @UiTemplate("GSTCloudUI.ui.xml")
	interface testagainUiBinder extends UiBinder<Panel, GSTCloudUI> {
	}
 
 	
 //Main Fields
 @UiField 
 VerticalPanel vp,vp1,vp2,mapwrapper;
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
 VerticalPanel vpAdmin;
 @UiField
 HorizontalPanel hpAdmin;
 @UiField
 Button btnAdminLoad,btnAdminDisplay;
 
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
			 			lbState.addItem(state);
			 		}
				}
  		});
		
		
		

		lbState.addItem("Select State");
		lbDistrict.addItem("No Available Districts");
		lbLocalBody.addItem("No Available Villages/Towns");
		setupMap();
//		hpAdmin.add(upload);
		layerLoader();
		setupLayerManager();
		setupTablePanel();
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
		 final Districts d1 = new Districts("Delhi","East Delhi",28.53,77.13);
		 final Districts d2 = new Districts("Delhi","West Delhi",28.595,77.102);
		 final Districts d3 = new Districts("Delhi","South Delhi",28.500,77.100);
		 final Districts d4 = new Districts("Uttar Pradesh","Varanasi",25.20,83.00);
		 final Districts d5 = new Districts("Maharashtra","Bombay",18.55,72.54);
		 final Districts d6 = new Districts("Kerala","Ernakulam (Cochin)",10.00,76.15);
		 final Districts d7 = new Districts("Kerala","Kannur",11.52,75.25);
		 final Districts d8 = new Districts("Punjab","Amritsar",31.37,74.55);
		 final Districts d9 = new Districts("Punjab","Ludhiana",30.55,75.54);
		 final Districts d10 = new Districts("Punjab","Kaithal",29.48,78.26);
		 final Districts d11 = new Districts("Goa","Vasco",15.25,73.43);
		 final Districts d12 = new Districts("Jammu and Kashmir","Leh Ladakh",34.10,77.40);
		 final Districts d13 = new Districts("Jammu and Kashmir","Srinagar",30.40,77.00);
		 final Districts d14 = new Districts("Jammu and Kashmir","Jammu",32.43,74.54);
		 final Districts d15 = new Districts("Gujarat","Ahemdabad",23.03,72.40);
		 final Districts d16 = new Districts("Gujarat","Vadodra",22.00,73.16);
	
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
		final LocalBodies l1 = new LocalBodies("South Delhi","Town","Chilla Saroda Bangar",28.29,77.00);
		final LocalBodies l2 = new LocalBodies("East Delhi","village","Kondli",28.11,77.29);
		final LocalBodies l3 = new LocalBodies("South Delhi","Town","Dwarka Sub City",28.19,77.00);
		final LocalBodies l4 = new LocalBodies("South Delhi","village","Najafgarh",27.11,77.909);
		final LocalBodies l5 = new LocalBodies("South Delhi","village","Bersarai",28.97,77.98);
		final LocalBodies l6 = new LocalBodies("South Delhi","Town","Hauz Khas",28.90,76.11);
		
		
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
		if (event.getSource()==btnGeoCodedSearch)	
		{
			showAddress(tbGeoCodedAddress.getText());
		}
		else if (event.getSource()==btnAdminLoad)
		{
			fpAdmin.submit();
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
		
		


		else if (event.getSource()==btnAdminDisplay) 
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

	else if (event.getSource()==btnGeoCodedClear) 
		{
			tbGeoCodedRadius.setValue("");
			tbGeoCodedAddress.setValue("");
			map.clearOverlays();
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
		}
		else if (event.getSource()==btnLatLngClear) 
		{
			tbLatitude.setValue("");
			tbLongitude.setValue("");
			tbLatLngRadius.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==btnSpatialSearch) 
			 
			 {
				 int selectedIndex=lbLocalBody.getSelectedIndex();
				 if(selectedIndex==-1 || lbLocalBody.getItemText(selectedIndex )== "Select Villages/Town"||lbLocalBody.getItemText(selectedIndex )== "No Available Villages/Town")
				 {
					 selectedIndex = lbDistrict.getSelectedIndex();
					 if(selectedIndex==-1 ||lbDistrict.getItemText( selectedIndex)== "Select District"||lbDistrict.getItemText( selectedIndex)=="No Available Districts")
					 {
						 selectedIndex=lbState.getSelectedIndex();
						 if(lbState.getItemText( selectedIndex)== "Select State")
						 {
//							 dialogBox.setText("Atleast Enter name of the State");
						 }	
						 else
						 {
							 String sName = lbState.getItemText( lbState.getSelectedIndex());
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
										drawCircleFromRadius(point,new Double(tbLatLngRadius.getText()),60);
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
										drawCircleFromRadius(point,new Double(tbLatLngRadius.getText()),60);
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
									drawCircleFromRadius(point,new Double(tbLatLngRadius.getText()),60);
								}
							 });
				 }
		}
		/**
		 * Deletes all the Overlays displayed on the map
		 */
		else if (event.getSource()==btnSpatialClear)
			{
				map.clearOverlays();
			}
		
//		catch (Exception ex) 
//		 {
//			 System.out.println(ex.getMessage());
//		 }

			
	
		
		else if (event.getSource()==btnLatLngSearch)	
		{	
			Double radius = new Double(tbLatLngRadius.getText());
			lblError.setText("");
			String latChck = new String();
			latChck = tbLatitude.getText();
			String longChck = new String();
			longChck = tbLongitude.getText();
			String spradChck = new String();
			spradChck = tbLatLngRadius.getText();
			if (!FieldVerifier.isaNumber(latChck,longChck))
			{
				lblError.setText("Enter only digits");
				tbLatitude.setValue("Enter again");
				tbLongitude.setValue("Enter again");
				tbLatLngRadius.setValue("Enter again");
				return;
			}
			//Double latCheck = new Integer (tbLatitude.getText());
			//Double longCheck = new Integer (tbLongitude.getText());
			if (!FieldVerifier.isValidNumber(tbLatitude.getText(),tbLongitude.getText())) 
			{
				lblError.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
				tbLatitude.setValue("Enter again");
				tbLongitude.setValue("Enter again");
				tbLatLngRadius.setValue("Enter again");
				return;
			}
			Double lat = new Double(tbLatitude.getText());	
			Double lng = new Double(tbLongitude.getText());
			LatLng point = LatLng.newInstance(lat,lng);
			drawCircleFromRadius(point,radius,60);
			map.setCenter(point,10);
		}
		else if (event.getSource()==tbLatitude)
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
		else if(event.getSource()== btnAttributeClear){
			tbAttributeRadius.setValue("");
			tbAttribute.setValue("");
			map.clearOverlays();
		}
		else if (event.getSource()==btnAttributeSearch)	
		{
////			check for creation of object fea,
		
			String boxText = new String();
			boxText = tbAttribute.getText().trim().replace("'", "\\'");
			fea.searchByAddress(boxText,new AsyncCallback<List<Landmarks>>()
			{
				public void onFailure(Throwable caught) 
				{
				}
			    public void onSuccess(List<Landmarks> result ) 
			    {
			    //	tbAttributeRadius.setValue("Running");
			    	Double radius = new Double(tbAttributeRadius.getText());
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
				    	  map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is " + result.get(row).getPlaceName()));
					}
			    }
			});
		}
}

	private void showAddress(final String address) {
		
		final InfoWindow info = map.getInfoWindow();
		final Label latLabel = new Label();
		final Label lngLabel = new Label();
	    try{
		geocoder.getLatLng(address, new LatLngCallback() {
	      public void onFailure() {
	        //Window.alert(address + " not found");
	    	  System.out.println(address+ " not found");
	      }
	      public void onSuccess(LatLng point) {
	    	 
	    	  map.setCenter(point, 13);
	          Marker marker = new Marker(point);
	          map.addOverlay(marker);
	          drawCircleFromRadius(point,new Double(tbGeoCodedRadius.getText()),60);
	          
	          NumberFormat fmt = NumberFormat.getFormat("#.0000000#");
	  	      latLabel.setText(fmt.format(point.getLatitude()));
	  	      lngLabel.setText(fmt.format(point.getLongitude()));
	          String address1 = address.toUpperCase();
	          String lab = new String("Latitude: " + latLabel + "Longitude: " + lngLabel );
//	          info.open(marker, new InfoWindowContent("Address: " + add ));
	          info.open(marker, new InfoWindowContent(lab +"Address: " + address1 ));
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
			dea.getDistrictsByStateName(stateText, new AsyncCallback<List<Districts>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<Districts> result) 
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
			dea.getLocalBodiesByDistrictName(districtText, new AsyncCallback<List<LocalBodies>>(){
				public void onFailure(Throwable caught) 
			 	{		 
					
			 	}
  		
			 	public void onSuccess(List<LocalBodies> result) 
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

	@UiHandler("btnLatLngSearch")
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
	   
	    
	    map.setSize("765px", "480px");
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
		datagrid.setFirstColumnVisible(true);
		datagrid.setTableModelService(landmarksModelService);
		datagrid.addRowSelectionListener(new RowSelectionListener() {
			public void onRowSelected(AdvancedTable sender, String rowId) {
				lblMessages.setText("Row " + rowId + " selected.");
			}
		});
		datagrid.setSize("1280px", "100px");
		datagrid.setPageSize(5);
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
