package org.i3xx.cloud.data.coor.domain;

import java.util.List;

public interface ObjectToOb4Repository {

	/**
	 * 
	 * @param newVal
	 */
	List<ObjectToOb4> queryByUuid(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<ObjectToOb4> queryByHistory(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<ObjectToOb4> queryByUuidAndHistory(String uuid, String history);

	/**
	 * 
	 * @param newVal
	 */
	List<ObjectToOb4> queryByIdAndTransidAndFirma(long id, int transid, String firma);

}
