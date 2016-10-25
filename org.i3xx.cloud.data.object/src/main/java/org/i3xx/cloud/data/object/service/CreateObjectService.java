package org.i3xx.cloud.data.object.service;

import org.i3xx.cloud.data.object.domain.Obid;
import org.springframework.stereotype.Service;

@Service
public interface CreateObjectService {

	String createObject(String classname, String uuidUser, String uuidOwner);
	
	String cloneObject(Obid origin);
}
