package org.i3xx.cloud.data.coor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name="objecthandshake", indexes={
@Index(name="history_idx", columnList="history")})
public class ObjectHandshake implements IHandshake {
	
	/** Object identification: universal unique identifier */
	private String uuid;
	
	/** Object identification: history information (uuid) */
	private String history;
	
	/** Object identification: transaction information (uuid) */
	private String transid;
	
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
		uuid = "";
		history = "";
		transid = "";
		syn = 0;
		ack = 0;
		fin = 0;
		rst = 0;
		timeout = 0;
		log = "";
	}

	@Id
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
