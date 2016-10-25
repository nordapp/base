package com.i3xx.cloud.data.object.service;

import org.springframework.stereotype.Service;

import com.i3xx.cloud.data.object.domain.Obid;

@Service
public interface CreateObjectService {

	String createObject(String classname, String uuidUser, String uuidOwner);
	
	String cloneObject(Obid origin);
}
