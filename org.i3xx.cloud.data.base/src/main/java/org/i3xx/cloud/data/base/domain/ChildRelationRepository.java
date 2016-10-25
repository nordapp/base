package org.i3xx.cloud.data.base.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChildRelationRepository extends CrudRepository<ChildRelation, String> {
	
	/**
	 * Search a Polbewga by it's id
	 * 
	 * @param reg
	 * @return
	 */
	List<ChildRelation> findByUuid(String uuid);
	
	@Query("select p from ChildRelation p where p.id = ?1 and p.up=?2")
	List<ChildRelation> findById(long up, long id);
	
}
