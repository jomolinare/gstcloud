package com.rmsi.lim.gstcloud.server.servlets;
/**
 * Sample reference implementation of the TableModelService class.
 * For simplicity it does not use database but shows how to implement
 * data paging, sorting and filtering.
 * 
 * (c) 2007 by Svetlin Nakov - http://www.nakov.com
 * This software is freeware. Use it at your own risk.
 */



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import com.rmsi.lim.gstcloud.client.interfaces.TableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelService;
import com.rmsi.lim.gstcloud.client.model.*;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.rmsi.lim.gstcloud.client.view.LayerManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.server.utilities.TowerComparator;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;
import com.rmsi.lim.gstcloud.server.utilities.ValueComparator;
import com.rmsi.lim.gstcloud.server.servlets.*;

public class TableModelServiceImpl extends RemoteServiceServlet implements
		TableModelService {

	private static final long serialVersionUID = 1L;
	private Class<? extends Object> objClass;
	private Class<? extends Object> DTOClass;
	private String[] filterColumns;
	private TableColumn[] columns; /*= new TableColumn[] {
			new TableColumn("CurrentlySelected","Selected"),
			new TableColumn("Category","Category"),
			new TableColumn("Coverage","Coverage"),
			new TableColumn("Height","Height"),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			new TableColumn("Name", "Name"),
			new TableColumn("Owner", "Owner" ),       
			new TableColumn("Status","Status" )			
			};*/
	private LayerServiceImpl objLayerServiceImpl = new LayerServiceImpl();
	private List<Object> allValues;
	
	private List<Object> filteredValues;
	
	@SuppressWarnings("unchecked")
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		
		Method method = null;
		try {
			if(DTOClass.getSimpleName().compareTo("WardBoundariesDTO") != 0){
				
				
				method = objClass.getMethod("display"+DTOClass.getSimpleName().substring(0, DTOClass.getSimpleName().length()-3)+"sWithinDistance",Double.class,Double.class, Double.class);
			}else
				method = objClass.getMethod("display"+DTOClass.getSimpleName().substring(0, DTOClass.getSimpleName().length()-3)+"WithinDistance",Double.class,Double.class, Double.class);
			
			
			Double[] arrObj = new Double[3];
			arrObj[0] = lat;
			arrObj[1] = lng;
			arrObj[2] = rad;
			this.allValues = (List<Object>) method.invoke( objClass.newInstance(),arrObj);
		
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.applyDataFilters(null);
		return "Spatial filter applied";
	}

	
	public TableModelServiceImpl() 
	{
		
	}

	@Override
	public TableColumn[] getColumns() 
	{
		return this.columns;
	}

	@Override
	public Integer getRowsCount(DataFilter[] filters) {
		applyDataFilters(filters);
		Integer count = this.filteredValues.size();
		return count;
	}

	@Override 
	public String[][] getRows(int startRow, int rowsCount,
							   DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		
		Object[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
				sortingOrder);
		int columnsCount = this.columns.length;
		String[][] rows = new String[rowsCount][columnsCount];
		for (int row = 0; row < rowsCount; row++) {
			for (int col = 0; col < columnsCount; col++) {
				String columnName = this.columns[col].getName();
				rows[row][col] = ReflectionUtils.getPropertyStringValue(
						rowsData[row], columnName);
			}
		}
		return rows;
	}

	private Object[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		Object[] rows = new Object[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredValues.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredValues = new ArrayList<Object>();
		if (filters == null) {
			// No filter - append all Towers
			this.filteredValues=this.allValues;
			//for (Object values : this.allValues) {
			//	this.filteredValues.add(values );
			//}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			
			for (Object value : this.allValues) {
				List<String> filteringValues = new ArrayList<String>(); 
				for (int ctr=0;ctr<this.filterColumns.length;ctr++){
			    
					
			     try {
					if(
					    ((String)DTOClass.getMethod("get"+filterColumns[ctr]).invoke(value,(Object[])null))
						 .toUpperCase().contains(keyword.toUpperCase())
					    )
						 this.filteredValues.add(value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    	 ;
			}
				
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			
		
				
			ValueComparator valueComparator = 
				new ValueComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredValues, valueComparator);
		}
	}

	@Override
	public String setLayer(String layerName) {

		try {
			
			String[] arrColumn,arrColumnDisplayNames;
			Method method = null;
			List<TableColumn> listArrColumn = new ArrayList<TableColumn>();
			DTOClass= Class.forName("com.rmsi.lim.gstcloud.client.model."+layerName+"DTO");
			objClass = Class.forName("com.rmsi.lim.gstcloud.server.servlets."+layerName+"ServiceImpl");
			arrColumn = this.objLayerServiceImpl.getColumns(layerName);
			arrColumnDisplayNames = this.objLayerServiceImpl.getColumnDisplayNames(layerName);
			for(int i=0;i<arrColumn.length;i+=1){
				listArrColumn.add(new TableColumn(arrColumn[i],arrColumnDisplayNames[i]));
			}
		 	System.out.println("List of Columns :"+ arrColumn.toString());
		 	this.columns= listArrColumn.toArray(new TableColumn[1]);
	        //this.columns= columns.toArray(new TableColumn[1]);
			//Iterator<String> itrFilterColumns = filterColumns.iterator();
			//this.filterColumns=filterColumns.toArray(new String[1]);
		 	//System.out.println("Filter Colum is "+this.filterColumns[2]);
			this.filterColumns = this.objLayerServiceImpl.getFilterColumns(layerName);
			//System.out.println("Filter Colum is "+this.filterColumns[2]);
			if (layerName.compareTo("WardBoundaries") != 0){
				method = objClass.getMethod("get"+layerName+"s");	
			}else
				method = objClass.getMethod("get"+layerName);	
			
			this.allValues = (List<Object>) method.invoke(objClass.newInstance(), (Object[])null);
		} catch (ClassNotFoundException e) {
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
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//this.allValues=fea1.getTowers();
		
		this.applyDataFilters(null);
		
		return "Initialization complete for layer:"+layerName;
	}

}

