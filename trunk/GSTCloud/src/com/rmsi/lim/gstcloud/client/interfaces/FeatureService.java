package com.rmsi.lim.gstcloud.client.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface FeatureService extends RemoteService{

	List<Object> getAllFeatures();
}
