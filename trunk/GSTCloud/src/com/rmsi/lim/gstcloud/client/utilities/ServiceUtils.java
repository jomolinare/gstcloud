package com.rmsi.lim.gstcloud.client.utilities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelService;
import com.rmsi.lim.gstcloud.client.interfaces.LandmarksTableModelServiceAsync;

public class ServiceUtils {
	
	private static LandmarksTableModelServiceAsync tableModelServiceAsync;
	
	public static LandmarksTableModelServiceAsync getTableModelServiceAsync(){
		if (tableModelServiceAsync == null) {
			tableModelServiceAsync = (LandmarksTableModelServiceAsync) 
				GWT.create(LandmarksTableModelService.class);
			ServiceDefTarget target = (
					ServiceDefTarget) tableModelServiceAsync;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + 
				"TableModel");
		}
		return tableModelServiceAsync;
	}

}
