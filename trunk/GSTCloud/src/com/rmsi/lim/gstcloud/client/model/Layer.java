package com.rmsi.lim.gstcloud.client.model;

import javax.jdo.annotations.Columns;
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
	 private String layerName;
	 
	 @Persistent
	 private String type;
	 
	 @Persistent
	 private String[] filterColumnNames;
	 @Persistent
	 private String[] columnNames;
	 
	 @Persistent  String[] columnDisplayNames;
	 
	 @Persistent
	 private String[] columnTypes;
	 
	 @Persistent
	 private String[] filterColumnTypes;
	 
	 public Layer()
	 {
		 
	 }
	 
	 public Layer(String name,String type)
	 {
		 this.name = name;
		 this.type = type;
		 this.columnNames = null;
		 this.filterColumnNames =null;
		 this.columnDisplayNames=null;
	 }
	
	 public Layer(String name,String layerName,String type,
			      String[] columnNames,String[] columnDisplayNames, 
			      String[] columnTypes,String[] filterColumnNames,
			      String[] filterColumnTypes)
	 {
		 this.name = name;
		 this.layerName = layerName;
		 this.type = type;
		 this.columnNames = columnNames;
		 this.filterColumnNames = filterColumnNames;
		 this.columnDisplayNames = columnDisplayNames;
		 this.columnTypes = columnTypes;
		 this.filterColumnTypes = filterColumnTypes;
	 }
	 
	 public String getName() 
	    {
	        return name;
	    }
	 
	 public String getLayerName() 
	    {
	        return layerName;
	    }
	 
	 public String getType() 
	    {
	        return type;
	    }
	 
	 public void setName(String name) 
	 	{
	        this.name = name;
	    }
	 
	 public void setLayerName(String layerName) 
	 	{
	        this.layerName = layerName;
	    }
	 
	 public void setType(String type) 
	 	{
	        this.type = type;
	    }
	 
	
	 

	 
	 public String[] getColumnDisplayNames() 
	    {
	        return columnDisplayNames;
	    }
	 public void setColumnDisplayNames(String[] columnDisplayNames) 
	 	{
	        this.columnDisplayNames = columnDisplayNames;
	    }
	 
	 
	
	 public String[] getColumnNames() 
	    {
	        return columnNames;
	    }
	 public void setColumnNames(String[] columnNames) 
	 	{
	        this.columnNames = columnNames;
	    }
	 
	 
	 public String[] getFilterColumnNames() 
	    {
	        return filterColumnNames;
	    }
	 public void setFilterColumnNames(String[] filterColumnNames) 
	 	{
	        this.filterColumnNames = filterColumnNames;
	    }
	 
	 
	 public String[] getColumnTypes() 
	    {
	        return columnTypes;
	    }
	 public void setColumnTypes(String[] columnTypes) 
	 	{
	        this.columnTypes = columnTypes;
	    }
	 
	 public String[] getFilterColumnTypes() 
	    {
	        return filterColumnTypes;
	    }
	 public void setFilterColumntypes(String[] filterColumnTypes) 
	 	{
	        this.filterColumnTypes = filterColumnTypes;
	    }
	 
}
