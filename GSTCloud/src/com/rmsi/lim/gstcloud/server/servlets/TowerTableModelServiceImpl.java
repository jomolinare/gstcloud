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


import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelService;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.server.utilities.TowerComparator;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;

public class TowerTableModelServiceImpl extends RemoteServiceServlet implements
		TowerTableModelService {

	private static final long serialVersionUID = 1L;

	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("CurrentlySelected","Selected"),
			new TableColumn("Category","Category"),
			new TableColumn("Coverage","Coverage"),
			new TableColumn("Height","Height"),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			new TableColumn("Name", "Name"),
			new TableColumn("Owner", "Owner" ),       
			new TableColumn("Status","Status" )			
			};
	
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		TowerServiceImpl fea1 = new TowerServiceImpl();
		this.allTowers=fea1.displayTowersWithinDistance(lat, lng, rad);
		this.applyDataFilters(null);
		return "filter applied";
	}

	private List<TowerDTO> allTowers ;

	private List<TowerDTO> filteredTowers;

	public TowerTableModelServiceImpl() 
	{
		TowerServiceImpl fea1 = new TowerServiceImpl();
		this.allTowers=fea1.getTowers();
		
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
		Integer count = this.filteredTowers.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		TowerDTO[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private TowerDTO[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		TowerDTO[] rows = new TowerDTO[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredTowers.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredTowers = new ArrayList<TowerDTO>();
		if (filters == null) {
			// No filter - append all Towers
			for (TowerDTO Towers : this.allTowers) {
				this.filteredTowers.add(Towers );
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (TowerDTO Towers : this.allTowers) {
				String name = Towers.getName();
				if (name == null) {
					name = "";
				}
				String owner = Towers.getOwner();
				if (owner == null) {
					owner = "";
				}
				String category = Towers.getCategory();
				if (category == null) {
					category = "";
				}
				if (name.toUpperCase().contains(keyword) || 
						owner.toUpperCase().contains(keyword) || category.toUpperCase().contains(keyword)) {
					this.filteredTowers.add(Towers);
				}
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			TowerComparator TowersComparator =
				new TowerComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredTowers, TowersComparator);
		}
	}

	@Override
	public String setLayer(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
