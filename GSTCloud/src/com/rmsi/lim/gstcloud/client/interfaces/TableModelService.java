package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.RemoteService;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;

public interface TableModelService extends RemoteService{

	public TableColumn[]  getColumns();
	public Integer getRowsCount(DataFilter[] filters);
	public String[][] getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder);
	public String applySpatialFilter(Double lat, Double lng, Double rad);
}
