package com.rmsi.lim.gstcloud.shared;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
/**
 * This is a JDO class for storing the layers and is Serializable.
 * @author Shrikant.Agarwal
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
public class Layer implements IsSerializable
{
	 @Persistent
	 @PrimaryKey
	 private String name;
	 
	 @Persistent
	 private String type;
	 
	 public Layer()
	 {
		 
	 }
	
	 public Layer(String name,String type)
	 {
		 this.name = name;
		 this.type = type;
	 }
	 
	 public String getName() 
	    {
	        return name;
	    }
	 
	 public String getType() 
	    {
	        return type;
	    }
	 
	 public void setName(String name) 
	 	{
	        this.name = name;
	    }
	 public void setType(String type) 
	 	{
	        this.type = type;
	    }
}
