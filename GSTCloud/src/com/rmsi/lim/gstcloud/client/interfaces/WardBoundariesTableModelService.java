package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;

@RemoteServiceRelativePath("wardBoundaryTableModel")
public interface WardBoundariesTableModelService {

	public TableColumn[]  getColumns();
	public Integer getRowsCount(DataFilter[] filters);
	public String[][] getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder);
	public String applySpatialFilter(Double lat, Double lng, Double rad);
}
