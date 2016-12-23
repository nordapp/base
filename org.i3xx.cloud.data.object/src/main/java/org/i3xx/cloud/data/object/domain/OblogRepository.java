package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @version 1.0
 * @created 23-Dez-2016 14:17:11
 */
public interface OblogRepository extends CrudRepository<Oblog, String> {

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