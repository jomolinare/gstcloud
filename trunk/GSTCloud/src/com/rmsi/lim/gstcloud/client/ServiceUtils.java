package com.rmsi.lim.gstcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

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
