package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-Jan-2017 15:10:57
 */
public interface ObidRepository extends CrudRepository<Obid, Long> {

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

}