package com.rmsi.lim.gstcloud.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rmsi.lim.gstcloud.client.GSTCloudUI;
import com.rmsi.lim.gstcloud.client.interfaces.LayerService;
import com.rmsi.lim.gstcloud.client.interfaces.LayerServiceAsync;
import com.rmsi.lim.gstcloud.client.model.Layer;

public class LayerManager extends Composite implements ClickHandler{

	private LayerTree lt = new LayerTree();
	private Button btnMoveLyrUp = new Button("Shift Up");
	private Button btnMoveLyrDown = new Button("Shift Down");
	private Button btnRefresh = new Button("Refresh");
	private final LayerServiceAsync layerService = GWT
	.create(LayerService.class);
	private GSTCloudUI parentUI;	
	public LayerManager(GSTCloudUI parent)
	{
		this.parentUI=parent;
		VerticalPanel vp= new VerticalPanel();		
		vp.add(lt);
		HorizontalPanel hp = new HorizontalPanel();
		btnMoveLyrUp.setStylePrimaryName("Button");
		btnMoveLyrDown.setStylePrimaryName("Button");
		btnRefresh.setStylePrimaryName("Button");
		hp.add(btnMoveLyrUp);
		hp.add(btnMoveLyrDown);
		hp.add(btnRefresh);
		btnMoveLyrUp.addClickHandler(this);
		btnMoveLyrDown.addClickHandler(this);
		btnRefresh.addClickHandler(this);
		vp.add(hp);
		initWidget(vp);
	}
	
	public LayerTree getLt()
	{		
		return this.lt;
	}
	
	public void addLayer(Layer layerIn)
	{
		lt.addLayer(layerIn);
	}
	
	
	public void onClick(ClickEvent event) 
	{
		if(event.getSource() == btnMoveLyrUp)
		{
			System.out.println("1");
			for(int i=0;i<lt.getItemCount();i++)
			{				
				if(lt.getItem(i).isSelected()== true)
					lt.dispLyrAftrShft(i);	
			}
		}
		else if(event.getSource() == btnRefresh)
		{
			System.out.println(lt.getSelectedItem().getText());
			parentUI.setupTablePanel();
			parentUI.lblsearchType.setText(lt.getSelectedItem().getText()+" Search");
			layerService.refreshMap(lt.getSelectedItem().getText(),new AsyncCallback<String>(){
				public void onFailure(Throwable caught) 
				{
					System.out.println("failure");	
				}

				public void onSuccess(String result) 
				{
					System.out.println("123");
					System.out.println(result);
				} 
			});
		
	}
	}

	}
