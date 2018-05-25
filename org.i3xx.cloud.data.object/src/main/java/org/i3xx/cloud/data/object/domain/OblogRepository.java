package org.i3xx.cloud.data.object.domain;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-Jan-2017 15:10:58
 */
public interface OblogRepository {

	/**
	 * 
	 * @param uuid
	 * @param history
	 */
	List<Oblog> queryByUuidAndHistory(String uuid, String history);

	/**
	 * 
	 * @param newVal
	 */
	List<Oblog> queryByUuid(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<Oblog> queryByHistory(String newVal);

	/**
	 * 
	 * @param newVal
	 */
	List<Oblog> queryByGuid(long newVal);
	
	/**
	 * @param guid
	 */
	Oblog findOne(Long guid);
	
	/**
	 * @param guid
	 */
	void delete(Long guid);
	
	/**
	 * @param oblog
	 * @return
	 */
	Oblog save(Oblog oblog);
}