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


import com.rmsi.lim.gstcloud.client.interfaces.RetailerTableModelService;
import com.rmsi.lim.gstcloud.client.model.RetailerDTO;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.server.utilities.RetailerComparator;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;

public class RetailerTableModelServiceImpl extends RemoteServiceServlet implements
		RetailerTableModelService {

	private static final long serialVersionUID = 1L;

	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("CurrentlySelected","Selected"),
			new TableColumn("Address", "Address"),
			new TableColumn("Category","Category"),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			new TableColumn("Name", "Name"),		
			};
	
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		RetailerServiceImpl fea1 = new RetailerServiceImpl();
		this.allRetailers=fea1.displayRetailersWithinDistance(lat, lng, rad);
		this.applyDataFilters(null);
		return "filter applied";
	}

	private List<RetailerDTO> allRetailers ;

	private List<RetailerDTO> filteredRetailers;

	public RetailerTableModelServiceImpl() 
{
		RetailerServiceImpl fea1 = new RetailerServiceImpl();
		this.allRetailers=fea1.getRetailers();
		
		
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
		Integer count = this.filteredRetailers.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		RetailerDTO[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private RetailerDTO[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		RetailerDTO[] rows = new RetailerDTO[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredRetailers.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredRetailers = new ArrayList<RetailerDTO>();
		if (filters == null) {
			// No filter - append all Retailers
			for (RetailerDTO Retailers : this.allRetailers) {
				this.filteredRetailers.add(Retailers );
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (RetailerDTO Retailers : this.allRetailers) {
				String name = Retailers.getName();
				if (name == null) {
					name = "";
				}
				String category = Retailers.getCategory();
				if (category == null) {
					category = "";
				}
				if (name.toUpperCase().contains(keyword) || category.toUpperCase().contains(keyword)) {
					this.filteredRetailers.add(Retailers);
				}
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			RetailerComparator RetailersComparator =
				new RetailerComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredRetailers, RetailersComparator);
		}
	}

	@Override
	public String setLayer(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
