package org.i3xx.cloud.data.object.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-Jan-2017 13:20:15
 */

 @Service(value="DAO_Object_Creator_Service")
public class DAO_Object_Creator_Service {

	private static DAO_Object_Creator_Service daoCreatorService = null;

	 @Autowired
	private ObidService obidService;

	 @Autowired
	private OblogService oblogService;

	public DAO_Object_Creator_Service(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param className
	 * @param guid
	 */
	public void deleteObject(String className, long guid){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			 obidService.delete(guid);
			return;
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			 oblogService.delete(guid);
			return;
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	/**
	 * 
	 * @param className
	 * @param guid
	 */
	public Object getObject(String className, long guid){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			return obidService.getObject(guid);
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			return oblogService.getObject(guid);
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	/**
	 * 
	 * @param className
	 * @param uuid
	 */
	public Object getObject(String className, String uuid){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			return obidService.getObject(uuid);
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			return oblogService.getObject(uuid);
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	/**
	 * 
	 * @param className
	 * @param guid
	 * @param uuid
	 * @param history
	 */
	public Object getOrCreateObject(String className, long guid, String uuid, String history){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			return obidService.getOrCreateObject(guid, uuid, history);
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			return oblogService.getOrCreateObject(guid, uuid, history);
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	/**
	 * 
	 * @param className
	 * @param uuid
	 */
	public Object getOrCreateObject(String className, String uuid){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			return obidService.getOrCreateObject(uuid);
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			return oblogService.getOrCreateObject(uuid);
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	/**
	 * 
	 * @param className
	 * @param uuid
	 * @param history
	 */
	public Object getOrCreateObject(String className, String uuid, String history){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			return obidService.getOrCreateObject(uuid, history);
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			return oblogService.getOrCreateObject(uuid, history);
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}

	public static DAO_Object_Creator_Service of(){
		//singleton
		if(daoCreatorService==null)
		  daoCreatorService = new DAO_Object_Creator_Service();
		return daoCreatorService;
	}

	/**
	 * 
	 * @param className
	 * @param obj
	 */
	public void saveObject(String className, Object obj){
		if (className!=null && className.equalsIgnoreCase("Obid")){
			 obidService.save((Obid)obj);
			return;
		}
	
		if (className!=null && className.equalsIgnoreCase("Oblog")){
			 oblogService.save((Oblog)obj);
			return;
		}
		throw new IllegalArgumentException(className+" is not a valid argument.");
	}
}//end DAO_Object_Creator_Service