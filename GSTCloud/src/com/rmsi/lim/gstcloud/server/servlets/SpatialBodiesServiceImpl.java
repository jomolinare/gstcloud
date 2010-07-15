package com.rmsi.lim.gstcloud.server.servlets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.SpatialBodiesService;
import com.rmsi.lim.gstcloud.client.model.District;
import com.rmsi.lim.gstcloud.client.model.LocalBody;
import com.rmsi.lim.gstcloud.client.model.State;
import com.rmsi.lim.gstcloud.server.utilities.PMF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class SpatialBodiesServiceImpl extends RemoteServiceServlet implements SpatialBodiesService
{
	
	PersistenceManager pm = PMF.get().getPersistenceManager();

	public List<State> getStates() 
	{
		String query = "SELECT FROM com.rmsi.lim.gstcloud.client.model.State ";
		List<State> statelist = (List<State>) pm.newQuery(query).execute();
		List<State>  tempList = new ArrayList<State>();
	 	
	    int rowCount=statelist.size();
	    for (int ctr=0;ctr<rowCount;ctr++)
	    {
	        tempList.add(statelist.get(ctr));
	    }
		
	    return tempList;
	}

	@Override
	public String loadDistricts(District district) 
	{
		try{
			pm.makePersistent(district);
		   } finally
		   	{
			}
		
		return null;
	}

	@Override
	public String loadStates(State state) 
	{
		try{
			pm.makePersistent(state);
		   } finally
		   	{
			}
		return null;
	}
	
	@Override
	public String loadLocalBody(LocalBody localBody) 
	{
		try{
			pm.makePersistent(localBody);
		   } finally
		   	{
			}
		
		return null;
	}
	
	public State getStateByName(String stateName)
	{
		
		String querySearchStates = "SELECT FROM com.rmsi.lim.gstcloud.client.model.State WHERE stateName =='" + stateName +"'" ;
		List<State> statesReturned=(List<State>)pm.newQuery(querySearchStates).execute();
		return statesReturned.get(0);
		
	}
	
	public District getDistrictByName(String districtName)
	{
		
		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.client.model.District WHERE districtName =='" + districtName +"'" ;
		List<District> districtsReturned=(List<District>)pm.newQuery(querySearchDistricts).execute();
		return districtsReturned.get(0);
	}
	
	public LocalBody getLocalBodyByName(String localBody)
	{
		
		String querySearchLocalBody = "SELECT FROM com.rmsi.lim.gstcloud.client.model.LocalBody WHERE localBodyName =='" + localBody +"'" ;
		List<LocalBody> LocalBodiesReturned=(List<LocalBody>)pm.newQuery(querySearchLocalBody).execute();
		return LocalBodiesReturned.get(0);
	}
	
//	public List<Districts> getDistrictsByStateId(Long stateId)
//	{
//		
//		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateId =='" + stateId.toString() +"'" ;
//		Query searchQ = pm.newQuery(querySearchDistricts);
//		searchQ.setSerializeRead(true);
//		
//		List<Districts> districtsReturned=(List<Districts>)searchQ.execute();
//		List<Districts> tempList= new ArrayList<Districts>();
//		int rowCount=districtsReturned.size();
//	    for (int ctr=0;ctr<rowCount ;ctr++)
//	    {
//	        
//	    	tempList.add(districtsReturned.get(ctr));
//	    }
//
//		return tempList; 
//		
//		
//	}
	public List<District> getDistrictsByStateName(String stateName)
	{
//		String querySearchStates = "SELECT FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + stateName +"'" ;
//		List<States> statesReturned=(List<States>)pm.newQuery(querySearchStates).execute();
//		
//		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateId ==" + statesReturned.get(0).getStateId();
//		
		String querySearchDistricts ="SELECT FROM com.rmsi.lim.gstcloud.client.model.District WHERE stateName =='" + stateName +"'" ;
		Query searchQ = pm.newQuery(querySearchDistricts);
		//searchQ.setSerializeRead(true);
		
		List<District> districtsReturned=(List<District>)searchQ.execute();
		List<District> tempList= new ArrayList<District>();
		int rowCount=districtsReturned.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	tempList.add(districtsReturned.get(ctr));
	    }

		return tempList; 
		
	}
	
	public List<LocalBody> getLocalBodiesByDistrictName(String districtName)
	{
//		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE districtName =='" + districtName +"'" ;
//		List<Districts> districtsReturned=(List<Districts>)pm.newQuery(querySearchDistricts).execute();
//		
//		String querySearchLocalBodies = "SELECT FROM com.rmsi.lim.gstcloud.shared.LocalBodies WHERE districtId ==" + districtsReturned.get(0).getDistrictId();
		String querySearchLocalBodies = "SELECT FROM com.rmsi.lim.gstcloud.client.model.LocalBody WHERE districtName =='" + districtName +"'" ;
		Query searchQ = pm.newQuery(querySearchLocalBodies);
		//searchQ.setSerializeRead(true);
		List<LocalBody> localBodiesReturned=(List<LocalBody>)searchQ.execute();
		List<LocalBody> tempList= new ArrayList<LocalBody>();
		
		int rowCount=localBodiesReturned.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    { 
	    	tempList.add(localBodiesReturned.get(ctr));
	    }

		return tempList; 
		
	}
	
}

