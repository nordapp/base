package org.i3xx.cloud.data.object.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;

/**
 * @author Administrator
 * @version 1.0
 * @created 05-Jan-2017 14:02:41
 */

 @Entity 
 @Table(name="Oblog", indexes={
 @Index(name="Oblog_history_idx", columnList="history"),
 @Index(name="Oblog_uuid_idx", columnList="uuid")})
public class Oblog {

	private String CLASSNAME;
	private long CREATETIMESTAMP;
	private String CREATEUSER;
	private long FLAGS;
	private long guid;
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

	public Oblog(){

	}

	public void finalize() throws Throwable {

	}

	 @Column(name="CLASSNAME", columnDefinition="varchar")
	public String getClassname(){
		return CLASSNAME;
	}


	 @Column(name="CREATETIMESTAMP", columnDefinition="bigint")
	public long getCreatetimestamp(){
		return CREATETIMESTAMP;
	}


	 @Column(name="CREATEUSER", columnDefinition="varchar")
	public String getCreateuser(){
		return CREATEUSER;
	}


	 @Column(name="FLAGS", columnDefinition="bigint")
	public long getFlags(){
		return FLAGS;
	}


	 @Id 
	 @SequenceGenerator(name="Oblog_guid_seq", sequenceName="Oblog_guid_seq", allocationSize=1) 
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Oblog_guid_seq") 
	 @Column(name="guid", updatable=false, nullable=false)
	public long getGuid(){
		return guid;
	}


	 @Column(name="history", columnDefinition="varchar")
	public String getHistory(){
		return history;
	}


	 @Column(name="LABEL", columnDefinition="varchar")
	public String getLabel(){
		return LABEL;
	}


	 @Column(name="MANDANT", columnDefinition="varchar")
	public String getMandant(){
		return MANDANT;
	}


	 @Column(name="NAME", columnDefinition="varchar")
	public String getName(){
		return NAME;
	}


	 @Column(name="OBTIMESTAMP", columnDefinition="bigint")
	public long getObtimestamp(){
		return OBTIMESTAMP;
	}


	 @Column(name="OBUSER", columnDefinition="varchar")
	public String getObuser(){
		return OBUSER;
	}


	 @Column(name="OWNER", columnDefinition="varchar")
	public String getOwner(){
		return OWNER;
	}


	 @Column(name="STEREOTYPES", columnDefinition="varchar")
	public String getStereotypes(){
		return STEREOTYPES;
	}


	 @Column(name="TRANSID", columnDefinition="integer")
	public int getTransid(){
		return TRANSID;
	}


	 @Column(name="uuid", columnDefinition="varchar")
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param flag    flag
	 */
	@Transient
	public boolean isFlag(long flag){
		return ((FLAGS & flag)==flag);
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setClassname(String newVal){
		CLASSNAME=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setCreatetimestamp(long newVal){
		CREATETIMESTAMP=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setCreateuser(String newVal){
		CREATEUSER=newVal;
	}

	/**
	 * 
	 * @param flag    flag
	 */
	@Transient
	public void setFlag(long flag){
		FLAGS &= flag;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setFlags(long newVal){
		FLAGS=newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	 
	public void setGuid(long newVal){
		guid=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setHistory(String newVal){
		history=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setLabel(String newVal){
		LABEL=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setMandant(String newVal){
		MANDANT=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setName(String newVal){
		NAME=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setObtimestamp(long newVal){
		OBTIMESTAMP=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setObuser(String newVal){
		OBUSER=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setOwner(String newVal){
		OWNER=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setStereotypes(String newVal){
		STEREOTYPES=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setTransid(int newVal){
		TRANSID=newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	 
	public void setUuid(String newVal){
		uuid=newVal;
	}

	@Transient
	public String version(){
		return "05-Jan-2017 14:02:01";
	}
}//end Oblog