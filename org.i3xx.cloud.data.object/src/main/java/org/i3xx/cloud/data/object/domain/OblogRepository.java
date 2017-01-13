package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @version 1.0
 * @created 04-Jan-2017 22:33:54
 */
public interface OblogRepository extends CrudRepository<Oblog, Long> {

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

}