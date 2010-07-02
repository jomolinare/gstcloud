/**
 * Sample reference implementation of the TableModelService class.
 * For simplicity it does not use database but shows how to implement
 * data paging, sorting and filtering.
 * 
 * (c) 2007 by Svetlin Nakov - http://www.nakov.com
 * National Academy for Software Development - http://academy.devbg.org 
 * This software is freeware. Use it at your own risk.
 */

package com.rmsi.lim.gstcloud.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rmsi.lim.gstcloud.client.LandmarksService;
import com.rmsi.lim.gstcloud.client.LandmarksServiceAsync;
import com.rmsi.lim.gstcloud.client.LandmarksTableModelService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.shared.DataFilter;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.TableColumn;

public class LandmarksTableModelServiceImpl extends RemoteServiceServlet implements
		LandmarksTableModelService {

	private static final long serialVersionUID = 1L;

	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("Category", "Category" ),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			new TableColumn("PlaceName","PlaceName")
			};
	


	private List<Landmarks> allLandmarks ;

	private List<Landmarks> filteredLandmarks;

	public LandmarksTableModelServiceImpl() 
{
		LandmarksServiceImpl fea1 = new LandmarksServiceImpl();
		this.allLandmarks=fea1.displayStation();
		
		
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
		Integer count = this.filteredLandmarks.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		Landmarks[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private Landmarks[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		Landmarks[] rows = new Landmarks[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredLandmarks.get(row);
		}
		return rows;
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredLandmarks = new ArrayList<Landmarks>();
		if (filters == null) {
			// No filter - append all Landmarks
			for (Landmarks landmarks : this.allLandmarks) {
				this.filteredLandmarks.add(landmarks );
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (Landmarks landmarks : this.allLandmarks) {
				String Category = landmarks.getCategory();
				if (Category == null) {
					Category = "";
				}
				String PlaceName = landmarks.getPlaceName();
				if (PlaceName == null) {
					PlaceName = "";
				}
				if (Category.toUpperCase().contains(keyword) || 
						PlaceName.toUpperCase().contains(keyword)) {
					this.filteredLandmarks.add(landmarks);
				}
			}
		}
	}

	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			LandmarksComparator landmarksComparator =
				new LandmarksComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredLandmarks, landmarksComparator);
		}
	}

}
