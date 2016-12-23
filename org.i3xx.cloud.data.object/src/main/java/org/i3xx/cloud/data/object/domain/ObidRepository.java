package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @version 1.0
 * @created 23-Dez-2016 14:17:11
 */
public interface ObidRepository extends CrudRepository<Obid, String> {

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