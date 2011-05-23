/**
 * Sample reference implementation of the TableModelService class.
 * For simplicity it does not use database but shows how to implement
 * data paging, sorting and filtering.
 * 
 * (c) 2007 by Svetlin Nakov - http://www.nakov.com
 * National Academy for Software Development - http://academy.devbg.org 
 * This software is freeware. Use it at your own risk.
 */

package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.rmsi.lim.gstcloud.client.interfaces.CSCTableModelService;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;
import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.server.utilities.CSCComparator;
import com.rmsi.lim.gstcloud.server.utilities.LandmarksComparator;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;

public class CSCTableModelServiceImpl extends RemoteServiceServlet implements
		CSCTableModelService {

	private static final long serialVersionUID = 1L;

	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("CurrentlySelected", "Selected"),
			new TableColumn("Address", "Address"),
			new TableColumn("Category", "Category"),
			new TableColumn("Contact_person", "Contact_person"),			
			new TableColumn("Name", "Name"),			
			new TableColumn("Latitude", "Latitude" ),
			new TableColumn("Longitude", "Longitude"),
			new TableColumn("Tower_name", "Tower_name"),				
			};
	
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		CSCServiceImpl fea1 = new CSCServiceImpl();
		this.allCSCs=fea1.displayCSCsWithinDistance(lat, lng, rad);
		this.applyDataFilters(null);
		return "filter applied";
	}

	private List<CSCDTO> allCSCs ;

	private List<CSCDTO> filteredCSCs;

	public CSCTableModelServiceImpl() 
{
		CSCServiceImpl fea1 = new CSCServiceImpl();
		this.allCSCs=fea1.getCSCs();
		
		
		this.applyDataFilters(null);
	}

	@Override
	public TableColumn[] getColumns() 
	{
		return this.columns;
	}

	@Override
	public Integer getRowsCount(DataFilter[] filters) {
		applyDataFilters(filters);
		Integer count = this.filteredCSCs.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		CSCDTO[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private CSCDTO[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		CSCDTO[] rows = new CSCDTO[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredCSCs.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredCSCs = new ArrayList<CSCDTO>();
		if (filters == null) {
			// No filter - append all Landmarks
			for (CSCDTO csc : this.allCSCs) {
				this.filteredCSCs.add(csc);
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (CSCDTO csc : this.allCSCs) {
				String category = csc.getCategory();
				if (category == null) {
					category = "";
				}
				String name = csc.getName();
				if (name == null) {
					name = "";
				}
				if (category.toUpperCase().contains(keyword) || 
						name.toUpperCase().contains(keyword)) {
					this.filteredCSCs.add(csc);
				}
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			CSCComparator cscComparator =
				new CSCComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredCSCs, cscComparator);
		}
	}

	@Override
	public String setLayer(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
