package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.WardBoundariesTableModelService;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;
import com.rmsi.lim.gstcloud.server.utilities.TowerComparator;

public class WardBoundariesTableModelServiceImpl extends RemoteServiceServlet implements
WardBoundariesTableModelService {

	private static final long serialVersionUID = 1L;

	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("Category","Category"),
			new TableColumn("PlaceName","PlaceName"),
			new TableColumn("PCord","PCord"),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			new TableColumn("CityName","CityName"),
			new TableColumn("StreetName", "StreetName"),
			new TableColumn("WardName", "WardName" ),
			new TableColumn("WardNum","WardNum" ),
			new TableColumn("TotalPop","TotalPop" )
			};
	
	private List<WardBoundariesDTO> allWardBoundaries ;
	
	private List<WardBoundariesDTO> filteredWardBoundaries;
	
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		WardBoundariesServiceImpl fea1 = new WardBoundariesServiceImpl();
		this.allWardBoundaries = fea1.displayWardBoundariesWithinDistance(lat, lng, rad);
		this.applyDataFilters(null);
		return "filter applied";
	}

	public WardBoundariesTableModelServiceImpl() 
{
		WardBoundariesServiceImpl fea1 = new WardBoundariesServiceImpl();
		this.allWardBoundaries=fea1.getWardBoundaries();	
		
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
		Integer count = this.filteredWardBoundaries.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		WardBoundariesDTO[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private WardBoundariesDTO[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		WardBoundariesDTO[] rows = new WardBoundariesDTO[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredWardBoundaries.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredWardBoundaries = new ArrayList<WardBoundariesDTO>();
		if (filters == null) {
			// No filter - append all Towers
			for (WardBoundariesDTO Towers : this.allWardBoundaries) {
				this.filteredWardBoundaries.add(Towers );
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (WardBoundariesDTO wardBoundary : this.allWardBoundaries) {
				String name = wardBoundary.getPlaceName();
				if (name == null) {
					name = "";
				}
				String category = wardBoundary.getCategory();
				if (category == null) {
					category = "";
				}
				if (name.toUpperCase().contains(keyword) || 
						 category.toUpperCase().contains(keyword)) {
					this.filteredWardBoundaries.add(wardBoundary);
				}
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
	{
		/*if (sortColumn != null) {
			TowerComparator TowersComparator =
				new TowerComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredWardBoundaries, TowersComparator);
		}*/
	}
}
