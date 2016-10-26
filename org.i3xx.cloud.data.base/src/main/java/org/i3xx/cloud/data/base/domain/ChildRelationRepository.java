package org.i3xx.cloud.data.base.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChildRelationRepository extends CrudRepository<ChildRelation, String> {
	
	/**
	 * Search a ChildRelation by it's id
	 * 
	 * @param reg
	 * @return
	 */
	List<ChildRelation> findByUuid(String uuid);
}
