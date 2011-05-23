package com.rmsi.lim.gstcloud.client;

/*import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rmsi.lim.gstcloud.client.interfaces.DatastoreService;
import com.rmsi.lim.gstcloud.client.interfaces.DatastoreServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudService;
import com.rmsi.lim.gstcloud.client.interfaces.GisCloudServiceAsync;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarkServiceAsync;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;

public class MakePopupPanel {
	
	
	/*private static Class<? extends Object> DTOClass;*/
	/*private final static DatastoreServiceAsync datastoreService = GWT.create(DatastoreService.class);*/
	
	 public static void PopupPanel(final List<String> listColumns,final Double latitude,final Double longitude,MapWidget map,final String layerName,final HashMap hMapForAsyncClass){
		 
		    final DatastoreServiceAsync datastoreService = GWT.create(DatastoreService.class);
		 	final HashMap<String,Object> hMapColumnVal = new HashMap<String,Object>();
			final PopupPanel popUp  = new PopupPanel(false);
			VerticalPanel popUpPanelContents = new VerticalPanel();
			HTML messageHeading = new HTML("Fill details for the layer "+layerName);
			HTML message = new HTML("Click 'cancel' to close the Window");
			HorizontalPanel btnHolder = new HorizontalPanel();
			final String[] arrTextBoxVal = new String[listColumns.size()];
			Grid g = new Grid(listColumns.size(), 2);
			
			
			List<HorizontalPanel> listOfHorizontalPanels = new ArrayList<HorizontalPanel>();
			List<Label> listOfLabels = new ArrayList<Label>();
		 	final TextBox[] tbPopPanel = new TextBox[listColumns.size()];
		 	
			for(int i=0;i<listColumns.size();i++){
				System.out.println(listColumns.get(i));
				listOfHorizontalPanels.add(i, new HorizontalPanel());
				tbPopPanel[i] = new TextBox();
				
			}
			
			messageHeading.setStyleName("demo-popup-messageHeading");
			popUpPanelContents.add(messageHeading);
			
			for(int i=0;i<listColumns.size();i++){
				
				listOfLabels.add(i, new Label(listColumns.get(i)));
				System.out.println(listOfLabels.get(i));
				
				/*(listOfHorizontalPanels.get(i)).add(listOfLabels.get(i));
				listOfHorizontalPanels.get(i).setSpacing(10);*/
				//(listOfHorizontalPanels.get(i)).setCellHorizontalAlignment(listOfLabels.get(i),HasHorizontalAlignment.ALIGN_LEFT);
				/*(listOfHorizontalPanels.get(i)).add(tbPopPanel[i]);
				(listOfHorizontalPanels.get(i)).setCellHorizontalAlignment(tbPopPanel[i],HasHorizontalAlignment.ALIGN_RIGHT);*/
				g.setWidget(i,0, listOfLabels.get(i));
				g.setWidget(i, 1, tbPopPanel[i]);
				System.out.println(listOfLabels.get(i).getText());
				
				if(listOfLabels.get(i).getText().equals("Longitude") ){
					tbPopPanel[i].setValue(longitude.toString());
				}
				if(listOfLabels.get(i).getText().equals("Latitude") ){
					tbPopPanel[i].setValue(latitude.toString());
				}
				
				hMapColumnVal.put(listColumns.get(i),tbPopPanel[i].getValue());
				
				popUpPanelContents.add(listOfHorizontalPanels.get(i));
				popUpPanelContents.add(g);
				//g.setWidget(i, 1, listOfHorizontalPanels.get(i));
			}	
			//popUpPanelContents.add(g);
			
			popUp.setStyleName("demo-popup");
			popUp.setTitle("PopUpPanel");
			message.setStyleName("demo-popup-message");
			
			ClickHandler popUpCloseHandler = new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event){
					popUp.hide();
				}
			};
			Button btnCancel = new Button("Cancel", popUpCloseHandler);
			
			/*ClickHandler popUpSaveHandler = new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event){
					
					
					Object object = null;
					try {
						DTOClass= Class.forName("com.rmsi.lim.gstcloud.client.model."+layerName+"DTO");
						object = DTOClass.newInstance();
						System.out.println(DTOClass.getMethods());
						for(int i=0;i<listColumns.size();i++){
							Method method = DTOClass.getMethod("set"+listColumns.get(i),Object.classDTOClass.getField(listColumns.get(i)).getType());
							
							Object obj = tbPopPanel[i].getValue();
							method.invoke(object,obj );
							
						}
						
						Object tempObj = hMapForAsyncClass.get(layerName);
							
						if(	tempObj instanceof LandmarkServiceAsync ){
							LandmarkServiceAsync objLandmarkServiceAsync = (LandmarkServiceAsync)tempObj;
							objLandmarkServiceAsync.loadStation((LandmarkDTO)object,new AsyncCallback<String>(){

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(String result) {
									
									popUp.hide();
									Window.alert("Data Saved Successfully");
								}
								
							});
						}
						
						
						//DTOClass objDTOClass = new DTOClass();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};*/
			
			
			ClickHandler popUpSaveHandlerGeneric = new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event){
					
					for(int i=0;i<listColumns.size();i++){
						arrTextBoxVal[i] = tbPopPanel[i].getValue();
					}
				
					datastoreService.loadData(layerName,arrTextBoxVal, new AsyncCallback<String>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(String result) {
							
							popUp.hide();
							Window.alert("Data Saved Successfully");
						}
						
					});
					
					
					/*Object tempObj = hMapForAsyncClass.get(layerName);
					
					if(	tempObj instanceof LandmarkServiceAsync ){
						LandmarkServiceAsync objLandmarkServiceAsync = (LandmarkServiceAsync)tempObj;
						objLandmarkServiceAsync.loadStationGeneric(hMapColumnVal,new AsyncCallback<String>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(String result) {
								
								popUp.hide();
								Window.alert("Data Saved Successfully");
							}
							
						});
					}*/
				}
			};
			
			
			Button btnSave= new Button("Save", popUpSaveHandlerGeneric);
			
			btnHolder.add(btnCancel);
			btnHolder.add(btnSave);
			btnHolder.setStyleName("demo-popup-footer");
			
			popUpPanelContents.add(message);
			popUpPanelContents.add(btnHolder);
			popUp.setWidget(popUpPanelContents);
			popUp.center();
			popUp.show();
			
	 }
	
	
}
