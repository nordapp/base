package org.i3xx.cloud.data.object.common;

/**
 * Basic information interface
 * 
 * This interface is used to get every data object by it's UUID and history.
 * 
 * Every OfficeBase object must have a random generated UUID for identification
 * and a random generated history. To create a history, clone the object using
 * the CreateObjectService or as similar mechanism before changing the object's
 * attributes. After then, update the history by a new random generated UUID
 * and leaf the UUID of the clone unchanged.
 * 
 * It is a good practice to store the history objects in a separate data store.
 * 
 * We use REDIS to get the information where the object's data is stored.
 * 
 * @author Stefan
 *
 */
public interface IbInfo {
	
	/**
	 * Gets the universal unique identifier (UUID) of the object
	 * 
	 * @return
	 */
	String getUuid();
	
	/**
	 * The UUID should not be changed if set.
	 * 
	 * @param newVal
	 */
	void setUuid(String newVal);
	
	/**
	 * Gets the history of the object.
	 * 
	 * @return
	 */
	String getHistory();
	
	/**
	 * The history should not be changed if set.
	 * 
	 * @param newVal
	 */
	void setHistory(String newVal);
}
