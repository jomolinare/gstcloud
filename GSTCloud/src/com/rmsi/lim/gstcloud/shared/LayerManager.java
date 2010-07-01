package com.rmsi.lim.gstcloud.shared;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LayerManager extends Composite implements ClickHandler{

	private LayerTree lt = new LayerTree();
	private Button btnMoveLyrUp = new Button("Shift Up");
	private Button btnMoveLyrDown = new Button("Shift Down");
	public LayerManager()
	{
		VerticalPanel vp= new VerticalPanel();
		
		vp.add(lt);
		HorizontalPanel hp = new HorizontalPanel();
		btnMoveLyrUp.setStylePrimaryName("Button");
		btnMoveLyrDown.setStylePrimaryName("Button");
		hp.add(btnMoveLyrUp);
		hp.add(btnMoveLyrDown);
		vp.add(hp);
		initWidget(vp);
	}
	public void addLayer(Layer layerIn)
	{
		lt.addLayer(layerIn);
	}
	@Override
	public void onClick(ClickEvent event) 
	{
		if(event.getSource() == btnMoveLyrUp)
		{
			for(int i=0;i<lt.getItemCount();i++)
			{				
				if(lt.getItem(i).isSelected()== true)
					lt.dispLyrAftrShft(i);	
			}
		}
	}
}
