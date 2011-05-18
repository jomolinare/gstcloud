/**
 * 
 */
package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

/**
 * @author Shrikant.Agarwal
 *
 */
public interface PointFeatureService extends FeatureService {

	public String storePointFeature(String layerName,Double latitude,Double longitude,String[] attributes);
	
	public List<Object> searchFeatureByName(String layerName,String text);
	public List<Object> searchFeatureByDistance(String layerName,Double latitude,Double longitude,Double distance);
}
