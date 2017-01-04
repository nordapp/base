package org.i3xx.cloud.data.coor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Stefan
 *
 */
@Entity 
@Table(name="Objecttoob4", indexes={
@Index(name="Objecttoob4_uuid_idx", columnList="uuid"),
@Index(name="Objecttoob4_history_idx", columnList="history"),
@Index(name="Objecttoob4_uuidindex_idx", columnList="uuid,history"),
@Index(name="Objecttoob4_idindex_idx", columnList="ID,TRANSID,FIRMA")})
public class ObjectToOb4 {
	
	/**  */
	private static final int ID_IS_DIRTY = 0x1;
	
	/**  */
	private static final int UUID_IS_DIRTY = 0x2;
	
	// OfficeBase_M5
	@Column(name="uuid", length=36, unique=true, nullable=false)
	private String uuid;
	@Column(name="history", length=36, unique=false, nullable=false)
	private String history;
	// OfficeBase_M4
	@Column(name="FIRMA", length=50, unique=false, nullable=false)
	private String FIRMA;
	@Column(name="ID", unique=false)
	private long ID;
	@Column(name="TRANSID", unique=false)
	private int TRANSID;
	//Index
	private long guid;
	@Transient
	private int dirty;
	
	public ObjectToOb4() {
		guid = 0;
		uuid = "";
		history = "";
		FIRMA = "";
		ID = 0L;
		TRANSID = 0;
	}
	
	@Transient
	public String getUuidandhistory() {
		//
		// The uuid is the object id to identify every object used by the software (except this mapping))
		// The history is the object id to identify each object's history.
		//
		// UUID ::= 128-bit UUID @see https://en.wikipedia.org/wiki/Universally_unique_identifier
		// uuid ::= UUID
		// history ::= UUID
		// primary ::= uuid '_' history
		//
		return uuid+"_"+history;
	}
	
	/**
	 * @param newValue
	 */
	@Transient
	public void setUuidandhistory(String newValue) {
		//
		// UUID ::= 128-bit UUID @see https://en.wikipedia.org/wiki/Universally_unique_identifier
		// primary ::= UUID '_' UUID
		//
		String[] s = newValue.split("\\_", 2);
		if(s!=null && s.length>=1 && s[0]!=null)
			uuid = s[0];
		if(s!=null && s.length>=2 && s[1]!=null)
			history = s[1];
	}
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	@Column(name="guid", updatable=false, nullable=false)
	public long getGuid() {
		return guid;
	}
	
	/**
	 * 
	 * @param newVal
	 */
	@Column(name="guid", columnDefinition="bigint")
	public void setGuid(long newVal) {
		guid = newVal;
	}
	
	@Column(name="uuid", columnDefinition="varchar")
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param newVal
	 */
	@Column(name="uuid", columnDefinition="varchar")
	public void setUuid(String newVal){
		uuid = newVal;
		dirty |= UUID_IS_DIRTY;
	}

	@Column(name="history", columnDefinition="varchar")
	public String getHistory(){
		return history;
	}

	/**
	 * 
	 * @param newVal
	 */
	@Column(name="history", columnDefinition="varchar")
	public void setHistory(String newVal){
		history = newVal;
		dirty |= UUID_IS_DIRTY;
	}

	@Column(name="ID", columnDefinition="bigint")
	public long getId(){
		return ID;
	}

	/**
	 * 
	 * @param newVal
	 */
	@Column(name="ID", columnDefinition="bigint")
	public void setId(long newVal){
		ID = newVal;
		dirty |= ID_IS_DIRTY;
	}
	
	@Column(name="TRANSID", columnDefinition="integer")
	public int getTransid(){
		return TRANSID;
	}

	/**
	 * 
	 * @param newVal
	 */
	@Column(name="TRANSID", columnDefinition="integer")
	public void setTransid(int newVal){
		TRANSID = newVal;
		dirty |= ID_IS_DIRTY;
	}

	@Column(name="FIRMA", columnDefinition="varchar")
	public String getFirma(){
		return FIRMA;
	}

	/**
	 * 
	 * @param newVal
	 */
	@Column(name="FIRMA", columnDefinition="varchar")
	public void setFirma(String newVal){
		FIRMA = newVal;
		dirty = ( UUID_IS_DIRTY | ID_IS_DIRTY );
	}
	
	// ------------------------------------------------------------------------
	// intern operations
	// ------------------------------------------------------------------------
	
	/**
	 * Finishes the operation and build the index
	 */
	public void finish() {
		//if( (dirty&UUID_IS_DIRTY)==UUID_IS_DIRTY )
			//
		//if( (dirty&ID_IS_DIRTY)==ID_IS_DIRTY )
			//
		
		dirty = 0;
	}
	
	/**
	 * @return True if anything has changed
	 */
	@Transient
	public boolean isDirty() {
		return dirty!=0;
	}

}
