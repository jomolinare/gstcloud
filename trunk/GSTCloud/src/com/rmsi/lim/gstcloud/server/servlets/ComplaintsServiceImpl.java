package com.rmsi.lim.gstcloud.server.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.ComplaintsService;
import com.rmsi.lim.gstcloud.client.model.ComplaintsDTO;
import com.rmsi.lim.gstcloud.server.model.Complaints;
import com.rmsi.lim.gstcloud.server.model.WardBoundaries;
import com.rmsi.lim.gstcloud.server.utilities.PMF;

public class ComplaintsServiceImpl extends RemoteServiceServlet implements ComplaintsService {

	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public String loadStation(ComplaintsDTO complaint) {
		try{
			Complaints objComplaint= new Complaints(complaint);
			pm.makePersistent(objComplaint);
		   } finally
		   	{
			   
			}
		return null;
	}

	@Override
	public List<ComplaintsDTO > getOutages() {
		
		String query = "SELECT FROM com.rmsi.lim.gstcloud.server.model.Complaints ";
		List<Complaints> complaintList = (List<Complaints>) pm.newQuery(query).execute();
		List<ComplaintsDTO> tempComplaintList = new ArrayList<ComplaintsDTO>();
		
		for(int i=0;i<complaintList.size();i++){
			Complaints complaints = complaintList.get(i);
			tempComplaintList.add(new ComplaintsDTO(complaints.getOutageType(),complaints.getContent(),complaints.getLatitude(),complaints.getLongitude()));
		}
		
		return tempComplaintList;
	}

}
