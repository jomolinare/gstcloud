package com.rmsi.lim.gstcloud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.LocalBodiesService;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.States;
import com.rmsi.lim.gstcloud.shared.LocalBodies;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class LocalBodiesServiceImpl extends RemoteServiceServlet implements LocalBodiesService
{
	
	PersistenceManager pm = PMF.get().getPersistenceManager();

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
	
	@Override
	public String loadLocalBody(LocalBodies localBody) 
	{
		try{
			pm.makePersistent(localBody);
		   } finally
		   	{
			}
		
		return null;
	}
	
	public States getStateByName(String stateName)
	{
		
		String querySearchStates = "SELECT FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + stateName +"'" ;
		List<States> statesReturned=(List<States>)pm.newQuery(querySearchStates).execute();
		return statesReturned.get(0);
		
	}
	
	public Districts getDistrictByName(String districtName)
	{
		
		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE districtName =='" + districtName +"'" ;
		List<Districts> districtsReturned=(List<Districts>)pm.newQuery(querySearchDistricts).execute();
		return districtsReturned.get(0);
	}
	
	public LocalBodies getLocalBodyByName(String localBody)
	{
		
		String querySearchLocalBody = "SELECT FROM com.rmsi.lim.gstcloud.shared.LocalBodies WHERE localBodyName =='" + localBody +"'" ;
		List<LocalBodies> LocalBodiesReturned=(List<LocalBodies>)pm.newQuery(querySearchLocalBody).execute();
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
	public List<Districts> getDistrictsByStateName(String stateName)
	{
//		String querySearchStates = "SELECT FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + stateName +"'" ;
//		List<States> statesReturned=(List<States>)pm.newQuery(querySearchStates).execute();
//		
//		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateId ==" + statesReturned.get(0).getStateId();
//		
		String querySearchDistricts ="SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateName =='" + stateName +"'" ;
		Query searchQ = pm.newQuery(querySearchDistricts);
		//searchQ.setSerializeRead(true);
		
		List<Districts> districtsReturned=(List<Districts>)searchQ.execute();
		List<Districts> tempList= new ArrayList<Districts>();
		int rowCount=districtsReturned.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	tempList.add(districtsReturned.get(ctr));
	    }

		return tempList; 
		
	}
	
	public List<LocalBodies> getLocalBodiesByDistrictName(String districtName)
	{
//		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE districtName =='" + districtName +"'" ;
//		List<Districts> districtsReturned=(List<Districts>)pm.newQuery(querySearchDistricts).execute();
//		
//		String querySearchLocalBodies = "SELECT FROM com.rmsi.lim.gstcloud.shared.LocalBodies WHERE districtId ==" + districtsReturned.get(0).getDistrictId();
		String querySearchLocalBodies = "SELECT FROM com.rmsi.lim.gstcloud.shared.LocalBodies WHERE districtName =='" + districtName +"'" ;
		Query searchQ = pm.newQuery(querySearchLocalBodies);
		//searchQ.setSerializeRead(true);
		List<LocalBodies> localBodiesReturned=(List<LocalBodies>)searchQ.execute();
		List<LocalBodies> tempList= new ArrayList<LocalBodies>();
		
		int rowCount=localBodiesReturned.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    { 
	    	tempList.add(localBodiesReturned.get(ctr));
	    }

		return tempList; 
		
	}
	
}

