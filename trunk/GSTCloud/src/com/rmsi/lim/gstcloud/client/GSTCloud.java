package com.rmsi.lim.gstcloud.client;



import java.util.List;
//import org.emcode.samples.giscloud.client.FieldVerifier;
import org.datanucleus.store.scostore.ListStore;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.core.java.util.Arrays;
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
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


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
	private Label errorLabel = new Label();
	
	TabPanel queryTabPanel = new TabPanel();
	final FormPanel form = new FormPanel();
	
	final FileUpload upload = new FileUpload();
	final Button loadButton = new Button("Load");
	final Button displayButton = new Button ("Display");
	public Button submitButton =new Button("submit");
	private Button closeButton;
	
	
	private TextBox textbox = new TextBox();
	
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
	private LandmarksServiceAsync fea =GWT
		    .create(LandmarksService.class);
	

//	private void centerMap()
//	{
//		errorLabel.setText("");
//		String latChck = new String();
//		latChck = latitudeBox.getText();
//		String longChck = new String();
//		longChck = longitudeBox.getText();
//		if (!FieldVerifier.isaNumber(latChck,longChck))
//		{
//			errorLabel.setText("Enter only digits");
//			return;
//		}
//		Integer latCheck = new Integer (latitudeBox.getText());
//		Integer longCheck = new Integer (longitudeBox.getText());
//		if (!FieldVerifier.isValidNumber(latCheck,longCheck)) 
//		{
//			errorLabel.setText("Please enter the latitude b/w -90 to +90 and longitude b/w -180 to 180");
//			return;
//		}
//		
//	
//		map.setCenter(LatLng.newInstance(new Double(latitudeBox.getText()),new Double(longitudeBox.getText())));
//	}
	
//	private final Landmarks r1 = new Landmarks("Landmark",28.5332740008996,77.1380750260093,"SULTAN GHORI'S TOMB");
//	private final Landmarks r2 = new Landmarks("Landmark",21.5340600313233,70.1421779768099,"RANG PURI");
//	private final Landmarks r3 = new Landmarks("Landmark",35.5642880083966,72.1446790266595,"MURADABAD PAHARI FORT");
//	private final Landmarks r4 = new Landmarks("Landmark",25.5133780333045,74.18106301263,"DHOBIYAN MOSQUE");
//	private final Landmarks r5 = new Landmarks("Landmark",18.5143749911163,76.1810389731052,"JAHAZ MAHAL");
//	private final Landmarks r6 = new Landmarks("Landmark",14.5162509923103,78.1837590156439,"KALA MAHAL");
//	private final Landmarks r7 = new Landmarks("Landmark",10.5187349935866,80.1808049982944,";LAL MAHAL");
//	private final Landmarks r8 = new Landmarks("Landmark",12.5274640137745,82.1808369601841,"QILA LAL KOT");
//	private final Landmarks r9 = new Landmarks("Landmark",30.5241309782258,79.1854310131575,"ALA-UD-DIN'S TOMB");
	
	/**
	 * this panel contains the Text boxes for entering Latitude, Longitude and the Specified Radius and contains the buttons Search and 
	 * clear.Search is used to search according to the textbox values and clear is to clear all the overlays displayed on the map
	 */
	
	private void setupLatLongPanel()
	{
		
		
		VerticalPanel vLatLongPanel = new VerticalPanel();
	    HorizontalPanel hLtLngPanel = new HorizontalPanel();
//		Button centerButton = new Button("Center");
//			centerButton.setStylePrimaryName("Button");
		Button search = new Button("Search ");
			search.setStylePrimaryName("Button");
			hLtLngPanel.add(search);
		Button clear = new Button("Clear");
			clear.setStylePrimaryName("Button");
			hLtLngPanel.add(clear);
		
		TextBox latitudeBox = new TextBox();
		TextBox longitudeBox = new TextBox();
		TextBox SpeRadBox = new TextBox();
		
		Label latLabel = new Label("P1 Latitude");
		Label longLabel = new Label("P1 Longitude");
	    Label speRadLabel = new Label("Specified Radius");
		
	    vLatLongPanel.setSize("200px", "450px");
		
	    vLatLongPanel.add(latLabel);
	    vLatLongPanel.add(latitudeBox);
			latitudeBox.setText("Enter Lattitude");
			latitudeBox.setWidth("180px");
			latitudeBox.addKeyUpHandler(handler);
		vLatLongPanel.add(longLabel);
		vLatLongPanel.add(longitudeBox);
			longitudeBox.setText("Enter Longitude");
			longitudeBox.setWidth("180px");
			longitudeBox.addKeyUpHandler(handler);
		vLatLongPanel.add(speRadLabel);
	    vLatLongPanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 
				
//		centerButton.addClickHandler(handler);
//		hp1.add(centerButton);
		
	    vLatLongPanel.add(hLtLngPanel);
		queryTabPanel.add(vLatLongPanel, "LatLong");
		queryTabPanel.selectTab(0);
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
		TextBox SpeRadBox = new TextBox();
		
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
					
					fea.searchStation(boxText,new AsyncCallback<List<Landmarks>>()
					{
						public void onFailure(Throwable caught) 
						{
						//
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
//		ListStore<String> store = new ListStore<String>(); 
//		store.add( Arrays.asList( new String[]{"A","B","C"}));
		Boolean dropdown = false;
		
		VerticalPanel vSpatialPanel = new VerticalPanel();
		HorizontalPanel hSpatPanel = new HorizontalPanel();
		
		Label stateLabel = new Label("State:");
		Label districtLabel = new Label("District:");
		Label localBodyLabel = new Label("Local Body:");
		Label speRadLabel = new Label("Specified Radius");
		
		ListBox stateBox = new ListBox();
//		TextBox  stateBox = new TextBox();
		TextBox districtBox = new TextBox();
		TextBox villageBox = new TextBox();
		TextBox SpeRadBox = new TextBox();
		
		Button search = new Button("Search ");
        	search.setStylePrimaryName("Button");
        	hSpatPanel.add(search);
        Button clear = new Button("Clear");
    		clear.setStylePrimaryName("Button");
    		hSpatPanel.add(clear);
		
    	vSpatialPanel.setSize("250px", "550px");
		
		
    	vSpatialPanel.add(stateLabel);
    	vSpatialPanel.add(stateBox);
    		stateBox.setWidth("180px");
    		stateBox.addItem("One");
    		stateBox.addItem("Two");
    		stateBox.addItem("Three");
    		stateBox.addItem("Four");
    		stateBox.addItem("Five");
    	    if(!dropdown)
    	    	stateBox.setVisibleItemCount(1);

		vSpatialPanel.add(districtLabel);
		vSpatialPanel.add(districtBox);
			districtBox.setWidth("120px"); 
	    vSpatialPanel.add(localBodyLabel);
	    vSpatialPanel.add(villageBox);
	    	villageBox.setWidth("120px"); 
	    vSpatialPanel.add(speRadLabel);
	    vSpatialPanel.add(SpeRadBox);
	    	SpeRadBox.setWidth("120px"); 		
		
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
	
	class MyHandler implements ClickHandler, KeyUpHandler
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
					public void onFailure(Throwable caught) {
						System.out.println("failure");
						
					}

					public void onSuccess(String result) {
						System.out.println("success");
					
				}
						
						
				}		
						
						
						
						//geoCallBack
						
				
				
				
				
				
				);
				
				
				
				
				
				
				
				
				System.out.println("file uploaded");
				//System.out.println(upload.getFilename());
				
			
			
				
		
				//vp.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
				
				
			}
			
			
//			if (event.getSource()==loadButton)
//			{
//				final AsyncCallback geoCallBack= new AsyncCallback<String>() 
//				{
//					public void onFailure(Throwable caught) 
//					{
//						//System.out.println("failure");	
//					}
//
//					public void onSuccess(String result) 
//					{
//						System.out.println("success");
//					} 
//				};
//				fea.loadStation(r1, geoCallBack); 
//				fea.loadStation(r2, geoCallBack);
//				fea.loadStation(r3, geoCallBack);
//				fea.loadStation(r4, geoCallBack);
//				fea.loadStation(r5, geoCallBack);
//				fea.loadStation(r6, geoCallBack);
//				fea.loadStation(r7, geoCallBack);
//				fea.loadStation(r8, geoCallBack);
//				fea.loadStation(r9, geoCallBack);
//			}
			else if (event.getSource()==displayButton) 
					 fea.displayStation(new AsyncCallback<List<Landmarks>>() 
					 {
						 public void onFailure(Throwable caught) 
						 {
					 // Show the RPC error message to the user 
					//						dialogBox.setText("Remote Procedure Call - Failure");					//						serverResponseLabel.addStyleName("serverResponseLabelError");
					//						serverResponseLabel.setHTML(SERVER_ERROR);
					//						dialogBox.center();
					//						closeButton.setFocus(true);
					     }

						 public void onSuccess(List<Landmarks> result) 
								{
									//	FlexTable resultsPanel = new FlexTable();			
									// 	Field[] fields=result.get(0).getClass().getDeclaredFields();
									//	int colCount = fields.length;
									int rowCount = result.size();
						
									// 	resultsPanel.setBorderWidth(2);
									// 	if (colCount != 0) 
									//	 	{
									//			for (int col = 0; col < colCount; col ++) 
									//			    {
									//					resultsPanel.getColumnFormatter().setWidth(col,"20px" );
									//	  			    Label newLabel= new Label(fields[col].getName());
									//				    resultsPanel.setText(0, col, fields[col].getName());
									//			    }
									//				    
									//		   //System.out.println(fields[col]);
									//	 	}
									for (int row = 0; row < rowCount; row ++) 
									{
										//								
										//								if (colCount != 0) 
										//								{
										//								

	                                    //for (int col = 0; col < colsize; col ++) 
										//								

	   
										//					 
										//		resultsPanel.setText(row,0,result.get(row).getid().toString() );
										//		resultsPanel.setText(row,1,result.get(row).getCategory() );
						                //      resultsPanel.setText(row,2,result.get(row).getLatitude().toString() );
										//		resultsPanel.setText(row,3,result.get(row).getLongitude().toString() );
										//		resultsPanel.setText(row,4,result.get(row).getPlaceName());
										//						
										//					
										String cat = result.get(row).getCategory();	
										Double lat = result.get(row).getLatitude();
										Double lng = result.get(row).getLongitude();
										String name = result.get(row).getPlaceName();
										//
										//								
										//								
										LatLng point = LatLng.newInstance(lat,lng);
										//								
										
                                      // Add a marker
										map.addOverlay(new Marker(point));
										map.setCenter(point,15);
										
										// Add an info window to highlight a point of interest
										map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
									}	
									// CODE BREAKS IN THE STATEMENT BELOW
									////	if (row == 0) getCellFormatter().setStyleName(row, col,style.axisrow());
									////	else if (col == 0) getCellFormatter().setStyleName(row, col,style.axiscol());
									////	else getCellFormatter().setStyleName(row, col, style.regular());									
									//							}
								}
				
					 });
			
			/**
			 * Fired when the user clicks on the CenterButton.
			 */
				
//			else if (event.getSource()==centerButton)
//					 centerMap();
			
		}
		
		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) 
		{
//			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
//			{
//				centerMap();
//			}
		}
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
	
	}}
