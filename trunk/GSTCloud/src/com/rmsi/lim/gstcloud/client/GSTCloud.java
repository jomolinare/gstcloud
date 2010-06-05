package com.rmsi.lim.gstcloud.client;

import java.util.List;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.FieldVerifier;
import com.rmsi.lim.gstcloud.shared.States;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import java.util.HashMap;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/
@SuppressWarnings("deprecation")
public class GSTCloud implements EntryPoint 
{
	
	/**
	 * Map elements in the UI
	 */
	private MapWidget map;
	private VerticalPanel mapWrapper = new VerticalPanel();
	/**
	 * Other UI elements
	 */
	private static final String applicationTitle1 = "Site Search";
	private static final String applicationTitle2 = "RMSI in the Cloud ";
	private static final String applicationTitle3 = "Tools";
	
	private HorizontalPanel hp = new HorizontalPanel();
	private HorizontalPanel tablePanel=new HorizontalPanel();
	
	private VerticalPanel vp = new VerticalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	private VerticalPanel vp2 = new VerticalPanel();
	private VerticalPanel vp3=new VerticalPanel();
	
	private DialogBox dialogBox;
	
	private HTML serverResponseLabel;
	private HTML title1,title2,title3;
  
	private Label textToServerLabel = new Label();
	
	TabPanel queryTabPanel = new TabPanel();
	final FormPanel form = new FormPanel();
	
	final FileUpload upload = new FileUpload();
	final Button loadButton = new Button("Load");
	final Button displayButton = new Button ("Display");
	public Button submitButton =new Button("submit");
	private Button closeButton;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	

	/**
	  * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GisCloudServiceAsync gisCloudService = GWT
			.create(GisCloudService.class);
	private LandmarksServiceAsync fea = GWT
		    .create(LandmarksService.class);
	private LocalBodiesServiceAsync dea = GWT
	        .create(LocalBodiesService.class);

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

			 Polygon circle = new Polygon(circlePoints, "white", 0, 0, "green", 0.5);
			 
			 map.addOverlay(circle);
			 return ;
	}

	
	
	private void StatesLoader()
	{
		 final States s1 = new States("Delhi",28.5332740008996,77.1380750260093);
		 final States s2 = new States("Uttar Pradesh",21.5340600313233,70.1421779768099);
		 final States s3 = new States("Maharashtra",35.5642880083966,72.1446790266595);
		 final States s4 = new States("Kerala",25.5133780333045,74.18106301263);
		 final States s5 = new States("Punjab",18.5143749911163,76.1810389731052);
		 final States s6 = new States("Haryana",14.5162509923103,78.1837590156439);
		 final States s7 = new States("Goa",10.5187349935866,80.1808049982944);
		 final States s8 = new States("Jammu and Kashmir",12.5274640137745,82.1808369601841);
		 final States s9 = new States("Gujarat",30.5241309782258,79.1854310131575);
	
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
	
	private void DistrictsLoader()
	{
		 final Districts d1 = new Districts(new Long(1),"Mukerjee Nagar",28.5332740008773,77.1380750260011);
		 final Districts d2 = new Districts(new Long(1),"Quila Rai Pithora",28.5332740008995,77.1380750260022);
		 final Districts d3 = new Districts(new Long(1),"Mehrauli",28.5332740009000,77.1380750260000);
		 final Districts d4 = new Districts(new Long(2),"Varanasi",21.5340600313233,70.1421779768092);
		 final Districts d5 = new Districts(new Long(3),"Bombay",35.5642880083333,72.1446790266515);
		 final Districts d6 = new Districts(new Long(4),"Ernakulam (Cochin)",25.5133780333045,74.18106301263);
		 final Districts d7 = new Districts(new Long(4),"Kannur",25.5133780334049,74.18106301234);
		 final Districts d8 = new Districts(new Long(5),"Amritsar",18.5143749910063,76.1810389731092);
		 final Districts d9 = new Districts(new Long(5),"Ludhiana",18.5143749910101,76.1810389731022);
		 final Districts d10 = new Districts(new Long(6),"Kaithal",14.5162509923103,78.1837590156439);
		 final Districts d11 = new Districts(new Long(7),"Vasco",10.5187349935866,80.1808049982944);
		 final Districts d12 = new Districts(new Long(8),"Leh Ladakh",12.5274640137745,82.1808369601841);
		 final Districts d13 = new Districts(new Long(8),"Srinagar",30.5241309782258,79.1854310131575);
		 final Districts d14 = new Districts(new Long(8),"Jammu",30.5241309782258,79.1854310131575);
		 final Districts d15 = new Districts(new Long(9),"Ahemdabad",30.5241309782258,79.1854310131575);
		 final Districts d16 = new Districts(new Long(9),"Vadodra",30.5241309782258,79.1854310131575);
	
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
	 * this panel contains the Text boxes for entering Latitude, Longitude and the Specified Radius and contains the buttons Search and 
	 * clear.Search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map
	 */
	
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
						return;
					}
					Integer latCheck = new Integer (latitudeBox.getText());
					Integer longCheck = new Integer (longitudeBox.getText());
					if (!FieldVerifier.isValidNumber(latCheck,longCheck)) 
					{
						errorLabel.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
						return;
					}
									
					Double lat = new Double(latitudeBox.getText());	
					Double lng = new Double(longitudeBox.getText());
					LatLng point = LatLng.newInstance(lat,lng);
					drawCircleFromRadius(point,radius,60);
					map.setCenter(point,5);
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
					Integer latCheck = new Integer (latitudeBox.getText());
					Integer longCheck = new Integer (longitudeBox.getText());
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
	
	/**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 */
	
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
					
					fea.searchByAddress(boxText,new AsyncCallback<List<Landmarks>>()
					{
						public void onFailure(Throwable caught) 
						{
						
						}

					    public void onSuccess(List<Landmarks> result ) 
					    {
					    	map.clearOverlays();
							
							int rowCount = result.size();
							for (int row = 0; row < rowCount; row ++)
							{
								Double lat = result.get(row).getLatitude();
								Double lng = result.get(row).getLongitude();
								
								LatLng point = LatLng.newInstance(lat,lng);
//								// Add a marker
					   	          map.addOverlay(new Marker(point));
					   	          map.setCenter(point, 15);
								// Add an info window to highlight a point of interest
						    	  map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
							}
							
					    }
					});
				}
				else if (event.getSource()==addressClear)
				{
					map.clearOverlays();
				}
			}
		}
		final keyWordHandler addressSearchHandler = new keyWordHandler();
		addressSearch.addClickHandler(addressSearchHandler);
		addressClear.addClickHandler(addressSearchHandler);
	}
	
	/**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 */
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
		final Button clear = new Button("Clear");
  			clear.setStylePrimaryName("Button");
  			hSpatPanel.add(clear);
		
  	vSpatialPanel.setSize("250px", "550px");
		
	
  	vSpatialPanel.add(stateLabel);           
  	vSpatialPanel.add(stateBox);
  		stateBox.setWidth("180px");
  		stateBox.addItem("Select State");
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
  	    if(!dropdown)
  	    	stateBox.setVisibleItemCount(1);

		vSpatialPanel.add(districtLabel);
		vSpatialPanel.add(districtBox);
			districtBox.setWidth("120px"); 
	    vSpatialPanel.add(localBodyLabel);
	    vSpatialPanel.add(localBodyBox);
	    	localBodyBox.setWidth("120px"); 
	    vSpatialPanel.add(speRadLabel);
	    vSpatialPanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
	    	
	    class SpatialHandler implements ClickHandler, ChangeHandler
	    {
			public void onClick(ClickEvent event)
			{
				if (event.getSource()== spatialSearch)	
				{
					if(localBodyBox.getName()== null)
					{
						if(districtBox.getName()== null)
						{
							if(stateBox.getName()==null)
							{
								dialogBox.setText("Atleast Enter name of the State");
							}
							else
							{
								String sName = stateBox.getName();
								dea.displayState(sName,new AsyncCallback<HashMap>()
										{
											public void onFailure(Throwable caught) 
											{
												
											}

											public void onSuccess(HashMap latLong ) 
											{
												Long lat = (Long)latLong.get("Lat");
												Long lng = (Long)latLong.get("Lon");
												
												LatLng point = LatLng.newInstance(lat,lng);
												
												map.addOverlay(new Marker(point));
												map.setCenter(point,15);
											}
										});
							}
						}
						else
						{
							String dName = districtBox.getName();
							dea.displayState(dName,new AsyncCallback<HashMap>()
									{
										public void onFailure(Throwable caught) 
										{
											
										}

										public void onSuccess(HashMap latLong ) 
										{
											Long lat = (Long)latLong.get("Lat");
											Long lng = (Long)latLong.get("Lon");
											
											LatLng point = LatLng.newInstance(lat,lng);
											
											map.addOverlay(new Marker(point));
											map.setCenter(point,15);
										}
									});
						}
					}
					else
					{
						// LocalBody display code
					}
				}
			}
			
			public void onChange(ChangeEvent event)
			{
				if (event.getSource() == stateBox)
				{
					String stateText = new String();
					stateText = stateBox.getName();
					dea.getDistricts(stateText,new AsyncCallback<List<Districts>>()
					{
						public void onFailure(Throwable caught) 
						{
						
						}

					    public void onSuccess(List<Districts> result ) 
					    {
					    	int counter = result.size();
					    	for(int i=0;i<counter;i++)
					    	{
					    		String district = result.get(i).getDistrictName();
					    		districtBox.addItem(district) ;
					    	}
					    }
					});
				}
			}
	    }
	    
	    final SpatialHandler spatHandler = new SpatialHandler();		
	    
	    stateBox.addClickHandler(spatHandler);
	    spatialSearch.addClickHandler(spatHandler);
	    vSpatialPanel.add(hSpatPanel);
		queryTabPanel.add(vSpatialPanel, "Spatial");
	}

	/**
	 * this panel contains the Text boxes for entering Addresses and the Specified Radius of the place to search and contains the buttons Search and 
	 * clear.search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map.
	 */
	
	private void setupAttributePanel()
	{
		VerticalPanel vAttributePanel = new VerticalPanel();
		HorizontalPanel hAttributePanel = new HorizontalPanel();
		
		Label queryIDLabel = new Label("QueryID:");
		Label speRadLabel = new Label("Specified Radius");
		
		TextBox queryIDBox = new TextBox();
	    TextBox SpeRadBox = new TextBox();
	    
	    Button search = new Button("Search ");
	    	search.setStylePrimaryName("Button");
	    Button clear = new Button("Clear");
	    	clear.setStylePrimaryName("Button");
	    
	    vAttributePanel.add(queryIDLabel);
	    vAttributePanel.add(queryIDBox);
	    	queryIDBox.setWidth("120px"); 
	    vAttributePanel.add(speRadLabel);
	    vAttributePanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
	         
	    hAttributePanel.add(search);
	    hAttributePanel.add(clear);
		vAttributePanel.add(hAttributePanel);	    
		
	    queryTabPanel.add(vAttributePanel, "Attribute");
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
		vp1.add(hp5);
						
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
		}
		
		/**
		 * Fired when the user types in the nameField.
		 */
	
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
	    
	    /*
	     * Disable double-click for zoom so we can 
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
	    map.addMapZoomEndHandler(new MapZoomEndHandler(){

			public void onZoomEnd(MapZoomEndEvent event) {
				
				/*
				 * We want a string to make a box, like this:
				 * "lng1 lat1, lng2 lat2, lng3 lat3, lng4 lat4, lng1 lat1"
				 */
			    LatLngBounds bounds = map.getBounds();
				LatLng ne = bounds.getNorthEast();
				LatLng sw = bounds.getSouthWest();
				
				double nLat = ne.getLatitude();
				double eLng = ne.getLongitude();
				double sLat = sw.getLatitude();
				double wLng = sw.getLongitude();
//				
				/*
				 * Start at ne and go clockwise
				 */
				String nePair = Double.toString(eLng) + " " + Double.toString(nLat);
				String sePair = Double.toString(eLng) + " " + Double.toString(sLat);
				String swPair = Double.toString(wLng) + " " + Double.toString(sLat);
          	String nwPair = Double.toString(wLng) + " " + Double.toString(nLat);
//				
				/*
				 * Finish bbox back at ne
				 */
				String bbox = nePair + "," + sePair + "," + swPair + "," + nwPair + "," + nePair;
				
				textToServerLabel.setText(bbox);
				sendMapBounds(bbox);
			
			}
				
	    });
	    
	    /*
	     * Let's repeat the zoom handler funtionality as a drag end handler. If
	     * the user is zoomed in, and is within the county, we want to warn them
	     * if they drag the map beyond the edge of the county too.
	     */
	    map.addMapDragEndHandler(new MapDragEndHandler(){
			public void onDragEnd(MapDragEndEvent event) {
				
				/*
				 * We want a string to make a box, like this:
				 * "lng1 lat1, lng2 lat2, lng3 lat3, lng4 lat4, lng1 lat1"
				 */
				LatLngBounds bounds = map.getBounds();
				LatLng ne = bounds.getNorthEast();
				LatLng sw = bounds.getSouthWest();				
				double nLat = ne.getLatitude();
				double eLng = ne.getLongitude();
				double sLat = sw.getLatitude();
				double wLng = sw.getLongitude();
				
				/*
				 * Start at ne and go clockwise
				 */
				String nePair = Double.toString(eLng) + " " + Double.toString(nLat);
				String sePair = Double.toString(eLng) + " " + Double.toString(sLat);
				String swPair = Double.toString(wLng) + " " + Double.toString(sLat);
				String nwPair = Double.toString(wLng) + " " + Double.toString(nLat);
				
				/*
				 * Finish bbox back at ne
				 */
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
		
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		title2 = new HTML(applicationTitle2);
		Window.setTitle(applicationTitle2);
		title2.setStylePrimaryName("title");
		vp.add(title2);
		vp.setCellHorizontalAlignment(title2, HasHorizontalAlignment.ALIGN_CENTER);
		//setupDialogBox();
		StatesLoader();
		DistrictsLoader();
		setupLatLongPanel();
		setupKeywordPanel();
		setupSpatialPanel();
		setupAttributePanel();
		setupQueryPanels();
		
		hp.add(vp1);
	
		setupMap();
		//creating vertical panel for Map
		hp.add(mapWrapper);
		setupToolsPanel();
		hp.add(vp2);
	    vp.add(hp);
	    /**vp.add(mapWrapper);
	    vp.setCellHorizontalAlignment(mapWrapper, HasHorizontalAlignment.ALIGN_CENTER);*/
	    // style the vp
	    vp.setWidth("100%");
	    vp.setStylePrimaryName("vp");
	    // Create the popup dialog box
		
		
	 // add flex table
      
	    FlexTable table = new FlexTable();
	    
	// Put some text at the table's extremes.  This forces the table to be 4 by 4
	    table.setText(0,0,"Category");
		table.setText(0,1,"Latitude");
		table.setText(0,2,"Longitude");
		table.setText(0,3,"Place Name");
		
      table.setBorderWidth(3);
 
		     //...and set it's column span so that it takes up the whole row.
      table.getFlexCellFormatter().setColSpan(0, 1, 2);		
      
		tablePanel.add(table); 
		vp.add(tablePanel);		    
	
		RootPanel.get().add(vp);
	    
	}

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
	
	}}
