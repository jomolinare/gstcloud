package com.rmsi.lim.gstcloud.client.view;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TreeItem;

public class LayerItem extends TreeItem
{
	private String type;
	
	public  LayerItem()
	{
		
	}
	
	public  LayerItem(CheckBox checkbox)
	{
		super(checkbox);
	}
	
	public LayerItem (CheckBox checkbox,String typeIn)
	{
		
		super(checkbox);
		this.type=typeIn;
	}
	
	public void setType(String typeIn)
	{
		this.type=typeIn;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void shftItemUp(int index)
	{
		
	}
	
	//private CheckBox checkbox;
	
//	public LayerItem(CheckBox checkbox)
//	{
//		super()
//		this.checkbox = checkbox;
//	}

	
}
