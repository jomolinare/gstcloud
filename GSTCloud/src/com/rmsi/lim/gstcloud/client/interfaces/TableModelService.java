package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;

@RemoteServiceRelativePath("tableModel")
public interface TableModelService extends RemoteService{

	public TableColumn[]  getColumns();
	public Integer getRowsCount(DataFilter[] filters);
	public String[][] getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder);
	public String applySpatialFilter(Double lat, Double lng, Double rad);
	public String setLayer(/*List<TableColumn> columns,*/String layerName/*,List<String> filterColumns*/);
}
