package org.i3xx.cloud.data.object.service;

import java.util.List;

import org.i3xx.cloud.data.object.domain.Obid;
import org.i3xx.cloud.data.object.domain.ObidRepository;
import org.i3xx.cloud.data.object.util.UuidTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CreateObjectService")
public class CreateObjectServiceImpl implements CreateObjectService {

	@Autowired
	ObidRepository obidRepository;
	
	public CreateObjectServiceImpl() {
		
	}
	
	/**
	 * @param classname
	 * @param uuidUser
	 * @param uuidOwner
	 * @return
	 */
	public String createObject(String classname, String uuidUser, String uuidOwner) {
		long currentTime = System.currentTimeMillis();
		String uuid = UuidTool.getOne();
		
		Obid obid = new Obid();
		
		obid.setUuid(uuid);
		obid.setClassname(classname);
		
		obid.setHistory(uuid);
		
		obid.setCreatetimestamp(currentTime);
		obid.setTransid(0);
		obid.setObtimestamp(currentTime);
		
		obid.setCreateuser(uuidUser);
		obid.setObuser(uuidUser);
		obid.setOwner(uuidOwner);
		obid.setLabel("");
		
		obidRepository.save(obid);
		
		return obid.getUuid();
	}
	
	/**
	 * Clones an Object with the exception of a new random generated UUID.
	 * The other fields are copied as they are, also the time fields.
	 * 
	 * @param origin the Object to clone
	 * @return
	 */
	public String cloneObject(Obid origin) {
		String uuid = UuidTool.getOne();
		boolean isCurrentState = origin.getHistory()==null || origin.getHistory().equals("") || 
				origin.getUuid().equals(origin.getHistory());
		
		Obid obid = new Obid();
		
		obid.setUuid(uuid);
		obid.setClassname(origin.getClassname());
		obid.setName(origin.getName());
		
		obid.setHistory( isCurrentState ? uuid : origin.getHistory() );
		
		obid.setCreatetimestamp(origin.getCreatetimestamp());
		obid.setTransid(origin.getTransid());
		obid.setObtimestamp(origin.getObtimestamp());
		
		obid.setCreateuser(origin.getCreateuser());
		obid.setObuser(origin.getObuser());
		obid.setOwner(origin.getOwner());
		obid.setLabel(origin.getLabel());
		
		obidRepository.save(obid);
		
		return obid.getUuid();
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public Obid getOne(String uuid) {
		List<Obid> list = obidRepository.queryByUuid(uuid);
		if(list.size()==0) {
			return null;
		}else if(list.size()==1) {
			return list.get(0);
		}else{
			//should not happen
			return list.get(0);
		}
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public List<Obid> getAll(String uuid) {
		List<Obid> list = obidRepository.queryByUuid(uuid);
		return list;
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public Obid findOne(Long guid) {
		return obidRepository.findOne(guid);
	}
}
