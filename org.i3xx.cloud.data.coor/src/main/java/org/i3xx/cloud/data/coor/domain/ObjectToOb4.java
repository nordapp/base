package org.i3xx.cloud.data.coor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author Stefan
 *
 */
@Entity 
@Table(name="objecttoob4", indexes={
@Index(name="uuid_idx", columnList="uuid"),
@Index(name="history_idx", columnList="history"),
@Index(name="uuidindex_idx", columnList="uuid, history"),
@Index(name="idindex_idx", columnList="ID, TRANSID, FIRMA")})
public class ObjectToOb4 {
	
	/**  */
	private static final int ID_IS_DIRTY = 0x1;
	
	/**  */
	private static final int UUID_IS_DIRTY = 0x2;
	
	// OfficeBase_M5
	private String uuid;
	private String history;
	// OfficeBase_M4
	private String FIRMA;
	private long ID;
	private int TRANSID;
	//Index
	private int dirty;
	
	public ObjectToOb4() {
		uuid = "";
		history = "";
		FIRMA = "";
		ID = 0L;
		TRANSID = 0;
	}
	
	@Id
	public String getPrimary() {
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
	public void setPrimary(String newValue) {
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
	
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUuid(String newVal){
		uuid = newVal;
		dirty |= UUID_IS_DIRTY;
	}

	public String getHistory(){
		return history;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setHistory(String newVal){
		history = newVal;
		dirty |= UUID_IS_DIRTY;
	}

	public long getId(){
		return ID;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(long newVal){
		ID = newVal;
		dirty |= ID_IS_DIRTY;
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
		dirty |= ID_IS_DIRTY;
	}

	public String getFirma(){
		return FIRMA;
	}

	/**
	 * 
	 * @param newVal
	 */
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
	public boolean isDirty() {
		return dirty!=0;
	}

}
