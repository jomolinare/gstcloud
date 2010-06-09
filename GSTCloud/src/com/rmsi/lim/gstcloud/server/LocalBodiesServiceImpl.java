package com.rmsi.lim.gstcloud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.LocalBodiesService;
import com.rmsi.lim.gstcloud.shared.Districts;
import com.rmsi.lim.gstcloud.shared.Landmarks;
import com.rmsi.lim.gstcloud.shared.States;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	
	public List<Districts> getDistrictsByStateId(Long stateId)
	{
		
		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateId =='" + stateId.toString() +"'" ;
		Query searchQ = pm.newQuery(querySearchDistricts);
		searchQ.setSerializeRead(true);
		
		List<Districts> districtsReturned=(List<Districts>)searchQ.execute();
		List<Districts> tempList= new ArrayList<Districts>();
		int rowCount=districtsReturned.size();
	    for (int ctr=0;ctr<rowCount ;ctr++)
	    {
	        
	    	tempList.add(districtsReturned.get(ctr));
	    }

		return tempList; 
		
		
	}
	public List<Districts> getDistrictsByStateName(String stateName)
	{
		String querySearchStates = "SELECT FROM com.rmsi.lim.gstcloud.shared.States WHERE stateName =='" + stateName +"'" ;
		List<States> statesReturned=(List<States>)pm.newQuery(querySearchStates).execute();
		;
		
		String querySearchDistricts = "SELECT FROM com.rmsi.lim.gstcloud.shared.Districts WHERE stateId ==" + statesReturned.get(0).getStateId();
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
}

