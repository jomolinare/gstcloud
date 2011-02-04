/* Sample reference implementation of the TableModelService class.
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


import com.rmsi.lim.gstcloud.client.interfaces.ComplaintTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.TowerTableModelService;
import com.rmsi.lim.gstcloud.client.model.CSCDTO;
import com.rmsi.lim.gstcloud.client.model.ComplaintDTO;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.server.*;
import com.rmsi.lim.gstcloud.server.utilities.ComplaintComparator;
import com.rmsi.lim.gstcloud.server.utilities.TowerComparator;
import com.rmsi.lim.gstcloud.server.utilities.ReflectionUtils;

public class ComplaintTableModelServiceImpl extends RemoteServiceServlet implements
		ComplaintTableModelService {

	private static final long serialVersionUID = 1L;


	//private static final ComplaintDTO Complaints = null;

	
	private TableColumn[] columns = new TableColumn[] {
			new TableColumn("CurrentlySelected","Selected"),
			new TableColumn("Category","Category"),
			new TableColumn("Circle","Circle"),
			new TableColumn("Subtype", "Subtype" ),
			new TableColumn("SubSubtype", "SubSubtype" ),
			new TableColumn("Latitude","Latitude" ),
			new TableColumn("Longitude","Longitude"),
			
			// new TableColumn("BR","BR" )			
			};
	
	public String applySpatialFilter(Double lat, Double lng, Double rad){
		ComplaintServiceImpl fea1 = new ComplaintServiceImpl();
		this.allComplaints=fea1.displayComplaintsWithinDistance(lat, lng, rad);
		this.applyDataFilters(null);
		return "filter applied";
	}

	private List<ComplaintDTO> allComplaints ;

	private List<ComplaintDTO> filteredComplaints;

	public void ComplaintTableModelServiceImpl() 
{
		ComplaintServiceImpl fea1 = new ComplaintServiceImpl();
		this.allComplaints=fea1.getComplaints();
		
		
		this.applyDataFilters(null);
	}

	private void applyDataFilters(DataFilter[] filters) {
		this.filteredComplaints = new ArrayList<ComplaintDTO>();
		if (filters == null) {
			// No filter - append all Landmarks
			for (ComplaintDTO complaint : this.allComplaints) {
				this.filteredComplaints.add(complaint);
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (ComplaintDTO complaint : this.allComplaints) {
				String category = complaint.getCategory();
				if (category == null) {
					category = "";
				}
				String subtype = complaint.getSubtype();
				if (subtype == null) {
					subtype = "";
				}
				
				String subSubtype = complaint.getSubSubtype();
				if (subSubtype == null) {
					subSubtype = "";
				}
				if (category.toUpperCase().contains(keyword) || 
						subtype.toUpperCase().contains(keyword)||
						subSubtype.toUpperCase().contains(keyword)) {
					this.filteredComplaints.add(complaint);
				}
			}
		}
		
	}

	@Override
	public TableColumn[] getColumns() 
	{
		return this.columns;
	}

	@Override
	public Integer getRowsCount(DataFilter[] filters) {
		applyDataFilters(filters);
		Integer count = this.filteredComplaints.size();
		return count;
	}

	@Override
	public String[][] getRows(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		ComplaintDTO[] rowsData = getRowsData(startRow, rowsCount, filters, sortColumn,
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

	private ComplaintDTO[] getRowsData(int startRow, int rowsCount,
			DataFilter[] filters, String sortColumn, boolean sortingOrder) {
		applyDataFilters(filters);
		applySorting(sortColumn, sortingOrder);
		ComplaintDTO[] rows = new ComplaintDTO[rowsCount];
		for (int row = startRow; row < startRow + rowsCount; row++) {
			rows[row - startRow] = this.filteredComplaints.get(row);
		}
		return rows;
	}
/*
	private void applyDataFilters(DataFilter[] filters) {
		this.filteredComplaints = new ArrayList<ComplaintDTO>();
		if (filters == null) {
			// No filter - append all Towers
			for (ComplaintDTO Towers : this.allComplaints) {
				this.filteredComplaints.add(Complaints );
			}
		} else {
			// Simulate data filtering
			String keyword = filters[0].getValue().toUpperCase();
			for (ComplaintDTO Complaints : this.allComplaints) {
				String name = Complaints.getCategory();
				if (name == null) {
					name = "";
				}
				String owner = Complaints.getcoverage();
				if (owner == null) {
					owner = "";
				}
				String category = Complaints.getCategory();
				if (category == null) {
					category = "";
				}
				if (name.toUpperCase().contains(keyword) || 
						owner.toUpperCase().contains(keyword) || category.toUpperCase().contains(keyword)) {
					this.filteredComplaints.add(Complaints);
				}
			}
		}
	}
*/ 
	private void applySorting(String sortColumn, boolean sortingOrder) 
{
		if (sortColumn != null) {
			ComplaintComparator ComplainComparator =
				new ComplaintComparator(sortColumn, sortingOrder);
			Collections.sort(this.filteredComplaints, ComplainComparator);
		}
	}

}
