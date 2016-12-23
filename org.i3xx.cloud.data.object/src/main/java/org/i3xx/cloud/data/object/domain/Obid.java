package org.i3xx.cloud.data.object.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Each data object must have a link to this called id.
 * @author Administrator
 * @version 1.0
 * @created 13-Dez-2016 12:40:15
 */

 
 @Entity 
 @Table(name="Obid", indexes={
 @Index(name="history_idx", columnList="history"),
 @Index(name="guid_idx", columnList="guid")})
public class Obid {

	private String CLASSNAME;
	private long CREATETIMESTAMP;
	private String CREATEUSER;
	private long FLAGS;
	private String history;
	private String LABEL;
	private String MANDANT;
	private String NAME;
	private long OBTIMESTAMP = 0;
	private String OBUSER;
	private String OWNER;
	private String STEREOTYPES;
	private int TRANSID = 0;
	private String uuid;
	private long guid;



	public void finalize() throws Throwable {

	}
	public Obid(){

	}

	public String getClassname(){
		return CLASSNAME;
	}

	public long getCreatetimestamp(){
		return CREATETIMESTAMP;
	}

	public String getCreateuser(){
		return CREATEUSER;
	}

	/**
	 * @return
	 */
	public long getFlags(){
		return FLAGS;
	}

	public String getHistory(){
		return history;
	}

	public String getLabel(){
		return LABEL;
	}

	public String getMandant(){
		return MANDANT;
	}

	public String getName(){
		return NAME;
	}

	public long getObtimestamp(){
		return OBTIMESTAMP;
	}

	public String getObuser(){
		return OBUSER;
	}

	public String getOwner(){
		return OWNER;
	}

	public String getStereotypes(){
		return STEREOTYPES;
	}

	public int getTransid(){
		return TRANSID;
	}

	@Id
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param flag
	 */
	@Transient
	public boolean isFlag(long flag){
		return ((FLAGS & flag)==flag);
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClassname(String newVal){
		CLASSNAME = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatetimestamp(long newVal){
		CREATETIMESTAMP = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreateuser(String newVal){
		CREATEUSER = newVal;
	}

	/**
	 * 
	 * @param flag
	 */
	@Transient
	public void setFlag(long flag){
		FLAGS &= flag;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFlags(long newVal){
		FLAGS = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setHistory(String newVal){
		history = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLabel(String newVal){
		LABEL = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMandant(String newVal){
		MANDANT = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		NAME = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setObtimestamp(long newVal){
		OBTIMESTAMP = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setObuser(String newVal){
		OBUSER = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOwner(String newVal){
		OWNER = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setStereotypes(String newVal){
		STEREOTYPES = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTransid(int newVal){
		TRANSID = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUuid(String newVal){
		uuid = newVal;
	}

	public long getGuid(){
		return guid;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setGuid(long newVal){
		guid = newVal;
	}

	public String version(){
		return "23-Dez-2016 13:15:16";
	}
}//end Obid