package org.i3xx.cloud.data.coor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity 
@Table(name="objecttoob4", indexes={
@Index(name="history_idx", columnList="history"),
@Index(name="uuidindex_idx", columnList="uuidindex"),
@Index(name="idindex_idx", columnList="idindex")})
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
	private String uuidindex;
	private String idindex;
	
	public ObjectToOb4() {
		uuid = "";
		history = "";
		FIRMA = "";
		ID = 0L;
		TRANSID = 0;
	}

	@Id
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
	// index getter and setter
	// ------------------------------------------------------------------------
	
	/**
	 * 
	 * @param newVal
	 */
	public void setUuidindex(String newVal){
		this.uuidindex = newVal;
		dirty = 0;
	}

	public String getUuidindex(){
		return this.uuidindex;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setIdindex(String newVal){
		this.idindex = newVal;
		dirty = 0;
	}

	public String getIdindex(){
		return this.idindex;
	}
	
	// ------------------------------------------------------------------------
	// intern operations
	// ------------------------------------------------------------------------
	
	/**
	 * Finishes the operation and build the index
	 */
	public void finish() {
		if( (dirty&UUID_IS_DIRTY)==UUID_IS_DIRTY )
			buildUuidIndex();
		if( (dirty&ID_IS_DIRTY)==ID_IS_DIRTY )
			buildIdIndex();
		
		dirty = 0;
	}
	
	/**
	 * @return True if anything has changed
	 */
	public boolean isDirty() {
		return dirty!=0;
	}
	
	/**
	 * Builds the index: FIRMA '+' uuid '+' history
	 */
	private void buildUuidIndex() {
		setUuidindex(getFirma()+"+"+getUuid()+"+"+getHistory());
	}
	
	/**
	 * Builds the index: FIRMA '+' ID '+' TRANSID
	 */
	private void buildIdIndex() {
		setIdindex(getFirma()+"+"+getId()+"+"+getTransid());
	}

}
