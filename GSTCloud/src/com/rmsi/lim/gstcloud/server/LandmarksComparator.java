package com.rmsi.lim.gstcloud.server;

import java.util.Comparator;

import com.rmsi.lim.gstcloud.shared.Landmarks;

public class LandmarksComparator implements Comparator<Landmarks> {
	
	private String sortColumn;
	private boolean sortingOrder;
	
	public LandmarksComparator(String sortColumn, boolean sortingOrder) {
		this.sortColumn = sortColumn;
		this.sortingOrder = sortingOrder;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(Landmarks landmarks1, Landmarks landmarks2) {
		Comparable column1 = (Comparable)
			ReflectionUtils.getPropertyValue(
					landmarks1, this.sortColumn);
		Comparable column2 = (Comparable)
			ReflectionUtils.getPropertyValue(
					landmarks2, this.sortColumn);
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