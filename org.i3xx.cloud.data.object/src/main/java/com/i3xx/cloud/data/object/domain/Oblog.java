package com.i3xx.cloud.data.object.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i3xx.cloud.data.object.common.IbInfo;

/**
 * @author Stefan Hauptmann
 * @version 1.0
 * @created 18-Okt-2016 17:54:41
 */
 
 
 
 @Entity 
 @Table
public class Oblog implements IbInfo {
	
	private String UUID;
	private String HISTORY;
	private String CLASSNAME;
	private String NAME;
	private long CREATETIMESTAMP;
	private long OBTIMESTAMP = 0;
	private int TRANSID = 0;
	private String OWNER;
	private String LABEL;
	private String CREATEUSER;
	private String OBUSER;

	public Oblog(){

	}
	
	@Id
	public String getUuid(){
		return UUID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUuid(String newVal){
		UUID = newVal;
	}

	public String getHistory(){
		return HISTORY;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setHistory(String newVal){
		HISTORY = newVal;
	}

	public String getClassname(){
		return CLASSNAME;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClassname(String newVal){
		CLASSNAME = newVal;
	}

	public String getName(){
		return NAME;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		NAME = newVal;
	}

	public long getCreatetimestamp(){
		return CREATETIMESTAMP;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatetimestamp(long newVal){
		CREATETIMESTAMP = newVal;
	}

	public long getObtimestamp(){
		return OBTIMESTAMP;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setObtimestamp(long newVal){
		OBTIMESTAMP = newVal;
	}

	public int getTransid(){
		return TRANSID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTransid(int newVal){
		TRANSID = newVal;
	}

	public String getOwner(){
		return OWNER;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOwner(String newVal){
		OWNER = newVal;
	}

	public String getLabel(){
		return LABEL;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLabel(String newVal){
		LABEL = newVal;
	}

	public String getCreateuser(){
		return CREATEUSER;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreateuser(String newVal){
		CREATEUSER = newVal;
	}

	public String getObuser(){
		return OBUSER;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setObuser(String newVal){
		OBUSER = newVal;
	}
}//end oblog
