package com.rmsi.lim.gstcloud.client;

import java.util.EventListener;

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