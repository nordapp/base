package org.i3xx.cloud.data.coor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <br>The class implements a three-way-handshake.
 * <pre>
 * Aufbau:
 * 
 * [A] ------- SYN(x) ------> [DATA] ------- SYN(x) ------> [B]
 * [A] <-- SYN(x+1) ACK(y) -- [DATA] <-- SYN(x+1) ACK(y) -- [B]
 * [A] ------ ACK(y+1) -----> [DATA] ------ ACK(y+1) -----> [B]
 * 
 * A or B process the data
 * 
 * Abbau:
 * 
 * [A] ------- FIN(x) ------> [DATA] ------- FIN(x) ------> [B]
 * [A] <-- FIN(x+1) ACK(y) -- [DATA] <-- FIN(x+1) ACK(y) -- [B]
 * [A] ------ ACK(y+1) -----> [DATA] ------ ACK(y+1) -----> [B]
 * 
 * The RST flag resets immediately
 * 
 * [A] ------- RST(x) ------> [DATA] ------- RST(x) ------> [B]
 * 
 * or
 * 
 * [A] <----- RST(x+1) ------ [DATA] <----- RST(x+1) ------ [B]
 * </pre>
 * 
 * @author Stefan
 *
 */
@Entity 
@Table(name="Objecthandshake", indexes={
@Index(name="Objecthandshake_uuid_idx", columnList="uuid"),
@Index(name="Objecthandshake_transid_idx", columnList="transid"),
@Index(name="Objecthandshake_history_idx", columnList="history")})
public class ObjectHandshake implements IHandshake {
	
	/** Object identification: universal unique identifier */
	private String uuid;
	
	/** Object identification: history information (uuid) */
	private String history;
	
	/** Object identification: transaction information (uuid) */
	private String transid;
	
	//Index
	private long guid;
	
	//Serial of syn 
	private int syn;
	
	//Serial of ack
	private int ack;
	
	//Serial of fin
	private int fin;
	
	//Serial of rst
	private int rst;
	
	//Any flag is valid for ms
	private long timeout;
	
	//The log
	private String log;

	public ObjectHandshake() {
		syn = 0;
		ack = 0;
		fin = 0;
		rst = 0;
		timeout = 0;
		log = "";
	}

	
	@Id 
	@SequenceGenerator(name="Objecthandshake_guid_seq", sequenceName="Objecthandshake_guid_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Objecthandshake_guid_seq") 
	@Column(name="guid", updatable=false, nullable=false)
	public long getGuid() {
		return guid;
	}
	
	/**
	 * 
	 * @param newVal
	 */
	public void setGuid(long newVal) {
		guid = newVal;
	}
	
	@Column(name="uuid", columnDefinition="varchar")
	public String getUuid(){
		return uuid;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUuid(String newVal){
		uuid = newVal;
	}

	@Column(name="history", columnDefinition="varchar")
	public String getHistory(){
		return history;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setHistory(String newVal){
		history = newVal;
	}

	/**
	 * @return the transid
	 */
	@Column(name="transid", columnDefinition="varchar")
	public String getTransid() {
		return transid;
	}

	/**
	 * @param transid the transid to set
	 */
	public void setTransid(String transid) {
		this.transid = transid;
	}

	/**
	 * @return the syn
	 */
	@Column(name="SYN", columnDefinition="integer")
	public int getSyn() {
		return syn;
	}

	/**
	 * @param syn the syn to set
	 */
	public void setSyn(int syn) {
		this.syn = syn;
	}

	/**
	 * @return the ack
	 */
	@Column(name="ACK", columnDefinition="integer")
	public int getAck() {
		return ack;
	}

	/**
	 * @param ack the ack to set
	 */
	public void setAck(int ack) {
		this.ack = ack;
	}

	/**
	 * @return the timeout
	 */
	@Column(name="TIMEOUT", columnDefinition="bigint")
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the fin
	 */
	@Column(name="FIN", columnDefinition="integer")
	public int getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}

	/**
	 * @return the rst
	 */
	@Column(name="RST", columnDefinition="integer")
	public int getRst() {
		return rst;
	}

	/**
	 * @param rst the rst to set
	 */
	public void setRst(int rst) {
		this.rst = rst;
	}

	/**
	 * @return the log
	 */
	@Column(name="LOG", columnDefinition="varchar")
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * 
	 */
	public void reset() {
		syn = 0;
		ack = 0;
		fin = 0;
		rst = 0;
		timeout = 0;
		log = "";
	}
}
