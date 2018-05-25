package org.i3xx.cloud.data.base.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.i3xx.cloud.data.base.domain.ChildRelation;
import org.i3xx.cloud.data.base.domain.ChildRelationRepository;

//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Transactional
public class ChildRelationMgr {
	
	private static final Logger log = LoggerFactory.getLogger(ChildRelationService.class);
	
	//The length of one UUID in chars or bytes
	private static final int fix_len = 36;
	
	// The byte array contains a java.util.Set<String>
	private final boolean sset;
	// The data is GZIP packed
	private final boolean gzip;
	// The byte array contains a row of UTF8-Strings with a fixed length of 38 chars each.
	private final boolean sser;
	
	private final String uuid;
	private final Set<String> set;
	
	private final ChildRelationRepository repo;
	
	
	/**
	 * Note: Needs the repo as a constructor argument because the manager is created by 'new'
	 * 
	 * @param repo The repository of the ChildRelation.
	 * @param bigInt The GUID as a BigInteger
	 */
	public ChildRelationMgr(ChildRelationRepository repo, String uuid) {
		this.sset = true;
		this.sser = false;
		this.gzip = false;
		this.repo = repo;
		this.uuid = uuid;
		//this.set = null;
		try {
			List<ChildRelation> rl = repo.findByUuid(this.uuid);
			ChildRelation rel = rl.isEmpty() ? null : rl.get(0);
			if(rel==null) {
				set = new LinkedHashSet<String>();
			}else{
				byte[] buf = rel.getList();
				if(buf==null){
					set = new LinkedHashSet<String>();
				}else{
					set = deserialize(buf);
				}//fi
			}
		} catch (IOException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Note: Needs the repo as a constructor argument because the manager is created by 'new'
	 * 
	 * @param repo The repository of the ChildRelation.
	 * @param uuid  The GUID
	 * @param use_set If set to true the intern set is serialized/deserialized (use_bytes = false)
	 * @param use_bytes If set to true a very fast String to byte conversion is used (use_set = false)
	 * @param use_gzip If set to true the GZIP compression is used.
	 */
	public ChildRelationMgr(ChildRelationRepository repo, String uuid, boolean use_set, boolean use_bytes, boolean use_gzip) {
		this.sset = use_set;
		this.sser = use_bytes;
		this.gzip = use_gzip;
		this.repo = repo;
		this.uuid = uuid;
		//this.set = null;
		try {
			List<ChildRelation> rl = repo.findByUuid(this.uuid);
			ChildRelation rel = rl.isEmpty() ? null : rl.get(0);
			if(rel==null) {
				set = new LinkedHashSet<String>();
			}else{
				byte[] buf = rel.getList();
				if(buf==null){
					set = new LinkedHashSet<String>();
				}else{
					set = deserialize(buf);
				}//fi
			}
		} catch (IOException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Note: For test purposes only
	 * 
	 * @param use_set If set to true the intern set is serialized/deserialized (use_bytes = false)
	 * @param use_bytes If set to true a very fast String to byte conversion is used (use_set = false)
	 * @param use_gzip If set to true the GZIP compression is used.
	 */
	protected ChildRelationMgr(boolean use_set, boolean use_bytes, boolean use_gzip) {
		this.sset = use_set;
		this.sser = use_bytes;
		this.gzip = use_gzip;
		this.repo = null;
		this.uuid = null;
		this.set = new LinkedHashSet<String>();
	}
	
	/*
	 * Keep the set final, moved this to constructor
	 * @return
	 */
	/*
	private final ChildRelationMgr init() {
		try {
			List<ChildRelation> rl = repo.findByUuid(this.uuid);
			ChildRelation rel = rl.isEmpty() ? null : rl.get(0);
			if(rel==null) {
				set = new LinkedHashSet<String>();
			}else{
				byte[] buf = rel.getList();
				if(buf==null){
					set = new LinkedHashSet<String>();
				}else{
					set = deserialize(buf);
				}//fi
			}
		} catch (IOException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			log.error("Exception loading data.", e);
			throw new RuntimeException(e);
		}
		
		return this;
	}
	*/
	
	/*
	 * Keep the set final - and wipe this
	 */
	/*
	public void load() throws IOException {
		List<ChildRelation> rl = repo.findByUuid(this.uuid);
		ChildRelation rel = rl.isEmpty() ? null : rl.get(0);
		if(rel==null) {
			set = new LinkedHashSet<String>();
		}else{
			try {
				byte[] buf = rel.getList();
				if(buf==null){
					set = new LinkedHashSet<String>();
				}else{
					set = deserialize(buf);
				}//fi
			} catch (ClassNotFoundException e) {
				log.error("The object cannot be serialized.", e);
				throw new IOException(e);
			}
		}//fi
	}
	*/
	
	/**
	 * @param uuid
	 * @return
	 */
	public boolean add(String uuid) {
		return set.add(uuid);
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public boolean remove(String uuid) {
		return set.remove(uuid);
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public boolean contains(String uuid) {
		return set.contains(uuid);
	}
	
	/**
	 * @return
	 */
	public Iterator<String> iterator() {
		return set.iterator();
	}
	
	/**
	 * 
	 */
	public void clear() {
		set.clear();
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void save() throws IOException {
		byte[] buf = serialize(set);
		List<ChildRelation> rl = repo.findByUuid(uuid);
		ChildRelation rel = rl.isEmpty() ? null : rl.get(0);
		if(rel==null) {
			rel = new ChildRelation();
			rel.setUuid(uuid);
			rel.setList(buf);
		}else{
			rel.setList(buf);
		}//fi
		repo.save(rel);
	}
	
	/**
	 * Creates a new intern LinkedHashSet (for test purposes only)
	 * In production environment use init() or load() instead.
	 * 
	 * @return
	 */
	/*
	protected void initData() {
		set = new LinkedHashSet<String>();
	}
	*/
	
	/**
	 * Returns the intern data set (for test purposes only)
	 * 
	 * @return
	 */
	final protected Set<String> getData() {
		return set;
	}
	
	/**
	 * @param rel The set to serialize
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	final protected byte[] serialize(Set<String> rel) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		OutputStream out = bout;
		
		final int[] ii = new int[]{0};
		
		if(gzip)
			out = new GZIPOutputStream(out);
		
		final byte[] buf;
		if(sset) {
			ObjectOutputStream pc = new ObjectOutputStream(out);
			pc.writeObject(rel);
			pc.flush();
			buf = bout.toByteArray();
			pc.close();
		}
		else if(sser){
			buf = new byte[rel.size()*fix_len];
			rel.forEach(e -> {e.getBytes(0, fix_len, buf, ii[0]);ii[0]+=fix_len;});
		}
		else{
			buf = new byte[0];
		}
		
		return buf;
	}
	
	/**
	 * @param buf The byte[] to deserialize
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	final protected Set<String> deserialize(byte[] buf) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bin = new ByteArrayInputStream(buf);
		InputStream in = bin;
		
		if(gzip)
			in = new GZIPInputStream(in);
		
		Set<String> set = null;
		if(sset) {
			ObjectInputStream pc = new ObjectInputStream(in);
			set = (Set<String>) pc.readObject();
			pc.close();
		}
		else if(sser){
			set = new LinkedHashSet<String>();
			for(int i=0;i<buf.length;i+=fix_len) {
				set.add( new String(buf, 0, i, fix_len) );
			}//for
		}
		else{
			set = new LinkedHashSet<String>();
		}
		
		return set;
	}
	
}
