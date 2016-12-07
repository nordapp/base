package org.i3xx.cloud.data.coor.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ObjectToOb4Repository extends CrudRepository<ObjectToOb4, String> {

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
	List<ObjectToOb4> queryByUuidAndHistoryAndFirma(long id, int transid, String firma);

	/**
	 * 
	 * @param newVal
	 */
	List<ObjectToOb4> queryByIdAndTransidAndFirma(long id, int transid, String firma);

}
