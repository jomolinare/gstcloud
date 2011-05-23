/**
 * 
 */
package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;



import com.rmsi.lim.gstcloud.client.interfaces.PointFeatureService;
import com.rmsi.lim.gstcloud.client.model.TowerDTO;
import com.rmsi.lim.gstcloud.server.model.Tower;
import com.rmsi.lim.gstcloud.server.utilities.PMF;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * @author Shrikant.Agarwal
 *
 */
public class PointFeatureServiceImpl extends RemoteServiceServlet implements
		PointFeatureService {

	
	
	/* (non-Javadoc)
	 * @see com.rmsi.lim.gstcloud.client.interfaces.PointFeatureService#searchFeatureByDistance(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public List<Object> searchFeatureByDistance(String layerName,Double latitude,
			Double longitude, Double distance) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rmsi.lim.gstcloud.client.interfaces.PointFeatureService#searchFeatureByName(java.lang.String)
	 */
	@Override
	public List<Object> searchFeatureByName(String layerName,String text) {
		//String querySearch = "SELECT FROM com.rmsi.cloudGIS.jdo.shared.Towers WHERE placeName.startsWith(" + text +")" ;
		String querySearch = "SELECT FROM com.rmsi.lim.gstcloud.server.model."+layerName+" WHERE name =='" + text +"'" ;
	
		/*List<Tower> Towerlist = (List<Tower>) pm.newQuery(querySearch).execute();
//		List<Towers> Towerlist = (List<Towers>) pm.newQuery(Towers.class, "this.placename =="+ "'" + text +"'").execute();
		List<TowerDTO> tempList= new ArrayList<TowerDTO>();
		int rowCount=Towerlist.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	 Tower temp =Towerlist.get(ctr);
		    tempList.add(new TowerDTO(temp.getCategory(),temp.getName(),temp.getOwner(),temp.getStatus(),temp.getCoverage(),temp.getHeight(),temp.getLatitude(),temp.getLongitude(),true));
	    }*/

		return (List<Object>) pm.newQuery(querySearch).execute(); 
	}

	/* (non-Javadoc)
	 * @see com.rmsi.lim.gstcloud.client.interfaces.PointFeatureService#storePointFeature(java.lang.String, java.lang.Double, java.lang.Double, java.lang.String[])
	 */
	@Override
	public String storePointFeature(String layerName, Double latitude,
			Double longitude, String[] attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rmsi.lim.gstcloud.client.interfaces.FeatureService#getAllFeatures()
	 */
	@Override
	public List<Object> getAllFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

}
