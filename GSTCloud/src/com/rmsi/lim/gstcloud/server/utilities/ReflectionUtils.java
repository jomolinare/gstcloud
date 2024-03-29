package com.rmsi.lim.gstcloud.server.utilities;

import java.lang.reflect.Method;

public class ReflectionUtils {
	
	public static Object getPropertyValue(Object obj, String propertyName) {
		try {
			Class<? extends Object> objClass = obj.getClass();
			Method method = objClass.getMethod("get" + propertyName);
			Object value = method.invoke(obj, (Object[])null);
			return value;
		}
		catch (Exception ex) {
			throw new RuntimeException(
				"Property not found " + propertyName, ex);
		}
	}
	
	public static String getPropertyStringValue(Object obj, String propertyName) {
		Object value = getPropertyValue(obj, propertyName);
		if (value == null) {
			return null;
		} 
		else {
			String valueStr = value.toString();
			return valueStr;
		}
	}
	
}
