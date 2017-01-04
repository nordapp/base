package org.i3xx.cloud.data.object.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * @author Administrator
 * @version 1.0
 * @created 04-Jan-2017 12:24:58
 */

 @Entity 
 @Table(name="Obid", indexes={
 @Index(name="Obid_history_idx", columnList="history"),
 @Index(name="Obid_uuid_idx", columnList="uuid")})
public class Obid {

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

	public Obid(){

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

	/**
	 * @return
	 */

	 @Column(name="FLAGS", columnDefinition="bigint")
	public long getFlags(){
		return FLAGS;
	}


	 @Id 
	 @GeneratedValue(strategy=GenerationType.SEQUENCE) 
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
	@Column(name="CLASSNAME", columnDefinition="varchar")
	public void setClassname(String newVal){
		CLASSNAME = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="CREATETIMESTAMP", columnDefinition="bigint")
	public void setCreatetimestamp(long newVal){
		CREATETIMESTAMP = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="CREATEUSER", columnDefinition="varchar")
	public void setCreateuser(String newVal){
		CREATEUSER = newVal;
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
	@Column(name="FLAGS", columnDefinition="bigint")
	public void setFlags(long newVal){
		FLAGS = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="guid", columnDefinition="bigint")
	public void setGuid(long newVal){
		guid = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="history", columnDefinition="varchar")
	public void setHistory(String newVal){
		history = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="LABEL", columnDefinition="varchar")
	public void setLabel(String newVal){
		LABEL = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="MANDANT", columnDefinition="varchar")
	public void setMandant(String newVal){
		MANDANT = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="NAME", columnDefinition="varchar")
	public void setName(String newVal){
		NAME = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="OBTIMESTAMP", columnDefinition="bigint")
	public void setObtimestamp(long newVal){
		OBTIMESTAMP = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="OBUSER", columnDefinition="varchar")
	public void setObuser(String newVal){
		OBUSER = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="OWNER", columnDefinition="varchar")
	public void setOwner(String newVal){
		OWNER = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="STEREOTYPES", columnDefinition="varchar")
	public void setStereotypes(String newVal){
		STEREOTYPES = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="TRANSID", columnDefinition="integer")
	public void setTransid(int newVal){
		TRANSID = newVal;
	}

	/**
	 * 
	 * @param newVal    newVal
	 */
	@Column(name="uuid", columnDefinition="varchar")
	public void setUuid(String newVal){
		uuid = newVal;
	}

	@Transient
	public String version(){
		return "04-Jan-2017 12:22:10";
	}
}//end Obid