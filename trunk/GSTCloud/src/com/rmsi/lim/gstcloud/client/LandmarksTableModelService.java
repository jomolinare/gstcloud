package com.rmsi.lim.gstcloud.client;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rmsi.lim.gstcloud.shared.DataFilter;
import com.rmsi.lim.gstcloud.shared.TableColumn;

@RemoteServiceRelativePath("TableModel")
public interface LandmarksTableModelService extends RemoteService {
	public TableColumn[]  getColumns();
	public Integer getRowsCount(DataFilter[] filters);
	public String[][] getRows(int startRow, int rowsCount,
		DataFilter[] filters, String sortColumn, boolean sortOrder);
}
