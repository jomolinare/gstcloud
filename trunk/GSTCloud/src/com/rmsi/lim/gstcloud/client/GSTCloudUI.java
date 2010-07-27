package com.rmsi.lim.gstcloud.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
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
import com.google.gwt.maps.client.event.GroundOverlayVisibilityChangedHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.GroundOverlayVisibilityChangedHandler.GroundOverlayVisibilityChangedEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapMoveEndHandler.MapMoveEndEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.GroundOverlay;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
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
import com.rmsi.lim.gstcloud.client.interfaces.CSCService;
import com.rmsi.lim.gstcloud.client.interfaces.CSCServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.CSCTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.CSCTableModelServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudService;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LayerService;
import com.rmsi.lim.gstcloud.client.interfaces.LayerServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.RetailerService;
import com.rmsi.lim.gstcloud.client.interfaces.RetailerServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.RetailerTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.RetailerTableModelServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.RowSelectionListener;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesService;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.TowerService;
import com.rmsi.lim.gstcloud.client.interfaces.TowerServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelServiceAsync;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;
import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.model.Layer;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.RetailerDTO;
import com.rmsi.lim.gstcloud.client.model.State;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.rmsi.lim.gstcloud.client.utilities.FieldVerifier;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudConstants;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudSharedConstants;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudUtils;
import com.rmsi.lim.gstcloud.client.view.AdvancedTable;
import com.rmsi.lim.gstcloud.client.view.LayerManager;
import com.rmsi.lim.gstcloud.client.utilities.GSTCloudSharedConstants;

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
 //Fields for Header
 @UiField
 public Label lblsearchType;
 
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
 
 //Fields for Footer
 @UiField 
 Label lblCenterPoint;
 
 //Fields for ToolPanel
 @UiField
 VerticalPanel toolsPanel;
 
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
	
	private AdvancedTable datagrid ;//= new AdvancedTable();
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
	private final LandmarksTableModelServiceAsync landmarksModelService=GWT
	.create(LandmarksTableModelService.class);
	LayerManager lm = new LayerManager(this);
	private final TowerTableModelServiceAsync towerModelService=GWT
	.create(TowerTableModelService.class);
	private final TowerServiceAsync towerService = GWT
    .create(TowerService.class);
	private final CSCTableModelServiceAsync cscModelService=GWT
	.create(CSCTableModelService.class);
	private final CSCServiceAsync cscService = GWT
    .create(CSCService.class);
	private final RetailerTableModelServiceAsync retailerModelService=GWT
	.create(RetailerTableModelService.class);
	private final RetailerServiceAsync retailerService = GWT
    .create(RetailerService.class);
	
	private String selectedLayer;

	private List<LatLng> listofClicks = new ArrayList<LatLng>();
	
	private InfoWindowContent createTowerInfoWindowContent(LatLng point, TowerDTO tw){
		final LatLng localPoint=point;
		final TowerDTO localTower=tw;
		 VerticalPanel vpc =new VerticalPanel();
	          FlexTable table =new FlexTable();
	          table.setText(0, 0 ,"Category:");
	          table.setText(0, 1 ,tw.getCategory());
	          table.setText(1, 0 ,"Latitude:");
	          table.setText(1, 1 ,tw.getLatitude().toString());
	          table.setText(2, 0 ,"Longitude:");
	          table.setText(2, 1 ,tw.getLongitude().toString());
	          table.setText(3, 0 ,"Tower Name:");
	          table.setText(3, 1 ,tw.getName());
	          table.setText(4, 0 ,"Status:");
	          table.setText(4, 1 ,tw.getStatus());
	          table.setText(5, 0 ,"Owner:");
	          table.setText(5, 1 ,tw.getOwner());
	          table.setText(6, 0 ,"Coverage:");
	          table.setText(6, 1 ,tw.getCoverage());
	          table.setText(7, 0 ,"Height:");
	          table.setText(7, 1 ,tw.getHeight().toString());
	         
	          
	          final Button btnShowCoverage =new Button("Show Coverage");
	          LatLngBounds rectBounds = LatLngBounds.newInstance(LatLng.newInstance(
	            		 localPoint.getLatitude() - 0.01, localPoint.getLongitude()
	                     - 0.01), LatLng.newInstance(localPoint.getLatitude() +0.01,
	                    		 localPoint.getLongitude() + 0.01));	 
	          final GroundOverlay overlay =new GroundOverlay("images/Coverages/"+localTower.getCoverage(),rectBounds);
	          overlay.addGroundOverlayVisibilityChangedHandler(new GroundOverlayVisibilityChangedHandler() {

	             public void onVisibilityChanged(
	                 GroundOverlayVisibilityChangedEvent event) {
	               if (event.isVisible()) {
	            	btnShowCoverage.setText("Hide Coverage");
	               } else {
	            	btnShowCoverage.setText("Show Coverage");
	               }
	             }

	           });
	          map.addOverlay(overlay);
	          overlay.setVisible(false);
	          btnShowCoverage.addClickHandler(new ClickHandler(){
	        	 public void onClick(ClickEvent event) {
	        		         	
	        	  
	        	  if (overlay.isVisible()) {
	        		  btnShowCoverage.setText("Show Coverage");
        	        } else {
        	        	btnShowCoverage.setText("Hide Coverage");
        	        }
        	        overlay.setVisible(!overlay.isVisible());
	        	 }
	          });
	          vpc.add(table);
	          vpc.add(btnShowCoverage);
	          InfoWindowContent content = new InfoWindowContent(vpc);
	          return content;
	}
	
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
						 			GSTCloudUtils.StatesLoader();
						 			GSTCloudUtils.DistrictsLoader();
						 			GSTCloudUtils.LocalBodyLoader();
						 		}
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
						 			
							}
			  		});
		
		
		lbState.addItem("Select State");
		lbDistrict.addItem("No Available Districts");
		lbLocalBody.addItem("No Available Villages/Towns");
		setupMap();
		//hpAdmin.add(upload);
		GSTCloudUtils.layerLoader();
		setupLayerManager();
		setupTablePanel();
		setupUninorDemo();
	}
		
	private void setupUninorDemo(){
		
		final Polygon UninorSalesTerritory = new Polygon(new LatLng[]
		                            {LatLng.newInstance(28.538084182259816, 77.12526798248291),
									 LatLng.newInstance(28.54713206234115, 77.14226245880127),
									 LatLng.newInstance(28.535595879000425, 77.16075897216797),
									 LatLng.newInstance(28.523266600650622, 77.14496612548828),
									 LatLng.newInstance(28.528922411976097, 77.1419620513916)/*,
									 LatLng.newInstance(28.538084182259816, 77.12526798248291),
									 LatLng.newInstance(28.538084182259816, 77.12526798248291),
									 LatLng.newInstance(28.538084182259816, 77.12526798248291),
									 LatLng.newInstance(28.538084182259816, 77.12526798248291)*/
									
											},"red", 1, 1, "red", 0.5);
		
		final Button btnShowSearchOverlay = new Button("Create Search Overlay");
		final Button btnHideTerritory = new Button("Clear Territory");
		final Button btnSelectRetailers= new Button ("Select Retailers");
		final Button btnShowTerritoryOverLay= new Button ("Show Resulting Territory");
		
		btnShowSearchOverlay.setEnabled(false);
		btnShowTerritoryOverLay.setEnabled(false);
		btnHideTerritory.setEnabled(false);
		
		final MapClickHandler uniclick = 
		new MapClickHandler() {
		      public void onClick(MapClickEvent e) {
		        
		    	MapWidget sender = e.getSender();
		    	btnShowSearchOverlay.setEnabled(true);
		    	Overlay overlay = e.getOverlay();
		        LatLng point = e.getLatLng();

		       /* if (overlay != null && overlay instanceof Marker) {
		          sender.removeOverlay(overlay);
		          
		        } else */{
		          //sender.addOverlay(new Marker(point));
		        	Icon icon = Icon.newInstance("http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/retailer-icon.png");
		        //Icon icon = Icon.newInstance(
		        //"file://RetailerIcon.png");
		        icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
		        icon.setIconSize(Size.newInstance(12, 20));
		        icon.setShadowSize(Size.newInstance(22, 20));
		        icon.setIconAnchor(Point.newInstance(6, 20));
		        icon.setInfoWindowAnchor(Point.newInstance(5, 1));
		        MarkerOptions options = MarkerOptions.newInstance();
		        options.setIcon(icon);
		        	
		         sender.addOverlay(new Marker(point, options));
		         listofClicks.add(point);
		        }
		      }
		     };
		    
		ClickHandler territoryClickHandler =new ClickHandler() {
			  Polygon territoryOverlay ;
		      public void onClick(ClickEvent event) {
		    	  if (event.getSource()==btnSelectRetailers)
		    	  {
		    		  listofClicks.clear();
		    		  map.clearOverlays();
		    		  map.addMapClickHandler(uniclick);
					  btnShowSearchOverlay.setEnabled(true);
					  
		    	  }
		      else if (event.getSource()==btnShowSearchOverlay){
		    		btnSelectRetailers.setEnabled(false);  
			        map.removeMapClickHandler(uniclick);
			        listofClicks.add(listofClicks.get(0));
			        //map.clearOverlays();
			        territoryOverlay = new Polygon(listofClicks.toArray(new LatLng[0]), "green", 1, 1, "green", 0.5);
			    	map.addOverlay(territoryOverlay);
			    	btnShowSearchOverlay.setEnabled(false);
			    	btnHideTerritory.setEnabled(true);
			    	btnShowTerritoryOverLay.setEnabled(true);
			        //Create and add overlay 
			        //toggleOverlay();
			    	}
			     
			      else if (event.getSource()==btnHideTerritory){
			    	  /*if (territoryOverlay!=null){
			    	  map.removeOverlay(territoryOverlay);
			    	  
			    	  listofClicks.clear();
			    	  //showOverLayButton.setEnabled(true);
			    	  btnSelectRetailers.setEnabled(true);  
			    	  }*/
			    	  map.removeOverlay(territoryOverlay);
			    	  
			    	  listofClicks.clear();
			    	  map.removeOverlay(UninorSalesTerritory);
			    	  btnSelectRetailers.setEnabled(true);
			    	  btnShowTerritoryOverLay.setEnabled(false);
			    	  btnShowSearchOverlay.setEnabled(false);
				      btnHideTerritory.setEnabled(false);
			      }
			      else if (event.getSource()==btnShowTerritoryOverLay){
			    	  if (territoryOverlay!=null){
				    	  map.removeOverlay(territoryOverlay);
				    	  //map.clearOverlays();
				    	  //listofClicks.clear();
				    	  map.addOverlay(UninorSalesTerritory);
				    	  //showOverLayButton.setEnabled(true);
				    	  btnSelectRetailers.setEnabled(true);
				    	  btnShowSearchOverlay.setEnabled(false);
					      btnHideTerritory.setEnabled(true);
					      
				    	  }
				      }
		      }};
		
		btnSelectRetailers.addClickHandler(territoryClickHandler);
		toolsPanel.add(btnSelectRetailers);
		btnShowSearchOverlay.addClickHandler(territoryClickHandler);
		toolsPanel.add(btnShowSearchOverlay);
		btnShowTerritoryOverLay.addClickHandler(territoryClickHandler);
		toolsPanel.add(btnShowTerritoryOverLay);
	    btnHideTerritory.addClickHandler(territoryClickHandler);
	    toolsPanel.add(btnHideTerritory);
	   
	    //initWidget(panel);
	}
	
	/**
	 * This Function is used to find the Landmarks within a circle using Proximity Search of JavaGeoModel
	 * @param point Centerpoint of the search circle
	 * @param radius Radius of the search circle
	 * @param map 
	 */
		
	private void drawPointsWithinCircle(final LatLng point, final Double radius)
	{
		final Double localRadius=radius;
		final LatLng localPoint =point;
		 
		if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0){
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
		    	LatLngBounds bounds = LatLngBounds.newInstance();
		    	int rowCount = result.size();
		    	
		    	map.addOverlay(GSTCloudUtils.drawSearchCircleOnScreen(localPoint,localRadius,60));
		    	map.setCenter(localPoint, 10);
		    	//map.addOverlay(new Marker(localPoint));
				for (int row = 0; row < rowCount; row ++)
				{
					final LandmarkDTO lm =result.get(row);
					final LatLng point = LatLng.newInstance(lm.getLatitude(),lm.getLongitude());
//					// Add a marker
					final Marker marker =new Marker(point);
                    bounds.extend(point);
		   	         marker.addMarkerClickHandler(new MarkerClickHandler() {
		   	          public void onClick(MarkerClickEvent event) {
		   	          InfoWindow info = map.getInfoWindow();
		   	          info.open(marker,
		   	              new InfoWindowContent("<tr><td>Category: </td><td>" +lm.getCategory()+
		   	            		  "</td></tr><tr><td>Latitude: </td><td>"+lm.getLatitude()+
		   	            		  "</td></tr><tr><td>Longitude: </td><td>"+lm.getLongitude()+
		   	            		  "</td></tr><tr><td>Landmark Name: </td><td>"+lm.getPlaceName()+
		   	            		  "</td></tr>"));
		   	        }
		   	      });
		   	      map.addOverlay(marker);
				}
				map.setCenter(bounds.getCenter());
				map.setZoomLevel(map.getBoundsZoomLevel(bounds)-1);
				datagrid.setCenterPoint(localPoint);
				datagrid.setSearchRadius(localRadius);
				datagrid.updateTableData();
		    }
		});
		}
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0)
		{
			towerService.displayTowersWithinDistance
			(localPoint.getLatitude(), 
			 localPoint.getLongitude(),
			 localRadius, 
			 new AsyncCallback<List<TowerDTO>>()
			{
				public void onFailure(Throwable caught) 
				{
				}
			    public void onSuccess(List<TowerDTO> result ) 
			    {
			    //	tbAttributeRadius.setValue("Running");
			    	int rowCount = result.size();
			    	
			    	map.addOverlay(GSTCloudUtils.drawSearchCircleOnScreen(localPoint,localRadius,60));
			    	map.setCenter(localPoint, 10);
			    	//map.addOverlay(new Marker(localPoint));
					for (int row = 0; row < rowCount; row ++)
					{
						final TowerDTO tw =result.get(row);
						final LatLng point = LatLng.newInstance(tw.getLatitude(),tw.getLongitude());
						
//						// Add a marker
						String iconName=
					        "http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/"+tw.getStatus()+".png";
						Icon icon = Icon.newInstance(iconName);
				         icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
				         icon.setIconSize(Size.newInstance(12, 20));
				         icon.setShadowSize(Size.newInstance(22, 20));
				         icon.setIconAnchor(Point.newInstance(6, 20));
				         icon.setInfoWindowAnchor(Point.newInstance(5, 1));
				         MarkerOptions options = MarkerOptions.newInstance();
				         options.setIcon(icon);
						final Marker marker =new Marker(point,options);
						/*final Marker marker =new Marker(point);
									   	          */
			   	         marker.addMarkerClickHandler(new MarkerClickHandler() {
			   	          public void onClick(MarkerClickEvent event) {
			   	          InfoWindow info = map.getInfoWindow();
			   	         
			   	          info.open(marker, createTowerInfoWindowContent(point,tw));
			   	         /* info.open(marker,
			   	              new InfoWindowContent("<tr><td>Category: </td><td>" +tw.getCategory()+
			   	            		  "</td></tr><tr><td>Latitude: </td><td>"+tw.getLatitude()+
			   	            		  "</td></tr><tr><td>Longitude: </td><td>"+tw.getLongitude()+
			   	            		  "</td></tr><tr><td>Tower Name: </td><td>"+tw.getName()+
			   	            		"</td></tr><tr><td>Status: </td><td>"+tw.getStatus()+
			   	            		"</td></tr><tr><td>Owner: </td><td>"+tw.getOwner()+
			   	            		"</td></tr><tr><td>Coverage: </td><td>"+tw.getCoverage()+
			   	            		"</td></tr><tr><td>Height: </td><td>"+tw.getHeight().toString()+
			   	            		  "</td></tr>"));*/
			   	        }
			   	      });
			   	      map.addOverlay(marker);
					}
					datagrid.setCenterPoint(localPoint);
					datagrid.setSearchRadius(localRadius);
					datagrid.updateTableData();
			    }
			});
		}
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0){
			cscService.displayCSCsWithinDistance
			(localPoint.getLatitude(), 
			 localPoint.getLongitude(),
			 localRadius, 
			 new AsyncCallback<List<CSCDTO>>()
			{
				public void onFailure(Throwable caught) 
				{
				}
			    public void onSuccess(List<CSCDTO> result ) 
			    {
			    //	tbAttributeRadius.setValue("Running");
			    	int rowCount = result.size();
			    	
			    	map.addOverlay(GSTCloudUtils.drawSearchCircleOnScreen(localPoint,localRadius,60));
			    	map.setCenter(localPoint, 10);
			    	//map.addOverlay(new Marker(localPoint));
					for (int row = 0; row < rowCount; row ++)
					{
						final CSCDTO csc =result.get(row);
						final LatLng point = LatLng.newInstance(csc.getLatitude(),csc.getLongitude());
//						// Add a marker
						Icon icon = Icon.newInstance("http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/csc.png");
				        //Icon icon = Icon.newInstance(
				        //"file://RetailerIcon.png");
				        icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
				        icon.setIconSize(Size.newInstance(12, 20));
				        icon.setShadowSize(Size.newInstance(22, 20));
				        icon.setIconAnchor(Point.newInstance(6, 20));
				        icon.setInfoWindowAnchor(Point.newInstance(5, 1));
				        MarkerOptions options = MarkerOptions.newInstance();
				        options.setIcon(icon);
						final Marker marker =new Marker(point,options);
			   	          
			   	         marker.addMarkerClickHandler(new MarkerClickHandler() {
			   	          public void onClick(MarkerClickEvent event) {
			   	          InfoWindow info = map.getInfoWindow();
			   	          info.open(marker,
			   	        		new InfoWindowContent("<tr><td>Category: </td><td>" +csc.getCategory()+
				   	            		  "</td></tr><tr><td>Latitude: </td><td>"+csc.getLatitude()+
				   	            		  "</td></tr><tr><td>Longitude: </td><td>"+csc.getLongitude()+
				   	            		"</td></tr><tr><td>CSC Name: </td><td>"+csc.getName()+
				   	            		  "</td></tr><tr><td>Tower Name: </td><td>"+csc.getTower_name()+
				   	            		"</td></tr><tr><td>Contact_person: </td><td>"+csc.getContact_person()+
				   	            		"</td></tr><tr><td>Address: </td><td>"+csc.getAddress()+
				   	            		  "</td></tr>"));
			   	        }
			   	      });
			   	      map.addOverlay(marker);
					}
					datagrid.setCenterPoint(localPoint);
					datagrid.setSearchRadius(localRadius);
					datagrid.updateTableData();
			    }
			});
			}
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0)
		{
			retailerService.displayRetailersWithinDistance
			(localPoint.getLatitude(), 
			 localPoint.getLongitude(),
			 localRadius, 
			 new AsyncCallback<List<RetailerDTO>>()
			{
				public void onFailure(Throwable caught) 
				{
				}
			    public void onSuccess(List<RetailerDTO> result ) 
			    {
			    //	tbAttributeRadius.setValue("Running");
			    	int rowCount = result.size();
			    	
			    	map.addOverlay(GSTCloudUtils.drawSearchCircleOnScreen(localPoint,localRadius,60));
			    	map.setCenter(localPoint, 10);
			    	//map.addOverlay(new Marker(localPoint));
					for (int row = 0; row < rowCount; row ++)
					{
						final RetailerDTO re =result.get(row);
						final LatLng point = LatLng.newInstance(re.getLatitude(),re.getLongitude());
//						// Add a marker
						Icon icon = Icon.newInstance("http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/retailer-icon.png");
				        //Icon icon = Icon.newInstance(
				        //"file://RetailerIcon.png");
				        icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
				        icon.setIconSize(Size.newInstance(12, 20));
				        icon.setShadowSize(Size.newInstance(22, 20));
				        icon.setIconAnchor(Point.newInstance(6, 20));
				        icon.setInfoWindowAnchor(Point.newInstance(5, 1));
				        MarkerOptions options = MarkerOptions.newInstance();
				        options.setIcon(icon);
						final Marker marker =new Marker(point,options);
			   	          
			   	         marker.addMarkerClickHandler(new MarkerClickHandler() {
			   	          public void onClick(MarkerClickEvent event) {
			   	          InfoWindow info = map.getInfoWindow();
			   	          info.open(marker,
			   	              new InfoWindowContent("<tr><td>Category: </td><td>" +re.getCategory()+
			   	            		  "</td></tr><tr><td>Latitude: </td><td>"+re.getLatitude()+
			   	            		  "</td></tr><tr><td>Longitude: </td><td>"+re.getLongitude()+
			   	            		  "</td></tr><tr><td>Retailer Name: </td><td>"+re.getName()+
			   	            		"</td></tr><tr><td>Address: </td><td>"+re.getAddress()+
			   	            		  "</td></tr>"));
			   	        }
			   	      });
			   	      map.addOverlay(marker);
					}
					datagrid.setCenterPoint(localPoint);
					datagrid.setSearchRadius(localRadius);
					datagrid.updateTableData();
			    }
			});
		}
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
		selectedLayer=lm.getLt().getSelectedItem().getText();
		eventMessageClick(event);
	}
	
	private void eventMessageClick(GwtEvent<?> event) {		
		
		if (event.getSource()==btnAdminLoad)
		{
			fpAdmin.submit();
		}
		else if (event.getSource()==btnAdminDisplay) 
			showAll();
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
	private void showAll(){	
		
		 if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0){
		landMarksService.getLandMarks(new AsyncCallback<List<LandmarkDTO>>() 
				 {
					 public void onFailure(Throwable caught) 
					 {
				
				     }

					 public void onSuccess(List<LandmarkDTO> result) 
							{
								int rowCount = result.size();
								LatLngBounds bounds = LatLngBounds.newInstance(); 
								for (int row = 0; row < rowCount; row ++) 
								{
									final LandmarkDTO lm=	result.get(row);
									LatLng point = LatLng.newInstance(lm.getLatitude(),lm.getLongitude());
									bounds.extend(point);
									final Marker marker =new Marker(point);
									marker.addMarkerClickHandler(new MarkerClickHandler() {
							   	          public void onClick(MarkerClickEvent event) {
								   	          InfoWindow info = map.getInfoWindow();
								   	          info.open(marker,
								   	              new InfoWindowContent("<tr><td>Category: </td><td>" +lm.getCategory()+
								   	            		  "</td></tr><tr><td>Latitude: </td><td>"+lm.getLatitude()+
								   	            		  "</td></tr><tr><td>Longitude: </td><td>"+lm.getLongitude()+
								   	            		  "</td></tr><tr><td>Landmark Name: </td><td>"+lm.getPlaceName()+
								   	            		  "</td></tr>"));
								   	        }
								   	      });						
                                   // Add a marker
									map.addOverlay(marker);
									
									//map.setCenter(point,15);
									
									// Add an info window to highlight a point of interest
									//map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
									
								}
								map.setCenter(bounds.getCenter());
								map.setZoomLevel(map.getBoundsZoomLevel(bounds)-1);
								datagrid.updateTableData();
							}
				 });
		 }
		else  if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0){
		towerService.getTowers(new AsyncCallback<List<TowerDTO>>() 
				 {
					 public void onFailure(Throwable caught) 
					 {
				
				     }

					 public void onSuccess(List<TowerDTO> result) 
							{
								int rowCount = result.size();
								LatLngBounds bounds = LatLngBounds.newInstance(); 
								for (int row = 0; row < rowCount; row ++) 
								{
									final TowerDTO tw=	result.get(row);	
									final LatLng point = LatLng.newInstance(tw.getLatitude(),tw.getLongitude());
									bounds.extend(point);
									 // Add a marker
									String iconName=
								        "http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/"+tw.getStatus()+".png";
									Icon icon = Icon.newInstance(iconName);
							         icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
							         icon.setIconSize(Size.newInstance(12, 20));
							         icon.setShadowSize(Size.newInstance(22, 20));
							         icon.setIconAnchor(Point.newInstance(6, 20));
							         icon.setInfoWindowAnchor(Point.newInstance(5, 1));
							         MarkerOptions options = MarkerOptions.newInstance();
							         options.setIcon(icon);
									final Marker marker =new Marker(point,options);
									
									marker.addMarkerClickHandler(new MarkerClickHandler() {
							   	          public void onClick(MarkerClickEvent event) {
								   	          InfoWindow info = map.getInfoWindow();
								   	          info.open(marker, createTowerInfoWindowContent(point,tw));
								   	        }
								   	      });						
                                 
									
									map.addOverlay(marker);
									
									//map.setCenter(point,15);
									
									// Add an info window to highlight a point of interest
									//map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
									
								}	
								map.setCenter(bounds.getCenter());
								map.setZoomLevel(map.getBoundsZoomLevel(bounds)-1);
								datagrid.updateTableData();
							}
				 });
		}
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0){
			cscService.getCSCs(new AsyncCallback<List<CSCDTO>>() 
					 {
						 public void onFailure(Throwable caught) 
						 {
					
					     }

						 public void onSuccess(List<CSCDTO> result) 
								{
									int rowCount = result.size();
									LatLngBounds bounds = LatLngBounds.newInstance(); 
									for (int row = 0; row < rowCount; row ++) 
									{
										final CSCDTO csc=	result.get(row);
										LatLng point = LatLng.newInstance(csc.getLatitude(),csc.getLongitude());
										bounds.extend(point);
										Icon icon = Icon.newInstance("http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/csc.png");
								        //Icon icon = Icon.newInstance(
								        //"file://RetailerIcon.png");
								        icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
								        icon.setIconSize(Size.newInstance(12, 20));
								        icon.setShadowSize(Size.newInstance(22, 20));
								        icon.setIconAnchor(Point.newInstance(6, 20));
								        icon.setInfoWindowAnchor(Point.newInstance(5, 1));
								        MarkerOptions options = MarkerOptions.newInstance();
								        options.setIcon(icon);
										final Marker marker =new Marker(point,options);
										marker.addMarkerClickHandler(new MarkerClickHandler() {
								   	          public void onClick(MarkerClickEvent event) {
									   	          InfoWindow info = map.getInfoWindow();
									   	          info.open(marker,
									   	              new InfoWindowContent("<tr><td>Category: </td><td>" +csc.getCategory()+
									   	            		  "</td></tr><tr><td>Latitude: </td><td>"+csc.getLatitude()+
									   	            		  "</td></tr><tr><td>Longitude: </td><td>"+csc.getLongitude()+
									   	            		"</td></tr><tr><td>CSC Name: </td><td>"+csc.getName()+
									   	            		  "</td></tr><tr><td>Tower Name: </td><td>"+csc.getTower_name()+
									   	            		"</td></tr><tr><td>Contact_person: </td><td>"+csc.getContact_person()+
									   	            		"</td></tr><tr><td>Address: </td><td>"+csc.getAddress()+
									   	            		  "</td></tr>"));
									   	        }
									   	      });						
	                                   // Add a marker
										map.addOverlay(marker);
										
										//map.setCenter(point,15);
										
										// Add an info window to highlight a point of interest
										//map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
										
									}
									map.setCenter(bounds.getCenter());
									map.setZoomLevel(map.getBoundsZoomLevel(bounds)-1);
									datagrid.updateTableData();
								}
					 });
			 }
		else if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0){
			retailerService.getRetailers(new AsyncCallback<List<RetailerDTO>>() 
					 {
						 public void onFailure(Throwable caught) 
						 {
					
					     }

						 public void onSuccess(List<RetailerDTO> result) 
								{
									int rowCount = result.size();
									LatLngBounds bounds = LatLngBounds.newInstance(); 
									for (int row = 0; row < rowCount; row ++) 
									{
										final RetailerDTO re=	result.get(row);
										LatLng point = LatLng.newInstance(re.getLatitude(),re.getLongitude());
										bounds.extend(point);
										Icon icon = Icon.newInstance("http://gstcloud.googlecode.com/svn/trunk/GSTCloud/war/images/retailer-icon.png");
								        //Icon icon = Icon.newInstance(
								        //"file://RetailerIcon.png");
								        icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
								        icon.setIconSize(Size.newInstance(12, 20));
								        icon.setShadowSize(Size.newInstance(22, 20));
								        icon.setIconAnchor(Point.newInstance(6, 20));
								        icon.setInfoWindowAnchor(Point.newInstance(5, 1));
								        MarkerOptions options = MarkerOptions.newInstance();
								        options.setIcon(icon);
										final Marker marker =new Marker(point,options);
										marker.addMarkerClickHandler(new MarkerClickHandler() {
								   	          public void onClick(MarkerClickEvent event) {
									   	          InfoWindow info = map.getInfoWindow();
									   	          info.open(marker,
									   	              new InfoWindowContent("<tr><td>Category: </td><td>" +re.getCategory()+
									   	            		  "</td></tr><tr><td>Latitude: </td><td>"+re.getLatitude()+
									   	            		  "</td></tr><tr><td>Longitude: </td><td>"+re.getLongitude()+
									   	            		  "</td></tr><tr><td>Retailer Name: </td><td>"+re.getName()+
									   	            		"</td></tr><tr><td>Address: </td><td>"+re.getAddress()+
									   	            		  "</td></tr>"));
									   	        }
									   	      });						
	                                   // Add a marker
										map.addOverlay(marker);
										
										//map.setCenter(point,15);
										
										// Add an info window to highlight a point of interest
										//map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
										
									}
									map.setCenter(bounds.getCenter());
									map.setZoomLevel(map.getBoundsZoomLevel(bounds)-1);
									datagrid.updateTableData();
								}
					 });
			 }
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
								drawPointsWithinCircle(point,radius);
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
								drawPointsWithinCircle(point,radius);
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
							drawPointsWithinCircle(point,radius);
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
		drawPointsWithinCircle(centerPoint,radius);
	}
	private void showByName(String name){
		 
		final Double radius = new Double(tbAttributeRadius.getText());
		if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0){
		landMarksService.searchLandmarkByName(name,new AsyncCallback<List<LandmarkDTO>>()
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
					    	  //map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getPlaceName()));
					    	  drawPointsWithinCircle(centerPoint,radius);
						}
				    }
				});}
		 else  if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0){
				towerService.searchTowerByName(name,new AsyncCallback<List<TowerDTO>>()
						{
							public void onFailure(Throwable caught) 
							{
							}
						    public void onSuccess(List<TowerDTO> result ) 
						    {						    
								int rowCount = result.size();
								for (int row = 0; row < rowCount; row ++)
								{
									Double lat = result.get(row).getLatitude();
									Double lng = result.get(row).getLongitude();
									LatLng centerPoint = LatLng.newInstance(lat,lng);
//									// Add a marker
						   	          //map.addOverlay(new Marker(centerPoint));
						   	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,radius,60,map);
						   	          //map.setCenter(centerPoint, 10);
									// Add an info window to highlight a point of interest
							    	  
							    	  //map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getName()));
							    	  drawPointsWithinCircle(centerPoint,radius);
								}
						    }
						});
		 }
		 else  if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0){
				cscService.searchCSCByName(name,new AsyncCallback<List<CSCDTO>>()
						{
							public void onFailure(Throwable caught) 
							{
							}
						    public void onSuccess(List<CSCDTO> result ) 
						    {						    
								int rowCount = result.size();
								for (int row = 0; row < rowCount; row ++)
								{
									Double lat = result.get(row).getLatitude();
									Double lng = result.get(row).getLongitude();
									LatLng centerPoint = LatLng.newInstance(lat,lng);
//									// Add a marker
						   	          //map.addOverlay(new Marker(centerPoint));
						   	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,radius,60,map);
						   	          //map.setCenter(centerPoint, 10);
									// Add an info window to highlight a point of interest
							    	  
							    	  //map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getName()));
							    	  drawPointsWithinCircle(centerPoint,radius);
								}
						    }
						});
		 }
		 else  if (selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0){
			 retailerService.searchRetailerByName(name,new AsyncCallback<List<RetailerDTO>>()
						{
							public void onFailure(Throwable caught) 
							{
							}
						    public void onSuccess(List<RetailerDTO> result ) 
						    {						    
								int rowCount = result.size();
								for (int row = 0; row < rowCount; row ++)
								{
									Double lat = result.get(row).getLatitude();
									Double lng = result.get(row).getLongitude();
									LatLng centerPoint = LatLng.newInstance(lat,lng);
//									// Add a marker
						   	          //map.addOverlay(new Marker(centerPoint));
						   	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,radius,60,map);
						   	          //map.setCenter(centerPoint, 10);
									// Add an info window to highlight a point of interest
							    	  
							    	  //map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("This is" + result.get(row).getName()));
							    	  drawPointsWithinCircle(centerPoint,radius);
								}
						    }
						});
		 }
	}

	private void showByGeoCodedAddress(final String address) {
		final Double radius = new Double(tbGeoCodedRadius.getText());
		//final InfoWindow info = map.getInfoWindow();
	    try{
		geocoder.getLatLng(address, new LatLngCallback() {
	      public void onFailure() {
	          Window.alert(address + " not found");
	    	  System.out.println(address+ "not found");
	      }
	      public void onSuccess(LatLng centerPoint) {
	    	  final LatLng localPoint = centerPoint;
	    	  //map.setCenter(centerPoint, 13);
	          //Marker marker = new Marker(centerPoint);
	          //map.addOverlay(marker);
	          //GSTCloudUtils.drawSearchCircleOnScreen(centerPoint,new Double(tbGeoCodedRadius.getText()),60,map);
	          drawPointsWithinCircle(localPoint,radius);
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
		
	    map = new MapWidget(GSTCloudConstants.delhiCityCentroid, 15);
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
	    int maxLatLength =new Double(map.getCenter().getLatitude()).toString().length();
	    if (maxLatLength>6) maxLatLength=6;
	    int maxLngLength =new Double(map.getCenter().getLongitude()).toString().length();
	    if (maxLngLength>6) maxLngLength=6;
	    lblCenterPoint.setText
	    	("MapCenter="+new Double(map.getCenter().getLatitude()).toString().substring(0,maxLatLength)+","+
	    			new Double(map.getCenter().getLongitude()).toString().substring(0,maxLngLength)		);	   
	    map.addMapMoveEndHandler(new MapMoveEndHandler() {
	        public void onMoveEnd(MapMoveEndEvent event) {
	        	int maxLatLength =new Double(map.getCenter().getLatitude()).toString().length();
	    	    if (maxLatLength>6) maxLatLength=6;
	    	    int maxLngLength =new Double(map.getCenter().getLongitude()).toString().length();
	    	    if (maxLngLength>6) maxLngLength=6;
	          lblCenterPoint.setText("MapCenter="+new Double(map.getCenter().getLatitude()).toString().substring(0,maxLatLength)+","+
		    			new Double(map.getCenter().getLongitude()).toString().substring(0,maxLngLength)		);	
	        }
	      });
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
						lm.getLt().setSelectedItem(lm.getLt().getItem(1));
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
	
	@SuppressWarnings("deprecation")
	public void setupTablePanel()
	{
		datagrid= new AdvancedTable();
		datagrid.setAllowRowMark(true);
		datagrid.setFirstColumnVisible(false);
		
		if (lm.getLt().getSelectedItem()==null) 
			selectedLayer=GSTCloudSharedConstants.Landmark;
		else 
			selectedLayer = lm.getLt().getSelectedItem().getText();
		
		if(selectedLayer.trim().compareTo(GSTCloudSharedConstants.Tower.trim())==0)
			datagrid.setTableModelService(towerModelService);
		else if(selectedLayer.trim().compareTo(GSTCloudSharedConstants.Landmark.trim())==0)
			datagrid.setTableModelService(landmarksModelService);
		else if(selectedLayer.trim().compareTo(GSTCloudSharedConstants.CSC.trim())==0)
			datagrid.setTableModelService(cscModelService);
		else if(selectedLayer.trim().compareTo(GSTCloudSharedConstants.Retailer.trim())==0)
			datagrid.setTableModelService(retailerModelService);
		
		datagrid.addRowSelectionListener(new RowSelectionListener() {
			public void onRowSelected(AdvancedTable sender, String rowId) {
				lblMessages.setText("Row " + rowId + " selected.");
			}
		});
		datagrid.setSize("1260px", "100px");
		datagrid.setPageSize(3);
		vpDataGird.clear();
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
