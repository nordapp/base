package org.i3xx.cloud.data.coor.service.threeway;

import static org.i3xx.cloud.data.coor.domain.ObjectHandshake.*;

import java.util.List;

import org.i3xx.cloud.data.coor.domain.IHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(LogProcessor.class);
	
	private LogWriter log;
	private IHandshake handshake;
	
	/** The partner */
	private int partner;
	
	/** The type ObjectHandshake.TYPE_ACK or ObjectHandshake.TYPE_FIN */
	private boolean ackFlag;
	
	/** If true the processor works on the log only and doesn't affect the dataset. */
	private boolean dryRun;
	
	/** The id (serial number) of the ack flag */
	private int ackId;
	
	/** The id (serial number) of the syn flag */
	private int synId;
	
	public LogProcessor() {
		this.handshake = null;
		log = new LogWriter();
		partner = 0;
		ackFlag = true;
		dryRun = false;
		ackId = 0;
		synId = 0;
	}
	
	public LogProcessor(IHandshake handshake) {
		this.handshake = handshake;
		log = new LogWriter();
		partner = 0;
		ackFlag = true;
		dryRun = false;
		ackId = 0;
		synId = 0;
	}

	/**
	 * @return the list
	 */
	public List<LogData> getList() {
		return log.getList();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<LogData> list) {
		log.setList(list);
	}

	/**
	 * @return the partner
	 */
	public int getPartner() {
		return partner;
	}

	/**
	 * The client process the data, the server is waiting for
	 * 
	 * Client: ObjectHandshake.PARTNER_A or
	 * Server: ObjectHandshake.PARTNER_B
	 * 
	 * @param partner the partner to set
	 */
	public void setPartner(int partner) {
		this.partner = partner;
	}

	/**
	 * @return the ackId
	 */
	public int getAckId() {
		return ackId;
	}

	/**
	 * @param ackId the ackId to set
	 */
	public void setAckId(int ackId) {
		this.ackId = ackId;
	}

	/**
	 * @return the synId
	 */
	public int getSynId() {
		return synId;
	}

	/**
	 * @param synId the synId to set
	 */
	public void setSynId(int synId) {
		this.synId = synId;
	}

	/**
	 * @return the type
	 */
	public boolean isAckFlag() {
		return ackFlag;
	}

	/**
	 * @param type the type to set
	 */
	public void setAckFlag(boolean ackFlag) {
		this.ackFlag = ackFlag;
	}

	/**
	 * @return the dryRun
	 */
	public boolean isDryRun() {
		return dryRun;
	}

	/**
	 * @param dryRun the dryRun to set
	 */
	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}
	
	/**
	 * Process part 1
	 */
	public void doPart1() {
		//send
		log.log(partner, ackFlag?ACK_FLAG:FIN_FLAG, ackId);
		setObject(ackFlag?ACK_FLAG:FIN_FLAG, ackId);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * Process part 2
	 */
	public void doPart2() {
		//received
		log.log(partner, ackFlag?ACK_FLAG:FIN_FLAG, ackId+1);
		setObject(ackFlag?ACK_FLAG:FIN_FLAG, ackId+1);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * Process part 3
	 */
	public void doPart3() {
		//received
		log.log(partner, SYN_FLAG, synId);
		setObject(SYN_FLAG, synId);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * Process part 4
	 */
	public void doPart4() {
		//send
		log.log(partner, SYN_FLAG, synId+1);
		setObject(SYN_FLAG, synId+1);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * The reset at part 1
	 */
	public void doReset1() {
		//send
		log.log(partner, RST_FLAG, ackId);
		setObject(RST_FLAG, ackId);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * The reset at part 2
	 */
	public void doReset2() {
		//send
		log.log(partner, RST_FLAG, ackId+1);
		setObject(RST_FLAG, ackId+1);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * The reset at part 3
	 */
	public void doReset3() {
		//send
		log.log(partner, RST_FLAG, synId);
		setObject(RST_FLAG, synId);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * The reset at part 4
	 */
	public void doReset4() {
		//send
		log.log(partner, RST_FLAG, synId+1);
		setObject(RST_FLAG, synId+1);
		if(logger.isTraceEnabled())
			logger.trace("List: {}", log.toString());
	}
	
	/**
	 * @param type
	 * @param value
	 */
	private void setObject(int type, int value) {
		logger.trace("SetObject dryRun:{}, isHandshake:{}, type:{}, value:{}",
				dryRun, (handshake!=null), type, value);
		
		if(dryRun || handshake==null)
			return;
		
		switch(type) {
		case ACK_FLAG:
			handshake.setAck(value);
			handshake.setLog(log.toString());
			break;
		case SYN_FLAG:
			handshake.setSyn(value);
			handshake.setLog(log.toString());
			break;
		case FIN_FLAG:
			handshake.setFin(value);
			handshake.setLog(log.toString());
			break;
		case RST_FLAG:
			handshake.setRst(value);
			handshake.setLog(log.toString());
			break;
		default:
		}
	}

}
