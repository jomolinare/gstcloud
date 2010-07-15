package com.rmsi.lim.gstcloud.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.rmsi.lim.gstcloud.client.model.Layer;

/**
 * The IsSerializable interface is both important and problematic. It's important because it gives the GWT 
 * compiler better information on what classes need to support serialization. It's problematic because it introduces 
 * a GWT-specific dependency into model classes.
 * @author Shrikant.Agarwal
 */

public class LayerTree extends Tree implements IsSerializable
{	
	private List<LayerItem> layersList = new ArrayList<LayerItem>();
	
	public LayerTree()
	{
		
	}
//	public LayerTree(Layer layer)
//	{	
//		String checkboxLabel = layer.getName();
//		checkBox.setText(checkboxLabel);-=
//		
//		initWidget();
//	}
	public void addLayer(Layer layer)
	{
		
		System.out.println("Layer Object ="+layer);
		String checkboxLabel = layer.getName();
		CheckBox checkbox = new CheckBox(checkboxLabel);
		LayerItem item = new LayerItem(checkbox,layer.getType());
		layersList.add(item);
		this.addItem(item);
		
	}
	
	public void dispLyrAftrShft(int index)
	{
		LayerItem itm2bShft = layersList.get(index);
		LayerItem shftItemUp =layersList.get(--index); 
		
	}
	
	//	private List<LayerItem> itemList;
//
//	public void addLayer(LayerItem item)
//	{
//		this.addItem(item);
//		this.itemList.add(item);
//		
//	}
//	
//	public List<LayerItem> getItem()
//	{
//		return itemList;
//	}
//	
//	public void removeLayer(LayerItem item)
//	{
//		this.removeItem(item);
//	}
}
