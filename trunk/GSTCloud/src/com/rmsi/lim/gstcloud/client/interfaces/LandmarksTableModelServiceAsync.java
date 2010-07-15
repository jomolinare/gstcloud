package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.client.model.TableColumn;
import com.rmsi.lim.gstcloud.client.utilities.DataFilter;

public interface LandmarksTableModelServiceAsync {
	public void getColumns(AsyncCallback<TableColumn[]> callback);
	public void getRowsCount(DataFilter[] filters, AsyncCallback<Integer> callback);
	public void getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder, AsyncCallback<String[][]> callback);
}
