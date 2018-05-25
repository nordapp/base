package org.i3xx.cloud.data.object.domain;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-Jan-2017 15:10:57
 */
public interface ObidRepository {

	/**
	 * 
	 * @param uuid
	 * @param history
	 */
	List<Obid> queryByUuidAndHistory(String uuid, String history);

	/**
	 * 
	 * @param newVal
	 */
	List<Obid> queryByUuid(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<Obid> queryByHistory(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<Obid> queryByGuid(long newVal);
	
	/**
	 * @param guid
	 */
	Obid findOne(Long guid);
	
	/**
	 * @param guid
	 */
	void delete(Long guid);
	
	/**
	 * @param obid
	 */
	Obid save(Obid obid);
}