package com.rmsi.lim.gstcloud.shared;

public class FieldVerifier 
{
	public static boolean isValidNumber(String latIn,String longIn) 
	{
	    Double lat= new Double(latIn);
	    Double longt= new Double(longIn);
		if (lat > 90 || lat <(-90) ) 
		{	
			return false;
		}
		if (longt >= 180 || lat <=(-180) ) 
		{	
			return false;
		}
		return true;
	}

	public static boolean isaNumber(String lt,String lng)
	{
		
		
			try { 
			        Double a = new Double(lt); 
			        Double b = new Double(lng); 
			        return true;
			    } catch (NumberFormatException e) {
			        return false;
			    }
			
	}
	
	public static boolean isValidName(String name) 
	{
		if (name == null) 
		{
			return false;
		}
		return name.length() > 3;
	}
	
}


