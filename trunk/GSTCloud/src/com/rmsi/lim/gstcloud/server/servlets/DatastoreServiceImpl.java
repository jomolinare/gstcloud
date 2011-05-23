package com.rmsi.lim.gstcloud.server.servlets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.jdo.PersistenceManager;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.Point;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rmsi.lim.gstcloud.client.interfaces.DatastoreService;
import com.rmsi.lim.gstcloud.server.utilities.PMF;

public class DatastoreServiceImpl extends RemoteServiceServlet  implements DatastoreService {

	PersistenceManager pm = PMF.get().getPersistenceManager();
	private static Class<? extends Object> JDOClass;
	private static Class<? extends Object> implClass;
	private LayerServiceImpl objLayerServiceImpl = new LayerServiceImpl();
	
	@Override
	public String loadData(String layerName, String[] arrTextBoxVal) {
		
		Object object = null;
		try {
			Class[] arrParameterTypes= new Class[arrTextBoxVal.length];
			String[] arrColTypes = objLayerServiceImpl.getColumnTypes(layerName);
			//String[] arrColNames = objLayerServiceImpl.getColumnTypes(layerName);
			Class<?>[] arrParameters=new Class<?>[arrTextBoxVal.length+1];
			for(int i=0;i<arrColTypes.length;i++){
				arrParameterTypes[i] = 	Class.forName(arrColTypes[i]);
			    arrParameters[i] = 
			    	(Class<?>) arrParameterTypes[i].getConstructor(String.class).newInstance(arrTextBoxVal[i]);
			    
			}
			//arrParameters[arrTextBoxVal.length]=GeocellManager.generateGeoCell(new com.beoui.geocell.model.Point(arrParameters[1].,arrParameters[2]));
			JDOClass = Class.forName("com.rmsi.lim.gstcloud.client.model."+layerName);
			//implClass = Class.forName("com.rmsi.lim.gstcloud.client.model."+layerName+"DTO");
			Constructor<?> objConstructor= JDOClass.getConstructor(arrParameterTypes);			
			objConstructor.newInstance(arrParameters);
			pm.makePersistent(JDOClass);
			//if (layerName == )
			
			
			
			
			
			
			//object = JDOClass.newInstance();
			//System.out.println(JDOClass.getMethods());
			
			
			
		/*	for(int i=0;i<listColumns.size();i++){
				 = DTOClass.
				
				getMethod("set"+listColumns.get(i),Object.classDTOClass.getField(listColumns.get(i)).getType());
				
				Object obj = tbPopPanel[i].getValue();
				method.invoke(object,obj );
				
			}*/
			
			
			
			
			//DTOClass objDTOClass = new DTOClass();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Success";
		
	}

}
