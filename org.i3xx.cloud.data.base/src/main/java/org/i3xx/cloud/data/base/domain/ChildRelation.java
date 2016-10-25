package org.i3xx.cloud.data.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
	
	@Id
	private String uuid;
	
	//http://stackoverflow.com/questions/3503841/jpa-mysql-blob-returns-data-too-long
	//MEDIUMBLOB 16777215
	@Column(length=100000)
	private byte[] list;
	
	//The type of the serialization
	private int type;
	
	public ChildRelation() {
		uuid = "";
		list = new byte[0];
	}

	/**
	 * @return the uuid
	 */
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

	/**
	 * @return the list
	 */
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
