package org.i3xx.cloud.data.base.service;

import java.io.IOException;
import java.util.Iterator;

import org.i3xx.cloud.data.base.domain.ChildRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildRelationService {
	
	//private static final Logger log = LoggerFactory.getLogger(ChildService.class);
	
	@Autowired
	ChildRelationRepository repo;
	
	/**
	 * @param id
	 * @throws IOException
	 */
	public ChildRelationService() {
	}
	
	/**
	 * Returns a new iterator of the ChildRelationMgr
	 * 
	 * @param id The id of the object to iterate the children.
	 * @return
	 */
	public Iterator<String> iterator(String uuid) {
		return (new ChildRelationMgr(repo, uuid)).iterator();
	}
	
	/**
	 * Returns a new instance of the ChildRelationMgr
	 * 
	 * @param id The id of the object to manage.
	 * @return
	 */
	public ChildRelationMgr getRelationManager(String uuid) {
		return new ChildRelationMgr(repo, uuid);
	}
}
