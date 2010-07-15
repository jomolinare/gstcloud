package com.rmsi.lim.gstcloud.server.utilities;

import java.util.Comparator;

import com.rmsi.lim.gstcloud.client.model.LandmarkDTO;


public class LandmarksComparator implements Comparator<LandmarkDTO> {
	
	private String sortColumn;
	private boolean sortingOrder;
	
	public LandmarksComparator(String sortColumn, boolean sortingOrder) {
		this.sortColumn = sortColumn;
		this.sortingOrder = sortingOrder;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(LandmarkDTO landmarks1, LandmarkDTO landmarks2) {
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