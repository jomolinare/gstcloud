package com.rmsi.lim.gstcloud.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataFilter implements IsSerializable {

	private String column;
	private String value;
	
	public DataFilter()
	{
	}

	public DataFilter(String column, String value) {
		this.column = column;
		this.value = value;
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
