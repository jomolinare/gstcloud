package com.rmsi.lim.gstcloud.server.utilities;

import java.util.Comparator;

import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.client.model.WardBoundariesDTO;

public class WardBoundariesComparator implements Comparator<WardBoundariesDTO> {
	
	private String sortColumn;
	private boolean sortingOrder;
	
	public WardBoundariesComparator(String sortColumn, boolean sortingOrder) {
		this.sortColumn = sortColumn;
		this.sortingOrder = sortingOrder;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(WardBoundariesDTO wardBoundary1, WardBoundariesDTO wardBoundary2) {
		Comparable column1 = (Comparable)
			ReflectionUtils.getPropertyValue(
					wardBoundary1, this.sortColumn);
		Comparable column2 = (Comparable)
			ReflectionUtils.getPropertyValue(
					wardBoundary2, this.sortColumn);
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
