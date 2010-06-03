package com.rmsi.lim.gstcloud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.LocalBodiesService;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.States;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class LocalBodiesServiceImpl extends RemoteServiceServlet implements LocalBodiesService
{
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
//	String query = "";//select districtName from org.emcode.samples.giscloud.shared.States innerjoin org.emcode.samples.giscloud.shared.Districts on tbstate.sid = tbldistrict.s_id where tbstate.sid = '1'";
	@Override
	public List<Districts> getDistricts(String stateName) 
	{
		String query = "SELECT districtName FROM com.rmsi.lim.gstcloud.shared.Districts,com.rmsi.lim.gstcloud.shared.States" +
				"WHERE States.statesId = Districts.states_Id ";
		List<Districts> districtlist = (List<Districts>) pm.newQuery(query).execute();
		List<Districts>  tempList = new ArrayList<Districts>();
		
		int rowCount=districtlist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        tempList.add(districtlist.get(ctr));
	    }
		
	    return tempList;
	}
	
	
	public List<States> getStates() 
	{
		String query = "SELECT FROM com.rmsi.lim.gstcloud.shared.States ";
		List<States> statelist = (List<States>) pm.newQuery(query).execute();
		List<States>  tempList = new ArrayList<States>();
	 	
	    int rowCount=statelist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        tempList.add(statelist.get(ctr));
	    }
		
	    return tempList;
	}

	@Override
	public String loadDistricts(Districts district) 
	{
		try{
			pm.makePersistent(district);
		   } finally
		   	{
			}
		
		return null;
	}

	@Override
	public String loadStates(States state) 
	{
		try{
			pm.makePersistent(state);
		   } finally
		   	{
			}
		return null;
	}
	
	public HashMap displayState(String text)
	{
		HashMap hashMap = new HashMap();
		String querySearchLat = "SELECT latitude FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + text +"'" ;
		Long lat = (Long) pm.newQuery(querySearchLat).execute();
		hashMap.put("Lat", lat);
		
		String querySearchLng = "SELECT longitude FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + text +"'" ;
		Long lng = (Long) pm.newQuery(querySearchLng).execute();
		hashMap.put("Lon", lng);

		return hashMap; 
	}
	
	public HashMap displayDistrict(String text)
	{
		HashMap hashMap = new HashMap();
		String querySearchLat = "SELECT latitude FROM com.rmsi.lim.gstcloud.shared.States WHERE districtName =='" + text +"'" ;
		Long lat = (Long) pm.newQuery(querySearchLat).execute();
		hashMap.put("Lat", lat);
		
		String querySearchLng = "SELECT longitude FROM com.rmsi.lim.gstcloud.shared.States WHERE districtName =='" + text +"'" ;
		Long lng = (Long) pm.newQuery(querySearchLng).execute();
		hashMap.put("Lon", lng);

		return hashMap; 
	}
}

