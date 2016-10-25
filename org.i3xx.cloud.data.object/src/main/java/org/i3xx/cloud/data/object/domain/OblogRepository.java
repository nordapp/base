package org.i3xx.cloud.data.object.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OblogRepository extends CrudRepository<Oblog, String> {
	
	/**
	 * Search a Obid by it's id
	 * 
	 * @param uuid
	 * @return
	 */
	List<Oblog> findByUuid(String uuid);
	
	/**
	 * Search a Obid by it's history
	 * 
	 * @param uuid
	 * @return
	 */
	List<Oblog> findByHistory(String uuid);

	
	/**
	 * Search a Obid by it's history
	 * 
	 * @param uuid
	 * @param history
	 * @return
	 */
	List<Oblog> findByUuidAndHistory(String uuid, String history);
}
