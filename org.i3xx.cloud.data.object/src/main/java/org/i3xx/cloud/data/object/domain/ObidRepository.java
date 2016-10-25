package org.i3xx.cloud.data.object.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObidRepository extends CrudRepository<Obid, String> {
	
	/**
	 * Search a Obid by it's id
	 * 
	 * @param uuid
	 * @return
	 */
	List<Obid> findByUuid(String uuid);
	
	/**
	 * Search a Obid by it's history
	 * 
	 * @param uuid
	 * @return
	 */
	List<Obid> findByHistory(String uuid);

	
	/**
	 * Search a Obid by it's history
	 * 
	 * @param uuid
	 * @param history
	 * @return
	 */
	List<Obid> findByUuidAndHistory(String uuid, String history);
}
