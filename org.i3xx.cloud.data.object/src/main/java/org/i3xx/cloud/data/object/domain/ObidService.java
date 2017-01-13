package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.Transient;
import org.i3xx.util.basic.core.IGeneratorService;

/**
 * @author Administrator
 * @version 1.0
 * @created 13-Jan-2017 21:40:44
 */

 @Service
public class ObidService implements IGeneratorService<Obid> {


	 @Autowired
	private ObidRepository obidRepository;

	public ObidService(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param guid
	 */
	@Transient
	public void delete(Long guid){
		obidRepository.delete(guid);
	
	}

	/**
	 * 
	 * @param guid
	 */
	@Transient
	public Obid getObject(Long guid){
		List<Obid> list = obidRepository.queryByGuid(guid);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	
	}

	/**
	 * 
	 * @param uuid
	 */
	@Transient
	public Obid getObject(String uuid){
		List<Obid> list = obidRepository.queryByUuid(uuid);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	
	}

	/**
	 * 
	 * @param uuid
	 */
	@Transient
	public Obid getOrCreateObject(String uuid){
		List<Obid> list = obidRepository.queryByUuid(uuid);
		Obid obj = null;
		if(list.isEmpty()){
			obj = new Obid();
			obj.setUuid(uuid);
			obj.setHistory(uuid);
		}else{
			obj = list.get(0);
		}
		return obj;
	
	}

	/**
	 * 
	 * @param uuid
	 * @param history
	 */
	@Transient
	public Obid getOrCreateObject(String uuid, String history){
		List<Obid> list = obidRepository.queryByUuidAndHistory(uuid, history);
		Obid obj = null;
		if(list.isEmpty()){
			obj = new Obid();
			obj.setUuid(uuid);
			obj.setHistory(history);
		}else{
			obj = list.get(0);
		}
		return obj;
	
	}

	/**
	 * 
	 * @param guid
	 * @param uuid
	 * @param history
	 */
	@Transient
	public Obid getOrCreateObject(Long guid, String uuid, String history){
		Obid obj = getObject(guid);
		if(obj==null) {
			obj = getOrCreateObject(uuid, history);
		}
		return obj;
	
	}

	/**
	 * 
	 * @param obj
	 */
	@Transient
	public Obid save(Obid obj){
		return obidRepository.save(obj);
	
	}

	@Transient
	public String version(){
		return "13-Jan-2017 21:40:06";
	}
}//end ObidService