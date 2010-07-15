package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.EventListener;

import com.rmsi.lim.gstcloud.client.view.AdvancedTable;

public interface RowSelectionListener extends EventListener {

	/**
	 * Fired when the currently selected row in the table changes.
	 * 
	 * @param sender
	 *     the AdvancedTable widget sending the event
	 * @param row
	 *     the row identifier (primary key) of the row being selected
	 */
	void onRowSelected(AdvancedTable sender, String rowId);
}
