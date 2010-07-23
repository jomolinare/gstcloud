package com.rmsi.lim.gstcloud.client.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GisCloudService</code>.
 */
public interface GisCloudServiceAsync {
	void pointInPolygon(String lngLat, AsyncCallback<String> callback);
	void polygonInPolygon(String bbox, AsyncCallback<String> callback);	
}
