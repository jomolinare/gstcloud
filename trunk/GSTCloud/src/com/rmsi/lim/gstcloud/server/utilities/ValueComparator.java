package com.rmsi.lim.gstcloud.server.utilities;

import java.util.Comparator;

import com.rmsi.lim.gstcloud.client.model.TowerDTO;


public class ValueComparator implements Comparator<Object> {
	
	private String sortColumn;
	private boolean sortingOrder;
	
	public ValueComparator(String sortColumn, boolean sortingOrder) {
		this.sortColumn = sortColumn;
		this.sortingOrder = sortingOrder;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(Object value1, Object value2) {
		Comparable column1 = (Comparable)
			ReflectionUtils.getPropertyValue(
					value1, this.sortColumn);
		Comparable column2 = (Comparable)
			ReflectionUtils.getPropertyValue(
					value2, this.sortColumn);
		int compareResult = -1;
		if (column1 != null) {
			if (column2 != null) {
				compareResult = column1.compareTo(column2);
			} else {
				compareResult = 1;
			}
		}		 
		if (!this.sortingOrder) {
			compareResult = -1 * compareResult;
		}
		return compareResult;
	}
	
}
