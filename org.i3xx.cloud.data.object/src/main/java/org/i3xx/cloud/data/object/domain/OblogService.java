package org.i3xx.cloud.data.object.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.Transient;
import org.i3xx.util.basic.core.IGeneratorService;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-Jan-2017 13:20:15
 */

 @Service
public class OblogService implements IGeneratorService<Oblog> {


	 @Autowired
	private OblogRepository oblogRepository;

	public OblogService(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param guid
	 */
	@Transient
	public void delete(Long guid){
		oblogRepository.delete(guid);
	
	}

	/**
	 * 
	 * @param guid
	 */
	@Transient
	public Oblog getObject(Long guid){
		List<Oblog> list = oblogRepository.queryByGuid(guid);
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
	public Oblog getObject(String uuid){
		List<Oblog> list = oblogRepository.queryByUuid(uuid);
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
	public Oblog getOrCreateObject(String uuid){
		List<Oblog> list = oblogRepository.queryByUuid(uuid);
		Oblog obj = null;
		if(list.isEmpty()){
			obj = new Oblog();
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
	public Oblog getOrCreateObject(String uuid, String history){
		List<Oblog> list = oblogRepository.queryByUuidAndHistory(uuid, history);
		Oblog obj = null;
		if(list.isEmpty()){
			obj = new Oblog();
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
	public Oblog getOrCreateObject(Long guid, String uuid, String history){
		Oblog obj = getObject(guid);
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
	public Oblog save(Oblog obj){
		return oblogRepository.save(obj);
	
	}

	@Transient
	public String version(){
		return "13-Jan-2017 21:40:06";
	}
}//end OblogService