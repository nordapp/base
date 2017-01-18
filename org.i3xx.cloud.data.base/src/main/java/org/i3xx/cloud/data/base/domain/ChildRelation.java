package org.i3xx.cloud.data.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Childrelation", indexes={
@Index(name="Childrelation_history_idx", columnList="history"),
@Index(name="Childrelation_uuid_idx", columnList="uuid")})
public class ChildRelation {
	
	// The byte array contains a java.util.Set<String>
	public static final int SET_OF_STRINGS = 0x1;
	
	// The byte array contains a row of UTF8-Strings with a fixed length of 38 chars each.
	public static final int SERIAL_STRING_OF_38 = 0x2;
	
	// The data is GZIP packed
	public static final int GZIP_PACKED_BITS = 0x4;
	
	// The byte array contains a set of INODE instead data UUID's
	// The inodes are similar to the EXT2 file system
	public static final int SET_OF_INODES = 0x8;
	
	private long guid;
	private String history;
	private String uuid;
	private byte[] list;
	
	//The type of the serialization
	private int subtype;
	
	public ChildRelation() {
		guid = 0;
		uuid = null;
		history = null;
		list = new byte[0];
		subtype = 0;
	}

	@Id 
	@SequenceGenerator(name="Childrelation_guid_seq", sequenceName="Childrelation_guid_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Childrelation_guid_seq") 
	@Column(name="guid", updatable=false, nullable=false)
	public long getGuid(){
		return guid;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setGuid(long newVal){
		guid=newVal;
	}

	/**
	 * @return the uuid
	 */
	@Column(name="uuid", columnDefinition="varchar")
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUuid(String newVal){
		uuid = newVal;
	}

	@Column(name="history", columnDefinition="varchar")
	public String getHistory(){
		return history;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setHistory(String newVal){
		history=newVal;
	}

	/**
	 * @return the list
	 */
	//http://stackoverflow.com/questions/3503841/jpa-mysql-blob-returns-data-too-long
	//MEDIUMBLOB 16777215
	@Column(name="LIST", length=100000, columnDefinition="bytea")
	public byte[] getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(byte[] list) {
		this.list = list;
	}

	/**
	 * @return the subtype
	 */
	@Column(name="SUBTYPE", columnDefinition="varchar")
	public int getSubtype() {
		return subtype;
	}

	/**
	 * @param type the subtype to set
	 */
	public void setSubtype(int type) {
		this.subtype = type;
	}
}
