package com.rmsi.lim.gstcloud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rmsi.lim.gstcloud.shared.DataFilter;
import com.rmsi.lim.gstcloud.shared.TableColumn;

public interface LandmarksTableModelServiceAsync {
	public void getColumns(AsyncCallback<TableColumn[]> callback);
	public void getRowsCount(DataFilter[] filters, AsyncCallback<Integer> callback);
	public void getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder, AsyncCallback<String[][]> callback);
}
