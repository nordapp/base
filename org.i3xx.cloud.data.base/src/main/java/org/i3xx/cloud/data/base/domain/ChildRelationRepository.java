package org.i3xx.cloud.data.base.domain;

import java.util.List;

public interface ChildRelationRepository {
	
	/**
	 * Get the object by it's uuid
	 * 
	 * @param uuid
	 * @return
	 */
	List<ChildRelation> findByUuid(String uuid);
	
	/**
	 * Save the object
	 * 
	 * @param childRelation
	 */
	void save(ChildRelation childRelation);
}
