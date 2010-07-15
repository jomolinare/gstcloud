package com.rmsi.lim.gstcloud.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.InheritanceStrategy;



@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)

public abstract class Geohashed {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY )
    private Long objectId;
	
	@Persistent
	private List<String> geocells = new ArrayList<String>();
	
	
	
	public void  setGeoCells(List<String> geocells)
	{
		this.geocells=geocells;
		
	}
	
	public List<String> getGeoCells(){
		return this.geocells;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public long getObjectId() {
		return objectId;
	}
	
	
}
